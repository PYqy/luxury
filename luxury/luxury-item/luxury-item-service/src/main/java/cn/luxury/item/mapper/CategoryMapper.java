package cn.luxury.item.mapper;

import cn.luxury.item.pojo.Category;
import cn.luxury.item.pojo.CategoryAndBrand;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface CategoryMapper  extends Mapper<Category> {
    @Select("select category_id,brand_id from tb_category_brand where brand_id=#{bid}")
    List<CategoryAndBrand> selectCategoryAndBrandByBid(Long bid);
}
