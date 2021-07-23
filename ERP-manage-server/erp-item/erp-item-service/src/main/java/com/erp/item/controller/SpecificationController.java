package com.erp.item.controller;

import com.erp.item.pojo.SpecGroup;
import com.erp.item.pojo.SpecParam;
import com.erp.item.service.SpecificationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags="规格参数")
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
    @ApiOperation(value = "根据分类id查询规格参数组")
    @ApiImplicitParam(name = "cid",value = "分类id",dataType = "Long")
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
    @ApiOperation(value = "新增规格分组")
    @ApiImplicitParam(name = "group",value = "规格分组对象",dataType = "SpecGroup")
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
    @ApiOperation(value = "删除规格分组")
    @ApiImplicitParam(name = "id",value = "规格表id",dataType = "Long")
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
    @ApiOperation(value = "修改规格分组")
    @ApiImplicitParam(name = "group",value = "规格分组对象",dataType = "SpecGroup")
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
    @ApiOperation(value = "根据条件查询规格参数")
    @ApiImplicitParam(name = "gid",value = "规格分组id",dataType = "Long")
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
    @ApiOperation(value = "新增规格")
    @ApiImplicitParam(name = "group",value = "规格对象",dataType = "SpecGroup")
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
    @ApiOperation(value = "删除规格")
    @ApiImplicitParam(name = "id",value = "规格表id",dataType = "Long")
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
    @ApiOperation(value = "修改规格")
    @ApiImplicitParam(name = "param",value = "规格对象",dataType = "SpecParam")
    @PutMapping("param")
    public ResponseEntity<Void> updateParam(SpecParam param) {
        this.specificationService.updateParams(param);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
