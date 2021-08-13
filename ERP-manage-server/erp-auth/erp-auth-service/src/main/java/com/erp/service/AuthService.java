package com.erp.service;

import com.erp.auth.entity.UserInfo;
import com.erp.auth.utils.JwtUtils;
import com.erp.config.JwtProperties;
import com.erp.mapper.UserMapper;
import com.erp.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JwtProperties jwtProperties;

    public String accredit(String username, String password) {
        try {
        //1.根据用户名和密码查询
            User record = new User();
            record.setUsername(username);
            User user = this.userMapper.selectOne(record);
        //2.判断user
        if (user == null||!user.getPassword().equals(password)){
            return null;
        }
            //3.jwtUtils生成jwt类型的token
            UserInfo userInfo = new UserInfo();
            userInfo.setId(user.getId());
            userInfo.setUsername(user.getUsername());
            return JwtUtils.generateToken(userInfo,this.jwtProperties.getPrivateKey(),this.jwtProperties.getExpire());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
