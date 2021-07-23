package com.erp.item.service;

import com.erp.common.pojo.PageResult;
import com.erp.item.mapper.PromotionMapper;
import com.erp.item.pojo.Promotion;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

@Service
public class PromotionService {
    @Autowired
    PromotionMapper promotionMapper;

    public PageResult<Promotion> queryByPage(
            String key,Integer page, Integer rows, String sortBy, Boolean desc) {
        // 开始分页
        PageHelper.startPage(page, rows);
        // 过滤
        Example example = new Example(Promotion.class);
        if (StringUtils.isNotBlank(key)) {
            example.createCriteria().andLike("name", "%" + key + "%")
                    .orEqualTo("id", key);
        }
        if (StringUtils.isNotBlank(sortBy)) {
            // 排序
            String orderByClause = sortBy + (desc ? " DESC" : " ASC");
            example.setOrderByClause(orderByClause);
        }
        // 查询
        Page<Promotion> pageInfo = (Page<Promotion>) promotionMapper.selectByExample(example);
        // 返回结果
        return new PageResult<>(pageInfo.getTotal(), pageInfo);
    }

    /**
     * 新增活动
     * @param promotion 商品对象
     * @return 200
     */
    public void savePro(Promotion promotion) {
        this.promotionMapper.insertSelective(promotion);
    }
    /**
     * 更新活动
     * @param promotion
     * @return
     */
    public void updatePro(Promotion promotion) {
        this.promotionMapper.updateByPrimaryKey(promotion);
    }

    /**
     * 删除活动
     * @param id
     */
    public void deletePro(Long id) {
        this.promotionMapper.deleteByPrimaryKey(id);
    }
}
