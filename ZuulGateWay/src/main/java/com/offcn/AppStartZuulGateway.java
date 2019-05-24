package com.offcn;

import com.offcn.filter.AccessFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

@EnableZuulProxy
@SpringCloudApplication//@SpringBootAapplication @EnableDiscoryClient @EnableCircuiBreaker
public class AppStartZuulGateway {
    public static void main(String[] args) {
        SpringApplication.run(AppStartZuulGateway.class,args);
    }

    @Bean
    public AccessFilter getAccessFilter(){
        return new AccessFilter();
    }
}
