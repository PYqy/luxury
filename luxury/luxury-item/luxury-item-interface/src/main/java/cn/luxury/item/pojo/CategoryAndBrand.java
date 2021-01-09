package cn.luxury.item.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="tb_category_brand")
public class CategoryAndBrand {
    private Long categoryId;
    private Long brandId;

    @Override
    public String toString() {
        return "CategoryAndBrand{" +
                "categoryId=" + categoryId +
                ", brandId=" + brandId +
                '}';
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }
}
