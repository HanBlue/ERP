package com.erp.item.service;


import com.erp.item.mapper.SpecGropMapper;
import com.erp.item.mapper.SpecParamMapper;
import com.erp.item.pojo.SpecGroup;
import com.erp.item.pojo.SpecParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SpecificationService {
    @Autowired
    private SpecGropMapper specGropMapper;
    @Autowired
    private SpecParamMapper specParamMapper;

    /**
     * 根据分类id查询规格参数组
     * @param cid
     * @return
     */
    public List<SpecGroup> queryGroupsBycId(Long cid) {
        SpecGroup record=new SpecGroup();
        record.setCid(cid);
        return this.specGropMapper.select(record);
    }
    /**
     * 新增规格分组
     * @param group 规格分组对象
     * @return 200
     */
    @Transactional
    public void saveGroup(SpecGroup group) {
        this.specGropMapper.insertSelective(group);
    }

    /**
     * 根据条件查询规格参数
     * @param gid
     * @return
     */
    public List<SpecParam> queryParams(Long gid){
        SpecParam record=new SpecParam();
        record.setGroupId(gid);
        return this.specParamMapper.select(record);
    }

    /**
     * 新增规格
     * @param param 规格属性对象
     * @return 200
     */
    @Transactional
    public void saveParams(SpecParam param) {
        this.specParamMapper.insertSelective(param);
    }
    /**
     * 删除规格
     * @param id 规格表id
     * @return
     */
    public void deleteParam(Long id) {
        this.specParamMapper.deleteParams(id);
    }

    /**
     * 修改规格
     * @param param
     */
    @Transactional
    public void updateParams(SpecParam param) {
        this.specParamMapper.updateByPrimaryKeySelective(param);
    }

    /**
     * 删除规格分组
     * @param id 规格分组表id
     * @return
     */
    public void deleteGroup(Long id) {
        this.specGropMapper.deleteByPrimaryKey(id);
    }

    /**
     * 修改规格分组
     * @group
     */
    @Transactional
    public void updateGroup(SpecGroup group) {
        this.specGropMapper.updateByPrimaryKeySelective(group);
    }
}
