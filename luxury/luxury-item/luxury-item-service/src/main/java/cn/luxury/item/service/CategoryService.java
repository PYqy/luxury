package cn.luxury.item.service;

import cn.luxury.item.mapper.CategoryMapper;
import cn.luxury.item.pojo.Category;
import cn.luxury.item.pojo.CategoryAndBrand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 根据父id查询子节点
     * @param pid
     * @return
     */
    public List<Category> queryCategoryByPid(Long pid) {
        Category category = new Category();
        category.setParentId(pid);
        return categoryMapper.select(category);

    }


    public List<Category> queryCategoryByBid(Long bid) {
        return categoryMapper.selectCategoryByBid(bid);
    }

    public List<String> queryNameByIds(List<Long> ids) {
        List<Category> categories = categoryMapper.selectByIdList(ids);
        return categories.stream().map(category -> category.getName()).collect(Collectors.toList());

    }

    public void saveCategory(Category category) {
        category.setId(null);

        this.categoryMapper.insert(category);
        //3.修改父节点
        Category parent = new Category();
        parent.setId(category.getParentId());
        parent.setIsParent(true);
        this.categoryMapper.updateByPrimaryKeySelective(parent);

    }

    /**
     * 分析：
     *
     * 先判断此节点是否是父节点
     * 如果是，则：
     * 需要一个可以查询所有叶子节点的函数queryAllLeafNode，参数有两个，一个是父节点，另一个是用来接收子节点的list。
     * 需要一个可以查询所有子节点的函数queryAllNode，参数同上。
     * 删除tb_category中的数据直接使用通用mappper中的方法即可。
     * 维护中间表tb_category_brand时，需要在mapper中自定义方法deleteByCategoryIdInCategoryBrand，根据category的id删除对应的数据。
     * 如果不是，则：
     * 查询此节点还有几个兄弟节点
     * 如果有兄弟，则直接删除自己，维护中间表
     * 如果没有兄弟，先删除自己，然后修改父节点的isParent为false，最后维护中间表
     * @param cid
     */
    public void removeBrandById(Long cid) {
        Category category = this.categoryMapper.selectByPrimaryKey(cid);
        if(category.getIsParent()) {
            //查找所有的叶子结点
            ArrayList<Category> list1 = new ArrayList<>();
            queryAllLeafNode(category,list1);

            //查找所有的子节点
            ArrayList<Category> list2 = new ArrayList<>();
            queryAllNode(category,list2);
            //删除tb_category中的数据,使用list2
            for (Category c:list2){
                this.categoryMapper.delete(c);
            }

            //维护中间表
            for (Category c:list1){
                this.categoryMapper.deleteByCategoryIdInCategoryBrand(c.getId());
            }

        } else {
            //查询此节点的富节点还有多少孩子
            Example example = new Example(Category.class);
            example.createCriteria().andEqualTo("parentId",category.getParentId());
            List<Category> categories = categoryMapper.selectByExample(example);
            if(categories.size()!=1) {
                //有兄弟,直接删除自己
                this.categoryMapper.deleteByPrimaryKey(category.getId());
                //维护中间表
                this.categoryMapper.deleteByCategoryIdInCategoryBrand(category.getId());

            } else {
                //已经没有兄弟了
                this.categoryMapper.deleteByPrimaryKey(category.getId());
                Category parent = new Category();
                parent.setId(category.getParentId());
                this.categoryMapper.updateByPrimaryKeySelective(parent);
                //维护中间表
                this.categoryMapper.deleteByCategoryIdInCategoryBrand(category.getId());


            }
        }
    }

    /**
     * 查询本节点下所有子节点
     * @param category
     * @param list2
     */
    private void queryAllNode(Category category, ArrayList<Category> list2) {
        list2.add(category);
        Example example = new Example(Category.class);
        example.createCriteria().andEqualTo("parentId",category.getId());
        List<Category> list=this.categoryMapper.selectByExample(example);

        for (Category category1:list){
            queryAllNode(category1,list2);
        }


    }

    /**
     * 查询本节点下所包含的所有叶子结点，用于维护tb_category_brand中间表
     * @param category
     * @param list1
     */
    private void queryAllLeafNode(Category category, ArrayList<Category> list1) {
        if (!category.getIsParent()) {list1.add(category); }
        Example example = new Example(Category.class);
        example.createCriteria().andEqualTo("parentId",category.getId());

        List<Category> list=this.categoryMapper.selectByExample(example);
        for (Category c: list) {
            queryAllLeafNode(c,list1);

        }

    }


    public void updateCategory(Category category) {

        this.categoryMapper.updateByPrimaryKeySelective(category);

    }

    /**
     * 查询数据库中最后一条数据
     * @return
     */
    public List<Category> queryLast() {
        List<Category> last =this.categoryMapper.selectLast();
        return last;
    }


}
