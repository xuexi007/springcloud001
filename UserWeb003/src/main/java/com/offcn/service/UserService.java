package com.offcn.service;

import com.offcn.config.FeignConfig;
import com.offcn.po.User;
import com.offcn.service.impl.UserServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
@FeignClient(value = "USERPROVIDER",configuration = FeignConfig.class,fallback = UserServiceImpl.class)
public interface UserService {
    //获取全部用户数据

    @GetMapping("/user/all")
    public Map<String,Object> getUserAll();

    //新增用户
    @PostMapping("/user/save")
    public void createUser(@RequestBody User user);
    //修改

    @PutMapping("/user/update/{id}")
    public void udateUser(@RequestParam("id")Long id,@RequestBody User user);

    //删除
    @DeleteMapping("/user/delete/{id}")
    public void deleteUser(@RequestParam("id")Long id);

    //根据用户id获取用户信息
    @GetMapping("/user/find/{id}")
    public User getUserById(@PathVariable("id") Long id);
}
