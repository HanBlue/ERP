package com.erp.item.service;

import com.erp.item.mapper.CategoryMapper;

import com.erp.item.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 根据parentId查询子类目
     *
     * @param pid
     * @return
     */
    public List<Category> queryCategoryListByParentId(Long pid) {
        Category record = new Category();
        record.setParentId(pid);
        return this.categoryMapper.select(record);
    }

    public List<Category> queryByBrandId(Long bid) {
        return this.categoryMapper.queryByBrandId(bid);
    }



    /**
     * 多个分类id查询名称
     * @param ids
     * @return
     */
    public List<String> queryNamesByIds(List<Long> ids) {
        return this.categoryMapper.selectByIdList(ids).stream().map(category-> category.getName()).collect(Collectors.toList());
    }
}