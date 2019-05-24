package com.offcn.service.impl;

import com.offcn.dao.UserDao;
import com.offcn.po.User;
import com.offcn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;


    @Override
    public Map<String,Object> getUserAll() {
        Map<String,Object> map=new HashMap<>();
        List<User> list = userDao.findAll();
        map.put("list",list);
        return map;

    }

    @Override
    public void createUser(User user) {
     userDao.save(user);
    }

    @Override
    public void udateUser( User user) {

     userDao.saveAndFlush(user);
    }

    @Override
    public void deleteUser(Long id) {
      userDao.deleteById(id);
    }

    @Override
    public User getUserById(Long id) {
        return userDao.findById(id).get();
    }
}
