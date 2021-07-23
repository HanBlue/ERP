package com.erp.item.controller;

import com.erp.common.pojo.PageResult;
import com.erp.item.pojo.Brand;
import com.erp.item.service.BrandService;
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
@Api(tags="品牌管理")
@Controller
@RequestMapping("brand")
public class BrandContoller {
    @Autowired
    private BrandService brandService;
    /**
     *分页查询品牌
     * @param key 搜索关键词
     * @param page  当前页
     * @param rows  每页大小
     * @param sortBy 排序字段
     * @param desc 是否为降序
     * @return PageResult
     */
    @ApiOperation(value = "分页查询品牌列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key",value = "搜索关键词",dataType = "String"),
            @ApiImplicitParam(name = "page",value = "当前页",dataType = "Integer"),
            @ApiImplicitParam(name = "rows",value = "每页大小",dataType = "Integer"),
            @ApiImplicitParam(name = "sortBy",value = "排序字段",dataType = "String"),
            @ApiImplicitParam(name = "desc",value = "是否为降序",dataType = "Boolean")})
    @GetMapping("page")
    public ResponseEntity<PageResult<Brand>> queryBrandsBypage(
            @RequestParam(value = "key",required = false)String key,
            @RequestParam(value = "page",defaultValue = "1")Integer page,
            @RequestParam(value = "rows", defaultValue = "5")Integer rows,
            @RequestParam(value = "sortBy",required = false)String sortBy,
            @RequestParam(value = "desc",required = false)Boolean desc
    ){
        PageResult<Brand> result=this.brandService.queryBrandsByPage(key,page,rows,sortBy,desc);
        if(result ==null|| CollectionUtils.isEmpty(result.getItems())){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    /**
     * 新增品牌
     * @param brand
     * @param cids
     * @return
     */
    @ApiOperation(value = "新增品牌")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "brand",value = "品牌对象",dataType = "Brand"),
            @ApiImplicitParam(name = "cids",value = "分类id",dataType = "Integer")})
    @PostMapping
    public ResponseEntity<Void> saveBrand(Brand brand, @RequestParam("cids") List<Long> cids) {
        this.brandService.saveBrand(brand, cids);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * 更新品牌
     * @param brand
     * @param cids
     * @return
     */
    @ApiOperation(value = "更新品牌")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "brand",value = "品牌对象",dataType = "Brand"),
            @ApiImplicitParam(name = "cids",value = "分类id",dataType = "Integer")})
    @PutMapping
    public ResponseEntity<Void> updateBrand(Brand brand, @RequestParam("cids") List<Long> cids) {
        this.brandService.updateBrand(brand, cids);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    /**
     * 删除品牌
     * @param bid 商品id
     * @return 200
     */
    @ApiOperation(value = "删除品牌")
    @ApiImplicitParam(name = "bids",value = "商品id",dataType = "Integer")
    @DeleteMapping
    public ResponseEntity<Void> deleteBrand(@RequestParam("id") Long bid) {
        this.brandService.deleteBrand(bid);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * 根据分类查询品牌
     * @param cid
     * @return
     */
    @ApiOperation(value = "根据分类查询品牌")
    @ApiImplicitParam(name = "cids",value = "分类id",dataType = "Integer")
    @GetMapping("cid/{cid}")
    public ResponseEntity<List<Brand>> queryBrandByCategory(@PathVariable("cid") Long cid) {
        List<Brand> list = this.brandService.queryBrandByCategory(cid);
        if(list == null){
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(list);
    }
}
