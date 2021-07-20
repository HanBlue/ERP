package com.erp.controller;


import com.erp.common.pojo.PageResult;
import com.erp.pojo.Role;
import com.erp.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class RoleController {
    @Autowired
    private RoleService roleService;

    /**
     * 获取角色列表
     * @param key
     * @param page
     * @param rows
     * @param sortBy
     * @param desc
     * @return
     */
    @GetMapping("/role/page")
    public ResponseEntity<PageResult<Role>> queryRolesBypage(
            @RequestParam(value = "key",required = false)String key,
            @RequestParam(value = "page",defaultValue = "1")Integer page,
            @RequestParam(value = "rows", defaultValue = "5")Integer rows,
            @RequestParam(value = "sortBy",required = false)String sortBy,
            @RequestParam(value = "desc",required = false)Boolean desc
    ){
        PageResult<Role> result=this.roleService.queryRolesList(key,page,rows,sortBy,desc);
        if(result ==null|| CollectionUtils.isEmpty(result.getItems())){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }

    /**
     * 拿权限名称，不分页
     * @param
     * @return
     */
    @GetMapping("/role/list")
    public ResponseEntity<List<Role>> queryRolesById() {
        try {
            // 执行查询操作
            List<Role> categoryList = this.roleService.querys();
            return ResponseEntity.ok(categoryList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 响应500
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    /**
     * 根据id查询权限
     * @param id
     * @return
     */
    @GetMapping("/role/{id}")
    public ResponseEntity<List<Role>> queryById(@PathVariable("id") Long id) {
        List<Role> roles = this.roleService.queryById(id);
        if (roles == null || roles.size() < 1) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(roles);
    }

    /**
     * 删除权限
     * @param id
     * @return
     */
    @DeleteMapping("/role")
    public ResponseEntity<Void> deleteRole(@RequestParam("id") Long id) {
        this.roleService.deleteRole(id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
