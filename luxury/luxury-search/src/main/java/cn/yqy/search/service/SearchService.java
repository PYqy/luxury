package cn.yqy.search.service;

import cn.luxury.common.pojo.PageResult;
import cn.luxury.item.pojo.*;
import cn.yqy.search.client.BrandClient;
import cn.yqy.search.client.CategoryClient;
import cn.yqy.search.client.GoodsClient;
import cn.yqy.search.client.ParamsClient;
import cn.yqy.search.pojo.Goods;
import cn.yqy.search.pojo.SearchRequest;
import cn.yqy.search.pojo.SearchResult;
import cn.yqy.search.repository.GoodsRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.beans.binding.ObjectExpression;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.LongTerms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.FetchSourceFilter;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.bouncycastle.asn1.x500.style.RFC4519Style.l;

@Service
public class SearchService {
    @Autowired
    private CategoryClient categoryClient;
    @Autowired
    private BrandClient brandClient;
    @Autowired
    private GoodsClient goodsClient;
    @Autowired
    private ParamsClient paramsClient;
    @Autowired
    private GoodsRepository goodsRepository;
    private ObjectMapper mapper = new ObjectMapper();

    private static final ObjectMapper MAPPER = new ObjectMapper();
    public Goods buildGoods(Spu spu) throws IOException {
//        Goods goods = new Goods();
//
//        //根据分类id查询分类名称
//        List<String> names = this.categoryClient.queryNamesByIds(Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3()));
//        //根据brandid查询品牌
//        Brand brand = this.brandClient.queryBrandById(spu.getBrandId());
//        //根据spuid select sku
//        List<Sku> skus = this.goodsClient.querySkuBySpuId(spu.getId());
//        ArrayList<Long> prices = new ArrayList<>();
//        //sku的必要字段信息
//        List<Map<String,Object>> skuMapList = new ArrayList<>();
//        skus.forEach(sku -> {
//            prices.add(sku.getPrice());
//            Map<String,Object> map = new HashMap<>();
//            map.put("id",sku.getId());
//            map.put("title",sku.getTitle());
//            map.put("price",sku.getPrice());
//            //获取sku中的图片，数据库中的图片，可能是多张，以，分割，所以也以，分割 ，返回图片数组，获取第一张
//            map.put("image",StringUtils.isBlank(sku.getImages()) ? "" : StringUtils.split(sku.getImages(),",")[0]);
//            skuMapList.add(map);
//        });
//        //根据spucid3查询所有搜索规则参数
//        List<SpecParam> params = this.paramsClient.queryParams(spu.getCid3(), true);
//        //根据spuid查询spudetai
//        SpuDetail spuDetail = this.goodsClient.querySpuDetailBySpuId(spu.getId());
//        //通用规格参数值，进行反序列化
//        Map<String, Object> genericSpecParams = MAPPER.readValue(spuDetail.getGenericSpec(), new TypeReference<Map<String, Object>>(){});
//        //特殊规格参数值，进行反序列化
//        Map<String, List<Object>> SpecialSpecParams = MAPPER.readValue(spuDetail.getSpecialSpec(), new TypeReference<Map<String, List<Object>>>(){});
//
//        Map<String, Object> specs = new HashMap<>();
//        params.forEach(param -> {
//            //判断规格参数的类型是否是通用的规格参数
//            String value ="";
//            if(param.getGeneric()) {
//                if(genericSpecParams!=null && genericSpecParams.size()>0) {
//                    if(param.getId()!=null)
//                    value = genericSpecParams.get(param.getId().toString()).toString();
//                }
//               //是否是数值类型，树脂类型应该返回一个区间
//                if (param.getNumeric()) {
//                    value = chooseSegment(value, param);
//                }
//                specs.put(param.getName(), value);
//            } else {
//                //特殊规格参数
//                List<Object> value2 = SpecialSpecParams.get(param.getId().toString());
//                specs.put(param.getName(), value2);
//            }
//
//        });
//        goods.setId(spu.getId());
//        goods.setCid1(spu.getCid1());
//        goods.setCid2(spu.getCid2());
//        goods.setCid3(spu.getCid3());
//        goods.setBrandId(spu.getBrandId());
//        goods.setCreateTime(spu.getCreateTime());
//        goods.setSubTitle(spu.getSubTitle());
//        //+空格 防止 这两个字段产生分词，分类名称、品牌名称
//        goods.setAll(spu.getTitle() + " " + StringUtils.join(names," ") + " " + brand.getName());
//        //get spu下的all sku的 price
//        goods.setPrice(prices);
//        //get spu 下的all sku 并转化json字符串
//        goods.setSkus(MAPPER.writeValueAsString(skuMapList));
//        //获取所有的查询规则参数 {name:value,...}
//        goods.setSpecs(specs);
//        return goods;


        Goods goods = new Goods();

        // 查询商品分类名称
        List<String> names = this.categoryClient.queryNamesByIds(Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3()));
        // 查询sku
        List<Sku> skus = this.goodsClient.querySkuBySpuId(spu.getId());
        // 查询详情
        SpuDetail spuDetail = this.goodsClient.querySpuDetailBySpuId(spu.getId());
        // 查询规格参数
        List<SpecParam> params = this.paramsClient.queryParams(spu.getCid3(), true);

        // 处理sku，仅封装id、价格、标题、图片，并获得价格集合
        List<Long> prices = new ArrayList<>();
        List<Map<String, Object>> skuList = new ArrayList<>();
        skus.forEach(sku -> {
            prices.add(sku.getPrice());
            Map<String, Object> skuMap = new HashMap<>();
            skuMap.put("id", sku.getId());
            skuMap.put("title", sku.getTitle());
            skuMap.put("price", sku.getPrice());
            skuMap.put("image", StringUtils.isBlank(sku.getImages()) ? "" : StringUtils.split(sku.getImages(), ",")[0]);
            skuList.add(skuMap);
        });

        // 处理规格参数
        Map<String, Object> genericSpecs = mapper.readValue(spuDetail.getGenericSpec(), new TypeReference<Map<String, Object>>() {
        });
        Map<String, Object> specialSpecs = mapper.readValue(spuDetail.getSpecialSpec(), new TypeReference<Map<String, Object>>() {
        });
        // 获取可搜索的规格参数
        Map<String, Object> searchSpec = new HashMap<>();

        // 过滤规格模板，把所有可搜索的信息保存到Map中
        Map<String, Object> specMap = new HashMap<>();
        params.forEach(p -> {
            if (p.getSearching()) {
                if (p.getGeneric()) {
                    String value = genericSpecs.get(p.getId().toString()).toString();
                    if(p.getNumeric()){
                        value = chooseSegment(value, p);
                    }
                    specMap.put(p.getName(), StringUtils.isBlank(value) ? "其它" : value);
                } else {
                    specMap.put(p.getName(), specialSpecs.get(p.getId().toString()));
                }
            }
        });

        goods.setId(spu.getId());
        goods.setSubTitle(spu.getSubTitle());
        goods.setBrandId(spu.getBrandId());
        goods.setCid1(spu.getCid1());
        goods.setCid2(spu.getCid2());
        goods.setCid3(spu.getCid3());
        goods.setCreateTime(spu.getCreateTime());
        goods.setAll(spu.getTitle() + " " + StringUtils.join(names, " "));
        goods.setPrice(prices);
        goods.setSkus(mapper.writeValueAsString(skuList));
        goods.setSpecs(specMap);
        return goods;
    }

    private String chooseSegment(String value, SpecParam p) {
        double val = NumberUtils.toDouble(value);
        String result = "其它";
        // 保存数值段
        for (String segment : p.getSegments().split(",")) {
            String[] segs = segment.split("-");
            // 获取数值范围
            double begin = NumberUtils.toDouble(segs[0]);
            double end = Double.MAX_VALUE;
            if(segs.length == 2){
                end = NumberUtils.toDouble(segs[1]);
            }
            // 判断是否在范围内
            if(val >= begin && val < end){
                if(segs.length == 1){
                    result = segs[0] + p.getUnit() + "以上";
                }else if(begin == 0){
                    result = segs[1] + p.getUnit() + "以下";
                }else{
                    result = segment + p.getUnit();
                }
                break;
            }
        }
        return result;
    }

    public SearchResult search(SearchRequest request) {
        if(StringUtils.isBlank(request.getKey())) {
            return null;
        }
        //自定义查询构建器
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        //添加查询条件
        queryBuilder.withQuery(QueryBuilders.matchQuery("all",request.getKey()).operator(Operator.AND));
        //添加分ye,从0开始
        queryBuilder.withPageable(PageRequest.of(request.getPage() - 1,request.getSize()));
        //add 结果集过滤
        queryBuilder.withSourceFilter(new FetchSourceFilter(new String[]{"id","skus","subTitle"},null));

        //添加分类和品牌的聚合
        String categoryAggName = "categories";
        String brandAggName = "brands";
        queryBuilder.addAggregation(AggregationBuilders.terms(categoryAggName).field("cid3"));
        queryBuilder.addAggregation(AggregationBuilders.terms(brandAggName).field("brandId"));
        //执行查询
        AggregatedPage<Goods> search = (AggregatedPage<Goods>)this.goodsRepository.search(queryBuilder.build());
        //获取聚合结果并解析
        List<Map<String,Object>> categories = getCategoryAggResult(search.getAggregation(categoryAggName));
        List<Brand> brands = getBrandAggResult(search.getAggregation(brandAggName));
        return new SearchResult(search.getTotalElements(),search.getTotalPages(),search.getContent(),categories,brands);
    }
    //解析品牌聚合结果集
    private List<Brand> getBrandAggResult(Aggregation aggregation) {
        LongTerms terms = (LongTerms) aggregation;
        List<Brand> brands = terms.getBuckets().stream().map(bucket -> {
            return this.brandClient.queryBrandById(bucket.getKeyAsNumber().longValue());
        }).collect(Collectors.toList());

        return brands;
    }
    //解析分类聚合结果集
    private List<Map<String,Object>> getCategoryAggResult(Aggregation aggregation) {
        LongTerms terms = (LongTerms) aggregation;
        return terms.getBuckets().stream().map(bucket -> {
            Map<String,Object> map = new HashMap<>();
            Long id = bucket.getKeyAsNumber().longValue();
            List<String> strings = this.categoryClient.queryNamesByIds(Arrays.asList(id));
            map.put("id",id);
            map.put("name",strings.get(0));
            return map;
        }).collect(Collectors.toList());
    }


}
