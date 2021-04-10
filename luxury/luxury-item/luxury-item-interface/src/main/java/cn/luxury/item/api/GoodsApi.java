package cn.luxury.item.api;


import cn.luxury.common.pojo.PageResult;
import cn.luxury.item.pojo.Sku;
import cn.luxury.item.pojo.SpuBo;
import cn.luxury.item.pojo.SpuDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface GoodsApi {

    /**
     * 分页查询显示
     * @param key
     * @param saleable
     * @param page
     * @param rows
     * @return
     */
    @GetMapping("/spu/page")
    public PageResult<SpuBo> querySpuByPage(@RequestParam(value = "key" ,required = false)String key,
            @RequestParam(value = "saleable" ,required = false)Boolean saleable,
            @RequestParam(value = "page" ,defaultValue = "1")Integer page,
            @RequestParam(value = "rows" ,defaultValue = "5")Integer rows
    );

    /**
     * 根据spuid查询spu
     * @param id
     * @return
     */
    @GetMapping("spu/detail/{id}")
    public SpuDetail querySpuDetailBySpuId(@PathVariable("id") Long id) ;
    /**
     * 根据spuid查询sku
     * @param id
     * @return
     */
    @GetMapping("sku/list")
    public List<Sku> querySkuBySpuId(@RequestParam("id") Long id) ;
}
