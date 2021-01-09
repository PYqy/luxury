package cn.luxury.item.controller;

import cn.luxury.common.pojo.PageResult;
import cn.luxury.item.pojo.Brand;
import cn.luxury.item.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    /**
     * 根据条件分页排序查询品牌信息
     * @param key
     * @param page
     * @param rows
     * @param sortBy
     * @param desc
     * @return
     */
    @GetMapping("page")
    public ResponseEntity<PageResult<Brand>> queryBrandByPage (
            @RequestParam(value = "key" ,required = false) String key,
            @RequestParam(value = "page" ,defaultValue = "1") Integer page,
            @RequestParam(value = "rows" ,defaultValue = "5") Integer rows,
            @RequestParam(value = "sortBy" ,required = false) String sortBy,
            @RequestParam(value = "desc" ,required = false) Boolean desc
    ) {
        PageResult<Brand> result = this.brandService.queryBrandByPage(key,page,rows,sortBy,desc);
        if(CollectionUtils.isEmpty(result.getItems())) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping
    public ResponseEntity<Void> createBrand(Brand brand,@RequestParam("cids") List<Long> cids) {
        this.brandService.creatBrand(brand,cids);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

}
