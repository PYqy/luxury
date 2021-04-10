package cn.luxury.item.controller;

import cn.luxury.common.pojo.PageResult;
import cn.luxury.item.pojo.Sku;
import cn.luxury.item.pojo.SpuBo;
import cn.luxury.item.pojo.SpuDetail;
import cn.luxury.item.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    /**
     * 分页查询显示
     * @param key
     * @param saleable
     * @param page
     * @param rows
     * @return
     */
    @GetMapping("/spu/page")
    public ResponseEntity<PageResult<SpuBo>> querySpuByPage(
            @RequestParam(value = "key" ,required = false)String key,
            @RequestParam(value = "saleable" ,required = false)Boolean saleable,
            @RequestParam(value = "page" ,defaultValue = "1")Integer page,
            @RequestParam(value = "rows" ,defaultValue = "5")Integer rows
    ){
        PageResult<SpuBo> result = goodsService.querySpuByPage(key,saleable,page,rows);
        if(result == null || CollectionUtils.isEmpty(result.getItems())){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);

    }

    /**
     * 插入商品
     * @param spuBo
     * @return
     */
    @PostMapping("goods")
    public ResponseEntity<Void> saveGoods(@RequestBody SpuBo spuBo) {
        try {
            goodsService.save(spuBo);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    /**
     * 根据spuid查询spu
     * @param id
     * @return
     */
    @GetMapping("spu/detail/{id}")
    public ResponseEntity<SpuDetail> querySpuDetailBySpuId(@PathVariable("id") Long id) {
        SpuDetail spuDetail = goodsService.querySpuDetailBySpuId(id);
        System.out.println(spuDetail);
        if(spuDetail == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(spuDetail);
    }

    /**
     * 根据spuid查询sku
     * @param id
     * @return
     */
    @GetMapping("sku/list")
    public ResponseEntity<List<Sku>> querySkuBySpuId(@RequestParam("id") Long id) {
        List<Sku> skus  = goodsService.querySkuBySpuId(id);
        if(CollectionUtils.isEmpty(skus)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(skus);
    }
    @DeleteMapping("spu/remove/{id}")
    public ResponseEntity<Void> removeSpuBySpuId(@PathVariable("id") Long id) {
        goodsService.removeSpuBySpuId(id);
        return ResponseEntity.status(HttpStatus.OK).build();

    }
    @PutMapping
    public ResponseEntity<Void> updateGoods(@RequestBody SpuBo spu) {
        try {
            this.goodsService.update(spu);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * 商品上下架
     * @param id
     * @return
     */
    @RequestMapping("spu/out/{id}")
    public ResponseEntity<Void> goodsSoldOut(@PathVariable("id") Long id){
        this.goodsService.goodsSoldOut(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
