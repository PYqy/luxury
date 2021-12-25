package cn.yqy.elasticsearch;

import cn.yqy.elasticsearch.entity.Item;
import cn.yqy.elasticsearch.repository.ItemRepository;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.aggregations.metrics.avg.InternalAvg;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilter;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ElasticsearchTest {
    @Autowired
    private ElasticsearchTemplate  elasticsearchTemplate;
    @Autowired
    private ItemRepository itemRepository;
    @Test
    public void createTest(){
        //创建索引，会根据@Document注解信息来创建
        elasticsearchTemplate.createIndex(Item.class);
        //配置映射，会根据ITem泪中的id，Field字段来自动完成映射
        elasticsearchTemplate.putMapping(Item.class);

    }
    @Test
    public void addDocument() {
        Item item = new Item(1L, "小米手机7", " 手机",
                "小米", 3499.00, "http://image.leyou.com/13123.jpg");
        itemRepository.save(item);

    }
    @Test
    public void addDocumentList() {
        List<Item> list = new ArrayList<>();
        list.add(new Item(1L, "小米手机7", "手机", "小米", 3299.00, "http://image.leyou.com/13123.jpg"));
        list.add(new Item(2L, "坚果手机R1", "手机", "锤子", 3699.00, "http://image.leyou.com/13123.jpg"));
        list.add(new Item(3L, "华为META10", "手机", "华为", 4499.00, "http://image.leyou.com/13123.jpg"));
        list.add(new Item(4L, "小米Mix2S", "手机", "小米", 4299.00, "http://image.leyou.com/13123.jpg"));
        list.add(new Item(5L, "荣耀V10", "手机", "华为", 2799.00, "http://image.leyou.com/13123.jpg"));
        // 接收对象集合，实现批量新增
        itemRepository.saveAll(list);
     }
     @Test
    public void simpleSearchById() {
         Optional<Item> id = itemRepository.findById(1l);
         System.out.println(id.get());
     }
     @Test
    public void simpleSearchAll() {
         Iterable<Item> price = itemRepository.findAll(Sort.by("price").descending());
         price.forEach(System.out::println);
     }
    @Test
    public void queryByPriceBetween(){
        List<Item> list = this.itemRepository.findByPriceBetween(2000.00, 3500.00);
        for (Item item : list) {
            System.out.println("item = " + item);
        }
    }
    /**
     * 以下是高级查询，基本查询无法满足我们的模糊查询、词条等
     */

    @Test
    public void advancedQueryTest() {
        //词条查询
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("title", "小米");
        Iterable<Item> search = itemRepository.search(matchQueryBuilder);
        search.forEach(System.out::println);
        //模糊查询
        QueryBuilders.fuzzyQuery("title","小米手机").fuzziness(Fuzziness.TWO);
    }
    /**
     * elasticsearch提供很多可用的查询方式，但是不够灵活。如果想玩过滤或者聚合查询等就很难了。
     * 以下是自定义查询
     */
    @Test
    public  void basicMatchQuery() {
        //构建查询条件
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        //添加基本的分词器
        nativeSearchQueryBuilder.withQuery(QueryBuilders.matchQuery("title","小米手机"));
       //执行搜索，获取结果
        Page<Item> search = itemRepository.search(nativeSearchQueryBuilder.build());
        //打印总条数
        System.out.println(search.getTotalElements());
        //打印总页数
        System.out.println(search.getTotalPages());
        search.forEach(System.out::println);
    }
    @Test
    public void pageQueryTest() {
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withQuery(QueryBuilders.matchAllQuery());
        //分页是从0开始的
        nativeSearchQueryBuilder.withPageable(PageRequest.of(1,2));
        Page<Item> search = itemRepository.search(nativeSearchQueryBuilder.build());
        search.forEach(System.out::println);
    }
    @Test
    public  void    soryQueryTest() {
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        nativeSearchQueryBuilder.withQuery(QueryBuilders.matchAllQuery());
        nativeSearchQueryBuilder.withSort(SortBuilders.fieldSort("id").order(SortOrder.DESC));

        Page<Item> search = itemRepository.search(nativeSearchQueryBuilder.build());
        search.forEach(System.out::println);
    }
    @Test
    public void aggregationTest() {

        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
       //不查询任何结果
        nativeSearchQueryBuilder.withSourceFilter(new FetchSourceFilter(new String[]{},null));
        //添加一个新聚合，聚合类型：terms，聚合名字：testaggs，聚合字段：brand，在brand聚合桶嵌套聚合求平均值
        nativeSearchQueryBuilder.addAggregation(AggregationBuilders.terms("testaggs").field("brand").subAggregation(AggregationBuilders.avg("priceavg").field("price")));
        //查询 需要强转成AggregatedPage类型
        AggregatedPage search = (AggregatedPage) itemRepository.search(nativeSearchQueryBuilder.build());
       //解析
        //从结果中取出名为brands的那个聚合
        //因为是利用String类型字段来进行的term聚合，所以结果要强转为StringTerm类型
        StringTerms testaggs = (StringTerms) search.getAggregation("testaggs");
        //获取桶
        List<StringTerms.Bucket> buckets = testaggs.getBuckets();
        buckets.forEach(bucket -> {
            System.out.println(bucket.getKeyAsString());
            System.out.println(bucket.getDocCount());
            //获取子聚合结果，需要强转
            InternalAvg aggregations = (InternalAvg) bucket.getAggregations().asMap().get("priceavg");
            System.out.println(aggregations.getValue());
        });

    }
}