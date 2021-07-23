package com.erp.item.mapper;

import com.erp.item.pojo.OrderDetail;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

public interface OrderDetailMapper extends Mapper<OrderDetail> {
    @Select("SELECT * FROM tb_order_detail WHERE id = #{id} limit 1")
    OrderDetail selectOneById(Long id);
}
