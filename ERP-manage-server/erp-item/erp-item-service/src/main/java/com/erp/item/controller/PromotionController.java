package com.erp.item.controller;

import com.erp.common.pojo.PageResult;
import com.erp.item.pojo.Promotion;
import com.erp.item.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
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
    @DeleteMapping
    public ResponseEntity<Void> deletePro(@RequestParam("id") Long id) {
        this.promotionService.deletePro(id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
