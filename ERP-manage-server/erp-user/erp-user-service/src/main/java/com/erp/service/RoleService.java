package com.erp.service;

import com.erp.common.pojo.PageResult;
import com.erp.mapper.RoleMapper;
import com.erp.pojo.Role;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleMapper roleMapper;

    /**
     * 查询权限列表
     * @param key
     * @param page
     * @param rows
     * @param sortBy
     * @param desc
     * @return
     */
    public PageResult<Role> queryRolesList(String key, Integer page, Integer rows, String sortBy, Boolean desc) {
        // 开始分页
        PageHelper.startPage(page, rows);
        // 过滤
        Example example = new Example(Role.class);
        if (StringUtils.isNotBlank(key)) {
            example.createCriteria().andLike("role_name", "%" + key + "%")
                    .orEqualTo("id", key);
        }
        if (StringUtils.isNotBlank(sortBy)) {
            // 排序
            String orderByClause = sortBy + (desc ? " DESC" : " ASC");
            example.setOrderByClause(orderByClause);
        }
        // 查询
        Page<Role> pageInfo = (Page<Role>) roleMapper.selectByExample(example);
        // 返回结果
        return new PageResult<>(pageInfo.getTotal(), pageInfo);
    }

    /**
     * 根据ID查询用户
     * @param id
     * @return
     */
    public List<Role> queryById(Long id) {
        Role record = new Role();
        record.setId(id);
        Role role = this.roleMapper.selectOne(record);
        if (role == null){
            return null;
        }
        List<Role> roles = new ArrayList<Role>();
        roles.add(role);
        return roles;
    }

    /**
     * 删除角色
     * @param id
     */
    public void deleteRole(Long id) {
        this.roleMapper.deleteByPrimaryKey(id);
    }

    /**
     * 查询所有角色
     * @return
     */
    public List<Role> querys() {
        return this.roleMapper.selectAll();
    }
}
