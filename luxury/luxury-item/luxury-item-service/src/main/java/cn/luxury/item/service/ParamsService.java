package cn.luxury.item.service;

import cn.luxury.item.mapper.ParamsMapper;
import cn.luxury.item.pojo.SpecParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParamsService {
    @Autowired
    private ParamsMapper paramsMapper;
    public List<SpecParam> querySpecParams(Long cid, Boolean searching, Boolean generic) {
        SpecParam param = new SpecParam();
        param.setCid(cid);
        param.setSearching(searching);
        param.setGeneric(generic);
        return this.paramsMapper.select(param);
    }
    public List<SpecParam> querySpecParams(Long cid, Boolean searching) {
        SpecParam param = new SpecParam();
        param.setCid(cid);
        param.setSearching(searching);
        return this.paramsMapper.select(param);
    }

    public List<SpecParam> queryParamsBycid(Long cid) {
        return this.paramsMapper.selectParamsBycid(cid);
    }

    public void deleteParamById(Long id) {
        this.paramsMapper.deleteByPrimaryKey(id);
    }

    public void updateParam(SpecParam specParam) {
        this.paramsMapper.updateByPrimaryKey(specParam);
    }

    public void save(SpecParam specParam) {
        this.paramsMapper.insert(specParam);
    }
}
