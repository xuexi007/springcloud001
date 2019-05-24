package com.offcn.service.impl;

import com.offcn.po.User;
import com.offcn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
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
      /*  List<ServiceInstance> serviceInstanceList = discoveryClient.getInstances("USERPROVIDER");

        //获取第一个服务提供者
        ServiceInstance serviceInstance = serviceInstanceList.get(0);

        //获取服务提供者对应的ip地址
        String ip = serviceInstance.getHost();
        int port = serviceInstance.getPort();
        //拼接远程服务接口实际地址
        String url="http://"+ip+":"+port+"/user";
       return  url;*/
        ServiceInstance serviceInstance = loadBalancerClient.choose("USERPROVIDER");
        String ip = serviceInstance.getHost();
        int port = serviceInstance.getPort();
        //拼接远程服务接口实际地址
        String url="http://"+ip+":"+port+"/user";
        return  url;
    }
    @Override
    public Map<String,Object> getUserAll() {
        return restTemplate.getForObject(getProviderServer()+"/all", Map.class);
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
