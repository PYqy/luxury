package cn.luxury.item.controller;

import cn.luxury.item.pojo.Category;
import cn.luxury.item.pojo.CategoryAndBrand;
import cn.luxury.item.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        //如果pid的值为-1那么需要获取数据库中最后一条数据
        if (pid == -1){
            List<Category> last = this.categoryService.queryLast();
            return ResponseEntity.ok(last);
        }else {

            if (pid == null || pid < 0) {
                return ResponseEntity.badRequest().build();
            }
            List<Category> categories = categoryService.queryCategoryByPid(pid);
            if (CollectionUtils.isEmpty(categories)) {
                System.out.println(categories.get(0));
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(categories);
        }
    }

    /**
     * 根据bid查询category
     * @param bid
     * @return
     */
    @GetMapping("bid/{oldBid}")
    public ResponseEntity<List<Category>> queryCategoryByBid(@PathVariable("oldBid")Long bid ) {
        if(bid == null || bid < 0 ){
             return ResponseEntity.badRequest().build();
        }
        List<Category> categories = this.categoryService.queryCategoryByBid(bid);
//        if(CollectionUtils.isEmpty(categories)) {
//            return ResponseEntity.notFound().build();
//        }
        return ResponseEntity.ok(categories);

    }

    /**
     * insert category
     * @param category
     * @return
     */
    @PostMapping
    public ResponseEntity<Void> saveCategory(Category category){
        System.out.println(category);
        this.categoryService.saveCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * 删除
     * @param cid
     * @return
     */
    @DeleteMapping("remove/{cid}")
    public ResponseEntity<Void> removeCategoryByCid(@PathVariable("cid") Long cid) {
        System.out.println(cid);
        categoryService.removeBrandById(cid);
        return ResponseEntity.status(HttpStatus.OK).build();

    }

    /**
     * 更新
     * @return
     */
    @PutMapping
    public ResponseEntity<Void> updateCategory(Category category){
        this.categoryService.updateCategory(category);
        return  ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    /**
     * 根据ids查询names
     * @param ids
     * @return
     */
    @GetMapping
    public ResponseEntity<List<String>> queryNamesByIds(@RequestParam("ids") List<Long> ids) {
        if(CollectionUtils.isEmpty(ids)) {
            return ResponseEntity.badRequest().build();
        }
        List<String> ids1 = this.categoryService.queryNameByIds(ids);
        if(CollectionUtils.isEmpty(ids1)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(ids1);

    }




}
