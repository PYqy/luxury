package cn.luxury.item.service;

import cn.luxury.item.mapper.CategoryMapper;
import cn.luxury.item.pojo.Category;
import cn.luxury.item.pojo.CategoryAndBrand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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


    public List<CategoryAndBrand> queryCategoryByBid(Long bid) {
        return categoryMapper.selectCategoryAndBrandByBid(bid);
    }
}
