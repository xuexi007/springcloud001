package com.offcn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
public class AppStartUserWeb001 {
    public static void main(String[] args) {
        SpringApplication.run(AppStartUserWeb001.class,args);
    }

    //创建一个RestTemplate
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
