package cn.luxury.item.service;

import cn.luxury.common.pojo.PageResult;
import cn.luxury.item.mapper.BrandMapper;
import cn.luxury.item.pojo.Brand;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mysql.jdbc.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class BrandService {
    @Autowired
    private BrandMapper brandMapper ;

    /**
     * 根据条件分页排序查询品牌信息
     * @param key
     * @param page
     * @param rows
     * @param sortBy
     * @param desc
     * @return
     */
    public PageResult<Brand> queryBrandByPage(String key, Integer page, Integer rows, String sortBy, Boolean desc) {
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();
        //根据name模糊查询 或大写字母
        if(org.apache.commons.lang.StringUtils.isNotBlank(key)) {
            criteria.andLike("name","%" + key + "%").orEqualTo("letter",key);
        }
        //分页
        PageHelper.startPage(page, rows);


        //排序
        if(org.apache.commons.lang.StringUtils.isNotBlank(sortBy)) {
            example.setOrderByClause(sortBy + " " + (desc ? "desc" : "asc"));
        }
        List<Brand> brands = brandMapper.selectByExample(example);
        PageInfo<Brand> pageInfo = new PageInfo<>(brands);
        return new PageResult<>(pageInfo.getTotal(),pageInfo.getList());
    }
    @Transactional
    public void creatBrand(Brand brand, List<Long> cids) {
        //save brand
       this.brandMapper.insertSelective(brand);
        //save middle table
       cids.forEach(cid -> {
           this.brandMapper.insertCategoryAndBrand(cid,brand.getId());
       });
    }
}
