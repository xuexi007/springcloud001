package com.offcn.service.impl;

import com.offcn.po.User;
import com.offcn.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
@Service
public class UserServiceImpl implements UserService {
    @Override
    public Map<String, Object> getUserAll() {
        Map<String,Object> map=new HashMap<>();
        map.put("list",new ArrayList());
        map.put("ProviderVersion","获取远程服务失败，发生熔断");
        return map;
    }

    @Override
    public void createUser(User user) {
        System.out.println("创建用户发生熔断");
    }

    @Override
    public void udateUser(Long id, User user) {
        System.out.println("更新用户发生熔断");
    }

    @Override
    public void deleteUser(Long id) {
        System.out.println("删除用户发生熔断");
    }

    @Override
    public User getUserById(Long id) {
        System.out.println("获取用户发生熔断");
        return new User();
    }
}
