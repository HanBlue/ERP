package com.erp.item.pojo;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tb_order_detail")
public class OrderDetail {
    @Id
    private Long ids;//id
    private Long id;//订单id
    private Long num;//购买数量
    private String title; //商品标题
    private String own_spec; //商品属性
    private Long price; //价格

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getids() {
        return ids;
    }

    public void setOid(Long ids) {
        this.ids = ids;
    }

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOwn_spec() {
        return own_spec;
    }

    public void setOwn_spec(String own_spec) {
        this.own_spec = own_spec;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
