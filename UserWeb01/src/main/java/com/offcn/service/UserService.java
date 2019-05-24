package com.offcn.service;

import com.offcn.po.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    //获取全部用户数据

    public Map<String,Object> getUserAll();

    //新增用户
    public void createUser(User user);
    //修改

    public void udateUser(Long id,User user);

    //删除
    public void deleteUser(Long id);

    //根据用户id获取用户信息
    public User getUserById(Long id);
}
