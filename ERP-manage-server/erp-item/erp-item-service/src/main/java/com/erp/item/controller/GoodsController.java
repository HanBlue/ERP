package com.erp.item.controller;

import com.erp.common.pojo.PageResult;
import com.erp.item.bo.SpuBo;
import com.erp.item.pojo.Sku;
import com.erp.item.pojo.Spu;
import com.erp.item.pojo.SpuDetail;
import com.erp.item.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Api(tags="商品列表管理")
@Controller
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    /**
     * 分页查询SPU
     * @param page
     * @param rows
     * @param key
     * @return
     */
    @ApiOperation(value = "分页查询SPU")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key",value = "搜索关键词",dataType = "String"),
            @ApiImplicitParam(name = "page",value = "当前页",dataType = "Integer"),
            @ApiImplicitParam(name = "rows",value = "每页大小",dataType = "Integer"),
            @ApiImplicitParam(name = "sortBy",value = "排序字段",dataType = "String"),
            @ApiImplicitParam(name = "desc",value = "是否为降序",dataType = "Boolean")})
    @GetMapping("spu/page")
    public ResponseEntity<PageResult<SpuBo>> querySpuByPage(
            @RequestParam(value = "key", required = false) String key,
            @RequestParam(value = "saleable", required = false) Boolean saleable,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "rows", defaultValue = "5") Integer rows
            ) {
        // 分页查询spu信息
        PageResult<SpuBo> result = this.goodsService.querySpuByPage( key,saleable,page,rows);
        if (result ==null||CollectionUtils.isEmpty(result.getItems())) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }
    /**
     * 新增商品
     * @param spuBo
     * @return
     */
    @ApiOperation(value = "新增商品")
    @ApiImplicitParam(name = "SpuBo",value = "商品对象",dataType = "SpuBo")
    @PostMapping("goods")
    public ResponseEntity<Void> saveGoods(@RequestBody SpuBo spuBo) {
        try {
            this.goodsService.save(spuBo);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
     * 更新商品信息
     * @param spuBo
     * @return
     */
    @ApiOperation(value = "更新商品")
    @ApiImplicitParam(name = "SpuBo",value = "商品对象",dataType = "SpuBo")
    @PutMapping("goods")
    public ResponseEntity<Void> updateGoods(@RequestBody SpuBo spuBo){
        this.goodsService.updateGoods(spuBo);
        return ResponseEntity.noContent().build();
    }


    /**
     * 根据SpuId查询SpuDetail
     * @param spuId
     * @return
     */
    @ApiOperation(value = "根据SpuId查询SpuDetail")
    @ApiImplicitParam(name = "spuId",value = "共有规格id",dataType = "Long")
    @GetMapping("spu/detail/{spuId}")
    public ResponseEntity<SpuDetail> querySpuDetailBySpuId(@PathVariable("spuId")Long spuId){
        SpuDetail spuDetail = this.goodsService.querySpuDetailBySpuId(spuId);
        if (spuDetail == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(spuDetail);
    }

    /**
     * 根据SpuId查询Sku
     * @param spuId
     * @return
     */
    @ApiOperation(value = "根据SpuId查询Sku")
    @ApiImplicitParam(name = "spuId",value = "共有规格id",dataType = "Long")
    @GetMapping("sku/list")
    public ResponseEntity<List<Sku>> querySkusBySpuId(@RequestParam("id")Long spuId){
        List<Sku> skus = this.goodsService.querySkusBySpuId(spuId);
        if (CollectionUtils.isEmpty(skus)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(skus);
    }

    /**
     * 根据商品id查询Spu
     * @param id
     * @return
     */
    @GetMapping("{id}")
    @ApiOperation(value = "根据SpuId查询Sku")
    @ApiImplicitParam(name = "id",value = "商品id",dataType = "Long")
    public ResponseEntity<Spu> querySpuById(@PathVariable("id")Long id){
        Spu spu = this.goodsService.querySpuById(id);
        if (spu == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(spu);
    }

    /**
     * 根据SkuId查询Sku
     * @param skuId
     * @return
     */
    @ApiOperation(value = "根据SpuId查询Sku")
    @ApiImplicitParam(name = "skuId",value = "skuId",dataType = "Long")
    @GetMapping("sku/{skuId}")
    public ResponseEntity<Sku> querySkuBySkuId(@PathVariable("skuId")Long skuId){
        Sku sku = this.goodsService.querySkuBySkuId(skuId);
        if (sku == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(sku);
    }

}
