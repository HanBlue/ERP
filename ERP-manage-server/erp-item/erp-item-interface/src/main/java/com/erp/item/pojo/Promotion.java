package com.erp.item.pojo;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tb_promotion")
public class Promotion {
    @Id
    private Long id;//活动id
    private String name;// 名称
    private String image;// 图片
    private String text;//活动描述

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
