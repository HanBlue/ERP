package com.erp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ErpUploadApplication {
    public static void main(String[] args) {
        SpringApplication.run(ErpUploadApplication.class);
    }
}
