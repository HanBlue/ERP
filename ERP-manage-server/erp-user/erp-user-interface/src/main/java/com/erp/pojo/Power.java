package com.erp.pojo;

import javax.persistence.Table;

@Table(name = "tb_power")
public class Power {
    private Long rid;
    private Long pid;

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }
}
