package com.offcn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
//允许当前应用作为Eureka服务器来启用
@EnableEurekaServer
public class AppStartEurekaServer001 {
    public static void main(String[] args) {
        SpringApplication.run(AppStartEurekaServer001.class,args);
    }
}
