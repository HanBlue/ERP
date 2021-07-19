package com.erp.item.controller;

import com.erp.item.pojo.SpecGroup;
import com.erp.item.pojo.SpecParam;
import com.erp.item.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("spec")
public class SpecificationController {
    @Autowired
    private SpecificationService specificationService;

    /**
     * 根据分类id查询规格参数组
     * @param cid
     * @return
     */
    @GetMapping("groups/{cid}")
    public ResponseEntity<List<SpecGroup>> queryGroupsByCid(@PathVariable("cid") Long cid){
        List<SpecGroup> groups = this.specificationService.queryGroupsBycId(cid);
        if (CollectionUtils.isEmpty(groups)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(groups);
    }
    /**
     * 新增规格分组
     * @param group 规格分组对象
     * @return 200
     */
    @PostMapping("group")
    public ResponseEntity<Void> saveGroup(SpecGroup group) {
        this.specificationService.saveGroup(group);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    /**
     * 删除规格分组
     * @param id 规格表id
     * @return
     */
    @DeleteMapping("group/{id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable("id") Long id) {
        this.specificationService.deleteGroup(id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    /**
     * 修改规格分组
     * @group 规格分组对象
     * @return
     */
    @PutMapping("group")
    public ResponseEntity<Void> updateGroup(SpecGroup group) {
        this.specificationService.updateGroup(group);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    /**
     * 根据条件查询规格参数
     * @param gid
     * @return
     */
    @GetMapping("params")
    public ResponseEntity<List<SpecParam>> queryParams(@RequestParam(value = "gid",required = false)Long gid){
        List<SpecParam> params = this.specificationService.queryParams(gid);
        if (CollectionUtils.isEmpty(params)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(params);
    }
    /**
     * 新增规格
     * @param param 规格属性对象
     * @return 200
     */
    @PostMapping("param")
    public ResponseEntity<Void> saveParam(SpecParam param) {
        this.specificationService.saveParams(param);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * 删除规格
     * @param id 规格表id
     * @return
     */
    @DeleteMapping("param/{id}")
    public ResponseEntity<Void> deleteParam(@PathVariable("id") Long id) {
        this.specificationService.deleteParam(id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * 修改规格
     * @param param
     * @return
     */
    @PutMapping("param")
    public ResponseEntity<Void> updateParam(SpecParam param) {
        this.specificationService.updateParams(param);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
