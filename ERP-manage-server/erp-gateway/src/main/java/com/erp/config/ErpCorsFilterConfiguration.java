package com.erp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


@Configuration
public class ErpCorsFilterConfiguration {
    @Bean
    public CorsFilter corsFilter(){
        //1.添加CORS配置信息
        CorsConfiguration configration = new CorsConfiguration();
        //1) 允许的域,不要写*，否则cookie就无法使用了
        configration.addAllowedOrigin("http://www.hanblue.top:9002");
        configration.addAllowedOrigin("http://manage.hanblue.top:9002");
        configration.addAllowedOrigin("http://www.hanblue.top");
        configration.addAllowedOrigin("http://manage.hanblue.top");
        //2) 是否发送Cookie信息
        configration.setAllowCredentials(true);
        //3) 允许的请求方式
        configration.addAllowedMethod("OPTIONS");
        configration.addAllowedMethod("HEAD");
        configration.addAllowedMethod("GET");
        configration.addAllowedMethod("PUT");
        configration.addAllowedMethod("POST");
        configration.addAllowedMethod("DELETE");
        configration.addAllowedMethod("PATCH");
        // 4）允许的头信息
        configration.addAllowedHeader("*");

        //2.添加映射路径，我们拦截一切请求
        UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
        configurationSource.registerCorsConfiguration("/**", configration);

        //3.返回新的CorsFilter.
        return new CorsFilter(configurationSource);
    }
    }

