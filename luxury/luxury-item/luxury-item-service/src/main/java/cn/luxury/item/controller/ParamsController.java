package cn.luxury.item.controller;

import cn.luxury.item.pojo.SpecParam;
import cn.luxury.item.service.ParamsService;
import net.minidev.json.JSONArray;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("spec")
public class ParamsController {
    @Autowired
    private ParamsService paramsService;

    /**
     * 根据条件查询参数
     * @param cid
     * @param searching
     * @param generic
     * @return
     */
    @GetMapping("params")
    public ResponseEntity<List<SpecParam>> queryParams(
            @RequestParam(value="cid", required = false) Long cid,
            @RequestParam(value="searching", required = false) Boolean searching,
            @RequestParam(value="generic", required = false) Boolean generic
    ) {
        List<SpecParam> list =
                this.paramsService.querySpecParams(cid,searching,generic);
        if(list == null || list.size() == 0){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(list);
    }
    @GetMapping("paramsNoGen")
    public ResponseEntity<List<SpecParam>> queryParams(
            @RequestParam(value="cid", required = false) Long cid,
            @RequestParam(value="searching", required = false) Boolean searching
    ) {
        List<SpecParam> list =
                this.paramsService.querySpecParams(cid,searching);
        if(list == null || list.size() == 0){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(list);
    }

    @GetMapping("{cid}")
    public ResponseEntity<String> queryParams(@PathVariable Long cid) {
        List<SpecParam> list = this.paramsService.queryParamsBycid(cid);
        if(list == null || list.size() == 0){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(JSONArray.toJSONString(list));
    }

    @DeleteMapping("param/{id}")
    public ResponseEntity<Void> deleteParamById(@PathVariable Long id) {
        this.paramsService.deleteParamById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @PutMapping("param")
    public ResponseEntity<Void> updateParam(SpecParam specParam) {

        this.paramsService.updateParam(specParam);
        return ResponseEntity.status(HttpStatus.OK).build();

    }
    @PostMapping("param")
    public ResponseEntity<Void> inserteParam( SpecParam specParam) {
        try {
           this.paramsService.save(specParam);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }
}