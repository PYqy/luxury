package cn.luxury.elasticsearch;

import cn.luxury.common.pojo.PageResult;
import cn.luxury.item.pojo.SpuBo;
import cn.yqy.search.LuxuryApplication;
import cn.yqy.search.client.GoodsClient;
import cn.yqy.search.pojo.Goods;
import cn.yqy.search.repository.GoodsRepository;
import cn.yqy.search.service.SearchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.netflix.eureka.serviceregistry.EurekaAutoServiceRegistration;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest(classes = LuxuryApplication.class)
@RunWith(SpringRunner.class)
public class ElasticSearchTest {
    @MockBean
    private EurekaAutoServiceRegistration eurekaAutoServiceRegistration;


    @Autowired
    private ElasticsearchTemplate template;
    @Autowired
    private GoodsRepository goodsRepository;
    @Autowired
    private SearchService searchService;
    @Autowired
    private GoodsClient goodsClient;


    @Test
    public void test() {
        this.template.createIndex(Goods.class);
        this.template.putMapping(Goods.class);
        Integer page = 1;
        Integer rows = 10;
        do {
            //分页查询spu
            PageResult<SpuBo> result = this.goodsClient.querySpuByPage(null, null, page, rows);
            //当前页数据
            List<SpuBo> items = result.getItems();
            //处理list<spubo> -》 List<Goods>
            List<Goods> collect = items.stream().map(spuBo -> {
                try {
                    return this.searchService.buildGoods(spuBo);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }).collect(Collectors.toList());
            //执行新增数据
            this.goodsRepository.saveAll(collect);
            rows = items.size();
            page++;
        } while (rows == 10);

    }
}
