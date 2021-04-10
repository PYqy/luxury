package cn.yqy.search.client;

import cn.luxury.item.api.ParamsApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("item-service")
public interface ParamsClient extends ParamsApi {
}
