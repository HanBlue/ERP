package com.erp.item.service;

import com.erp.common.pojo.PageResult;
import com.erp.item.mapper.OrderDetailMapper;
import com.erp.item.mapper.OrderMapper;
import com.erp.item.pojo.Order;
import com.erp.item.pojo.OrderDetail;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;

    /**
     * 分页查询订单信息
     * @param key
     * @param page
     * @param rows
     * @param sortBy
     * @param desc
     * @return
     */
    public PageResult<Order> queryOrdersByPage(String key, Integer page, Integer rows, String sortBy, Boolean desc) {
        // 开始分页
        PageHelper.startPage(page, rows);
        // 过滤
        Example example = new Example(Order.class);
        if (StringUtils.isNotBlank(key)) {
            example.createCriteria().andLike("buyer_name", "%" + key + "%")
                    .orEqualTo("id", key);
        }
        if (StringUtils.isNotBlank(sortBy)) {
            // 排序
            String orderByClause = sortBy + (desc ? " DESC" : " ASC");
            example.setOrderByClause(orderByClause);
        }
        // 查询
        List<Order> orders=orderMapper.selectByExample(example);
        for(Order order:  orders){
            OrderDetail de=orderDetailMapper.selectOneById(order.getId());
            order.setNum(de.getNum());
            order.setTitle(de.getTitle());
            order.setOwn_spec(de.getOwn_spec());
            order.setPrice(de.getPrice());
        }
        Page<Order> pageInfo = (Page<Order>) orders;

        // 返回结果
        return new PageResult<>(pageInfo.getTotal(), pageInfo);

    }

}
