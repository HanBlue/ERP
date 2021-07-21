package com.erp.controller;

import com.erp.common.pojo.PageResult;
import com.erp.pojo.User;
import com.erp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 获取用户列表
     * @param key
     * @param page
     * @param rows
     * @param sortBy
     * @param desc
     * @return
     */
    @GetMapping("page")
    public ResponseEntity<PageResult<User>> queryBrandsBypage(
            @RequestParam(value = "key",required = false)String key,
            @RequestParam(value = "page",defaultValue = "1")Integer page,
            @RequestParam(value = "rows", defaultValue = "5")Integer rows,
            @RequestParam(value = "sortBy",required = false)String sortBy,
            @RequestParam(value = "desc",required = false)Boolean desc
    ){
        PageResult<User> result=this.userService.queryUsersList(key,page,rows,sortBy,desc);
        if(result ==null|| CollectionUtils.isEmpty(result.getItems())){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(result);
    }
    /**
     * 通过id查询
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<List<User>> queryByBrandId(@PathVariable("id") Long id) {
        List<User> users = this.userService.queryByBrandId(id);
        if (users == null || users.size() < 1) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(users);
    }
    /**
     * 删除
     * @param id
     * @return 200
     */
    @DeleteMapping
    public ResponseEntity<Void> deleteUser(@RequestParam("id") Long id) {
        this.userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    /**
     * 更新用户信息
     * @param user
     * @return
     */
    @PutMapping("/update")
    public ResponseEntity<Void> updateUser(@RequestBody User user){
        this.userService.updateUser(user);
        return ResponseEntity.noContent().build();
    }


    /**
     * 校验数据是否可用
     * @param data
     * @return
     */
    @GetMapping("check/{data}")
    public ResponseEntity<Boolean> checkUserData(@PathVariable("data") String data) {

        Boolean boo = this.userService.checkData(data);
        if (boo == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(boo);
    }
    /**
     * 注册
     * @param user
     * @return
     */
    @PostMapping("register")
    public ResponseEntity<Void> register(@Valid User user) {
        this.userService.register(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    /**
     * 根据用户名和密码查询用户
     * @param username
     * @param password
     * @return
     */
    @GetMapping("query")
    public ResponseEntity<User> queryUser(@RequestParam("username")String username,@RequestParam("password")String password){
        User user = this.userService.queryUser(username,password);
        if (user == null){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(user);
    }
}
