package com.erp.api;

import com.erp.pojo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface UserApi {
    @GetMapping("query")
    public User queryUser(
            @RequestParam("username") String username,
            @RequestParam(value = "password") String password
    );
}
