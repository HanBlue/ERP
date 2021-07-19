package com.erp;

import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.erp.mapper")
public class ErpUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(ErpUserApplication.class, args);
    }
}
