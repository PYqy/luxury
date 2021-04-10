package cn.luxury.item.mapper;

import cn.luxury.item.pojo.SpecParam;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ParamsMapper extends Mapper<SpecParam> {
    @Select("select * from tb_spec_param where cid=#{cid}")
    List<SpecParam> selectParamsBycid(Long cid);
}
