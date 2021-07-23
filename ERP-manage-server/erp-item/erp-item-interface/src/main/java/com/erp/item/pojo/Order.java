package com.erp.item.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "tb_order")
public class Order {
    @Id
    private Long id;//订单id
    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date create_time;// 创建时间
    private String buyer_name; //买家昵称
    private String buyer_message;//买家留言
    private String shipping_name;//物流名称
    private String shipping_code;//物流单号
    private String receiver;//收货人
    private String receiver_mobile;//收货人手机
    private String receiver_address;//收获地址
    private Long num;//购买数量
    private Long price; //价格
    private String title; //商品标题
    private String own_spec; //商品属性

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getBuyer_name() {
        return buyer_name;
    }

    public void setBuyer_name(String buyer_name) {
        this.buyer_name = buyer_name;
    }

    public String getBuyer_message() {
        return buyer_message;
    }

    public void setBuyer_message(String buyer_message) {
        this.buyer_message = buyer_message;
    }

    public String getShipping_name() {
        return shipping_name;
    }

    public void setShipping_name(String shipping_name) {
        this.shipping_name = shipping_name;
    }

    public String getShipping_code() {
        return shipping_code;
    }

    public void setShipping_code(String shipping_code) {
        this.shipping_code = shipping_code;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getReceiver_mobile() {
        return receiver_mobile;
    }

    public void setReceiver_mobile(String receiver_mobile) {
        this.receiver_mobile = receiver_mobile;
    }

    public String getReceiver_address() {
        return receiver_address;
    }

    public void setReceiver_address(String receiver_address) {
        this.receiver_address = receiver_address;
    }
}
