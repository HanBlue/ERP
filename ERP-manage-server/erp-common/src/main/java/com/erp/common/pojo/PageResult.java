package com.erp.common.pojo;

import java.util.List;

public class PageResult<T> {
    private Long total;
    private Integer totalPage;
    private List<T> items;

    public PageResult() {
    }

    public PageResult(Long total, Integer totalPage, List<T> item) {
        this.total = total;
        this.totalPage = totalPage;
        this.items = item;
    }

    public PageResult(Long total, List<T> item) {
        this.total = total;
        this.items = item;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getItems() {
        return items;
    }

    public void setItems(List<T> items) {
        this.items = items;
    }
}
