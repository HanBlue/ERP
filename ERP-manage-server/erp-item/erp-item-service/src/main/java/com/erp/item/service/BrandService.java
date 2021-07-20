package com.erp.item.service;

import com.erp.item.mapper.BrandMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.erp.common.pojo.PageResult;
import com.erp.item.pojo.Brand;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import java.util.List;


@Service
public class BrandService {
    @Autowired
    private BrandMapper brandMapper;

    /**
     *品牌查询功能
     * @param key 搜索关键词
     * @param page  当前页
     * @param rows  每页大小
     * @param sortBy 排序字段
     * @param desc 是否为降序
     * @return PageResult
     */
    public PageResult<Brand> queryBrandsByPage(
            String key,Integer page, Integer rows, String sortBy, Boolean desc) {
        // 开始分页
        PageHelper.startPage(page, rows);
        // 过滤
        Example example = new Example(Brand.class);
        if (StringUtils.isNotBlank(key)) {
            example.createCriteria().andLike("name", "%" + key + "%")
                    .orEqualTo("letter", key);
        }
        if (StringUtils.isNotBlank(sortBy)) {
            // 排序
            String orderByClause = sortBy + (desc ? " DESC" : " ASC");
            example.setOrderByClause(orderByClause);
        }
        // 查询
        Page<Brand> pageInfo = (Page<Brand>) brandMapper.selectByExample(example);
        // 返回结果
        return new PageResult<>(pageInfo.getTotal(), pageInfo);
    }

    /**
     * 新增品牌
     * @param brand 品牌对象
     * @param cids 商品外键
     */
    @Transactional
    public void saveBrand(Brand brand, List<Long> cids) {
        // 新增品牌信息
        this.brandMapper.insertSelective(brand);
        // 新增品牌和分类中间表
        for (Long cid : cids) {
            this.brandMapper.insertCategoryBrand(cid, brand.getId());
        }
    }
    /**
     * 更新品牌
     * @param brand 品牌对象
     * @param cids 商品外键
     */
    @Transactional
    public void updateBrand(Brand brand, List<Long> cids) {
        // 新增品牌信息
        this.brandMapper.updateByPrimaryKey(brand);
        // 新增品牌和分类中间表
        for (Long cid : cids) {
            this.brandMapper.deleteCategoryBrand( brand.getId());
            this.brandMapper.insertCategoryBrand(cid, brand.getId());
        }
    }
    /**
     * 删除品牌
     * @param bid 商品外键
     */
    public void deleteBrand( Long bid) {
        this.brandMapper.deletebid(bid);
        this.brandMapper.deleteCategoryBrand(bid);
    }


    public List<Brand> queryBrandByCategory(Long cid) {
        return this.brandMapper.queryByCategoryId(cid);
    }
}
