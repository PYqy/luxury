package cn.luxury.item.api;



import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("category")
public interface CategoryApi {


    /**
     * 根据ids查询names
     * @param ids
     * @return
     */
    @GetMapping
    public List<String> queryNamesByIds(@RequestParam("ids")List<Long> ids) ;




}
