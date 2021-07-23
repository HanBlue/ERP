package com.erp.item.controller;

import com.erp.common.pojo.PageResult;
import com.erp.item.pojo.Promotion;
import com.erp.item.service.PromotionService;
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

@Api(tags="促销管理")
@Controller
@RequestMapping("promotion")
public class PromotionController {
    @Autowired
    private PromotionService promotionService;

    /**
     *分页查询促销活动
     * @param key 搜索关键词
     * @param page  当前页
     * @param rows  每页大小
     * @param sortBy 排序字段
     * @param desc 是否为降序
     * @return PageResult
     */
    @ApiOperation(value = "分页查询促销活动")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key",value = "搜索关键词",dataType = "String"),
            @ApiImplicitParam(name = "page",value = "当前页",dataType = "Integer"),
            @ApiImplicitParam(name = "rows",value = "每页大小",dataType = "Integer"),
            @ApiImplicitParam(name = "sortBy",value = "排序字段",dataType = "String"),
            @ApiImplicitParam(name = "desc",value = "是否为降序",dataType = "Boolean")})
    @GetMapping("page")
    public ResponseEntity<PageResult<Promotion>> queryByPage(
            @RequestParam(value = "key",required = false)String key,
            @RequestParam(value = "page",defaultValue = "1")Integer page,
            @RequestParam(value = "rows", defaultValue = "5")Integer rows,
            @RequestParam(value = "sortBy",required = false)String sortBy,
            @RequestParam(value = "desc",required = false)Boolean desc
    ){
        PageResult<Promotion> result=this.promotionService.queryByPage(key,page,rows,sortBy,desc);
        if(result ==null|| CollectionUtils.isEmpty(result.getItems())){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }
    /**
     * 新增活动
     * @param promotion 商品对象
     * @return 200
     */
    @ApiOperation(value = "新增促销活动")
    @ApiImplicitParam(name = "promotion",value = "活动对象",dataType = "Promotion")
    @PostMapping
    public ResponseEntity<Void> savePro(Promotion promotion) {
        this.promotionService.savePro(promotion);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * 更新活动
     * @param promotion
     * @return
     */
    @ApiOperation(value = "更新促销活动")
    @ApiImplicitParam(name = "promotion",value = "活动对象",dataType = "Promotion")
    @PutMapping
    public ResponseEntity<Void> updatePro(Promotion promotion) {
        this.promotionService.updatePro(promotion);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * 删除活动
     * @param id
     * @return
     */
    @ApiOperation(value = "删除促销活动")
    @ApiImplicitParam(name = "id",value = "促销活动id",dataType = "Long")
    @DeleteMapping
    public ResponseEntity<Void> deletePro(@RequestParam("id") Long id) {
        this.promotionService.deletePro(id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
