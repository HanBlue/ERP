package com.erp.controller;

import com.erp.auth.entity.UserInfo;
import com.erp.auth.utils.JwtUtils;
import com.erp.config.JwtProperties;
import com.erp.common.utils.CookieUtils;
import com.erp.pojo.User;
import com.erp.service.AuthService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@EnableConfigurationProperties(JwtProperties.class)
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private JwtProperties prop;

    /**
     * 登录授权
     *
     * @param
     * @return
     */
    @PostMapping("accredit")
    public ResponseEntity<String> accredit(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletRequest request, HttpServletResponse response) {

        String token = this.authService.accredit(username, password);
        if (StringUtils.isBlank(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        CookieUtils.setCookie(request, response, this.prop.getCookieName(), token, this.prop.getExpire() * 60);

        return ResponseEntity.ok(token);
    }

    /**
     * 验证用户信息
     *
     * @param token
     * @return
     */
    @GetMapping("verify")
    public ResponseEntity<UserInfo> verify(@CookieValue("LY_TOKEN")String token,HttpServletRequest request, HttpServletResponse response){
        try {
            //通过jwt工具类使用公钥解析jwt
            UserInfo user = JwtUtils.getInfoFromToken(token,this.prop.getPublicKey());

            if (user == null){
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
            //刷新jwt中有效时间
            token = JwtUtils.generateToken(user,this.prop.getPrivateKey(),this.prop.getExpire());
            //刷新cookie中的有效时间
            CookieUtils.setCookie(request,response,this.prop.getCookieName(),token,this.prop.getExpire() * 60);

            return ResponseEntity.ok(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }


}
