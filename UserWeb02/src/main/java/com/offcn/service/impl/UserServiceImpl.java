package com.offcn.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.offcn.po.User;
import com.offcn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    //注入远程调用工具
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    //获取发布好的远程服务提供者
    public String getProviderServer(){

        String url="http://USERPROVIDER/user";
        return  url;
    }
    @Override
    @HystrixCommand(fallbackMethod = "getUserAllHystrix")
    public Map<String,Object> getUserAll() {
        long beginTime = System.currentTimeMillis();

        Map<String,Object> map = restTemplate.getForObject(getProviderServer() + "/all", Map.class);

        long endTime=System.currentTimeMillis();
        System.out.println("程序执行时间:"+(endTime-beginTime));
        return map;

    }
    //熔断方法
    public Map<String,Object> getUserAllHystrix() {
        Map<String,Object> map=new HashMap<>();
        map.put("list",new ArrayList());
        map.put("ProviderVersion","获取远程服务失败，发生熔断");
        return map;
    }

    @Override
    public void createUser(User user) {
        System.out.println("调用远程服务:"+user);
        String result = restTemplate.postForObject(getProviderServer() + "/save", user, String.class);
        System.out.println("创建用户返回结果:"+result);
    }

    @Override
    public void udateUser(Long id, User user) {
      restTemplate.put(getProviderServer()+"/update/"+id,user);
    }

    @Override
    public void deleteUser(Long id) {
      restTemplate.delete(getProviderServer()+"/delete/"+id);
    }

    @Override
    public User getUserById(Long id) {
        return restTemplate.getForObject(getProviderServer()+"/find/"+id,User.class);
    }
}
