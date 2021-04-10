package cn.luxury.item.service;

import cn.luxury.common.pojo.PageResult;
import cn.luxury.item.mapper.*;
import cn.luxury.item.pojo.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.netflix.discovery.converters.Auto;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.genid.GenId;
import tk.mybatis.mapper.util.StringUtil;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GoodsService {

    @Autowired
    private SpuMapper spuMapper;
    @Autowired
    private SpuDetailMapper spuDetailMapper;
    @Autowired
    private BrandMapper brandMapper;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private SkuMapper skuMapper;
    @Autowired
    private StockMapper stockMapper;

    public PageResult<SpuBo> querySpuByPage(String key, Boolean saleable, Integer page, Integer rows) {

        //开始分页
        PageHelper.startPage(page,rows);
        Example example = new Example(Spu.class);
        Example.Criteria criteria = example.createCriteria();
        //查询
        //判断是否有模糊查询
        if(StringUtils.isNotBlank(key)){
            criteria.andLike("title","%" + key + "%");

        }
        //判断是否上架下架
        if(saleable != null) {
            criteria.andEqualTo("saleable",saleable);
        }
        List<Spu> spus = spuMapper.selectByExample(example);
        PageInfo<Spu> info = new PageInfo(spus);
        //spu转换spubo
        List<SpuBo> spuBos = spus.stream().map(spu -> {
            SpuBo spuBo = new SpuBo();
            //copy
            BeanUtils.copyProperties(spu, spuBo);
            //根据brandid查询brand
            Brand brand = brandMapper.selectByPrimaryKey(spuBo.getBrandId());
            spuBo.setBname(brand.getName());
            //根据list<cid> 查询 通用mapper 有一个SelectByIdListMapper
            //Arrays.asList(spuBo.getCid1(),spuBo.getCid2(),spuBo.getCid3())
            List<Long> ids = new ArrayList<>();
            ids.add(spuBo.getCid1());
            ids.add(spuBo.getCid2());
            ids.add(spuBo.getCid3());
            List<String> strings = categoryService.queryNameByIds(ids);
            spuBo.setCname(StringUtils.join(strings, "-"));
            return spuBo;
        }).collect(Collectors.toList());
        //返回
        PageResult<SpuBo> result = new PageResult<>();
        result.setTotal(info.getTotal());
        result.setItems(spuBos);
        return result;
    }

    /**
     * 插入商品
     *
     * @param spuBo
     */
    @Transient
    public void save(SpuBo spuBo) {
        //insert spu
        spuBo.setId(null);
        spuBo.setSaleable(true);
        spuBo.setValid(true);
        spuBo.setCreateTime(new Date());;
        spuBo.setLastUpdateTime(spuBo.getCreateTime());
        System.out.println(spuBo.getTitle());
        spuMapper.insert(spuBo);
        //insert spudetail
        SpuDetail spuDetail = spuBo.getSpuDetail();
        spuDetail.setSpuId(spuBo.getId());
        spuDetailMapper.insert(spuDetail);
        //insert sku
        //insert stock
        spuBo.getSkus().forEach(sku->{
            sku.setSpuId(spuBo.getId());
            sku.setCreateTime(new Date());;
            sku.setLastUpdateTime(spuBo.getCreateTime());
            skuMapper.insert(sku);

            Stock stock = new Stock();
            stock.setSkuId(sku.getId());
            stock.setStock(sku.getStock());
            stockMapper.insert(stock);


        });

    }

    public SpuDetail querySpuDetailBySpuId(Long id) {
        return spuDetailMapper.selectByPrimaryKey(id);
    }

    public List<Sku> querySkuBySpuId(Long id) {
        Sku sku = new Sku();
        sku.setSpuId(id);
        List<Sku> select = skuMapper.select(sku);
        select.forEach(skuu -> {
            Stock stock = stockMapper.selectByPrimaryKey(skuu.getId());
            skuu.setStock(stock.getStock());
        });
        return select;
    }
    /**
     * 商品删除
     * @param id
     */
    @Transactional
    public void removeSpuBySpuId(long id) {
        //删除spu表中的数据
        this.spuMapper.deleteByPrimaryKey(id);

        //删除spu_detail中的数据
        Example example = new Example(SpuDetail.class);
        example.createCriteria().andEqualTo("spuId",id);
        this.spuDetailMapper.deleteByExample(example);


        List<Sku> skuList = this.skuMapper.selectByExample(example);
        for (Sku sku : skuList){
            //删除sku中的数据
            this.skuMapper.deleteByPrimaryKey(sku.getId());
            //删除stock中的数据
            this.stockMapper.deleteByPrimaryKey(sku.getId());
        }

    }

@Transactional
    public void update(SpuBo spu) {
        //根据spu查询所有的sku
        Sku sku = new Sku();
        sku.setSpuId(spu.getId());
        List<Sku> select = skuMapper.select(sku);
        //删除stock
        select.forEach(sku2 -> {
            Stock stock = new Stock();
            stock.setSkuId(sku2.getId());
            //delete stock
            stockMapper.delete(stock);

        });
        //根据spudeletesku
        skuMapper.delete(sku);
        //insert sku
        //insert stock
        spu.getSkus().forEach(sku3->{
            sku3.setSpuId(spu.getId());
            sku3.setCreateTime(new Date());;
            sku3.setLastUpdateTime(spu.getCreateTime());
            skuMapper.insert(sku3);

            Stock stock = new Stock();
            stock.setSkuId(sku3.getId());
            stock.setStock(sku3.getStock());
            stockMapper.insert(stock);

        });
        //更新 spu spudetail
        spu.setCreateTime(null);
        spu.setLastUpdateTime(new Date());
        spu.setValid(null);
        spu.setSaleable(null);
        spuMapper.updateByPrimaryKeySelective(spu);
        spuDetailMapper.updateByPrimaryKeySelective(spu.getSpuDetail());

}
    /**
     * 商品下架
     * @param id
     */
    @Transactional(rollbackFor = Exception.class)
    public void goodsSoldOut(Long id) {
        //下架或者上架spu中的商品

        Spu oldSpu = this.spuMapper.selectByPrimaryKey(id);
        Example example = new Example(Sku.class);
        example.createCriteria().andEqualTo("spuId",id);
        List<Sku> skuList = this.skuMapper.selectByExample(example);
        if (oldSpu.getSaleable()){
            //下架
            oldSpu.setSaleable(false);
            this.spuMapper.updateByPrimaryKeySelective(oldSpu);
            //下架sku中的具体商品
            for (Sku sku : skuList){
                sku.setEnable(false);
                this.skuMapper.updateByPrimaryKeySelective(sku);
            }

        }else {
            //上架
            oldSpu.setSaleable(true);
            this.spuMapper.updateByPrimaryKeySelective(oldSpu);
            //上架sku中的具体商品
            for (Sku sku : skuList){
                sku.setEnable(true);
                this.skuMapper.updateByPrimaryKeySelective(sku);
            }
        }

    }


}
