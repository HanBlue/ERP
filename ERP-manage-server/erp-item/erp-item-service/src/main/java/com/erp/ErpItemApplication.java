package com.erp;

import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.erp.item.mapper")
public class ErpItemApplication {
    public static void main(String[] args) {
        SpringApplication.run(ErpItemApplication.class);

    }
}
