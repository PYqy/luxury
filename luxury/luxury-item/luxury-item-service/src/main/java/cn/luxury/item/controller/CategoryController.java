package cn.luxury.item.controller;

import cn.luxury.item.pojo.Category;
import cn.luxury.item.pojo.CategoryAndBrand;
import cn.luxury.item.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 根据父节点查询子节点
     * @param pid
     * @return
     */
    @GetMapping("list")
    public ResponseEntity<List<Category>> queryCategoryByPid(@RequestParam(value = "pid",defaultValue = "0")Long pid) {
        if(pid == null || pid < 0) {
            return ResponseEntity.badRequest().build();
        }
        List<Category> categories = categoryService.queryCategoryByPid(pid);
       if(CollectionUtils.isEmpty(categories)) {
           System.out.println(categories.get(0));
           return ResponseEntity.notFound().build();
       }
       return ResponseEntity.ok(categories);
    }
    @GetMapping("bid/{oldBid}")
    public ResponseEntity<List<CategoryAndBrand>> queryCategoryByBid(@PathVariable("oldBid")Long bid ) {
        if(bid == null || bid < 0 ){
             return ResponseEntity.badRequest().build();
        }
        List<CategoryAndBrand> categories = this.categoryService.queryCategoryByBid(bid);
        if(CollectionUtils.isEmpty(categories)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(categories);

    }
}
