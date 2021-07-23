package com.erp.item.controller;

import com.erp.common.pojo.PageResult;
import com.erp.item.pojo.Order;
import com.erp.item.pojo.OrderDetail;
import com.erp.item.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
@Api(tags="交易管理")
@Controller
@RequestMapping("order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    /**
     *分页查询订单
     * @param key 搜索关键词
     * @param page  当前页
     * @param rows  每页大小
     * @param sortBy 排序字段
     * @param desc 是否为降序
     * @return PageResult
     */
    @ApiOperation(value = "分页查询订单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key",value = "搜索关键词",dataType = "String"),
            @ApiImplicitParam(name = "page",value = "当前页",dataType = "Integer"),
            @ApiImplicitParam(name = "rows",value = "每页大小",dataType = "Integer"),
            @ApiImplicitParam(name = "sortBy",value = "排序字段",dataType = "String"),
            @ApiImplicitParam(name = "desc",value = "是否为降序",dataType = "Boolean")})
    @GetMapping("/page")
    public ResponseEntity<PageResult<Order>> queryOrdersByPage(
            @RequestParam(value = "key",required = false)String key,
            @RequestParam(value = "page",defaultValue = "1")Integer page,
            @RequestParam(value = "rows", defaultValue = "7")Integer rows,
            @RequestParam(value = "sortBy",required = false)String sortBy,
            @RequestParam(value = "desc",required = false)Boolean desc
    ){
        PageResult<Order> result=this.orderService.queryOrdersByPage(key,page,rows,sortBy,desc);
        if(result ==null|| CollectionUtils.isEmpty(result.getItems())){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

}
