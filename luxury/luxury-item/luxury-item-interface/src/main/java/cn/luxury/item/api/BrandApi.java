package cn.luxury.item.api;

import cn.luxury.item.pojo.Brand;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("brand")
public interface BrandApi {


    @GetMapping("{id}")
    public Brand queryBrandById(@PathVariable("id") Long id) ;
}
