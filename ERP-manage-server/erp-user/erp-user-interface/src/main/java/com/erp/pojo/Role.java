package com.erp.pojo;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tb_role")
public class Role {
    @Id
    private Long id;
    private String name;// 用户权限
    private String role_key;// 权限标识
    private String role_sort;// 权限顺序

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

    public String getRole_key() {
        return role_key;
    }

    public void setRole_key(String role_key) {
        this.role_key = role_key;
    }

    public String getRole_sort() {
        return role_sort;
    }

    public void setRole_sort(String role_sort) {
        this.role_sort = role_sort;
    }
}
