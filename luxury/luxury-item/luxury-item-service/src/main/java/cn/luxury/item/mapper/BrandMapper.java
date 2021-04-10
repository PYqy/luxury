package cn.luxury.item.mapper;

import cn.luxury.item.pojo.Brand;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BrandMapper extends Mapper<Brand> {
    @Insert("insert into tb_category_brand(category_id,brand_id) values(#{cid},#{bid})")
    void insertCategoryAndBrand(@Param("cid") Long cid, @Param("bid") Long bid);
    @Select("SELECT * from tb_brand a inner join tb_category_brand b on a.id = b.brand_id where b.category_id = #{cid}")
    List<Brand> queryBrandByCategoryId(Long cid);

    @Delete("DELETE FROM tb_category_brand WHERE brand_id = #{id}")
    void deleteByBrandIdInCategoryBrand(Long id);
}
