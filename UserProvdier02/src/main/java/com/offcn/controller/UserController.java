package com.offcn.controller;

import com.offcn.po.User;
import com.offcn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    //获取全部用户
    @GetMapping("/all")
    public Map<String,Object> findAll(){
        Map<String,Object> map= userService.getUserAll();


        map.put("ProviderVersion","UserProvider002:0.0.1V");
        //模拟执行延迟
      /*  try {
            Thread.sleep(new Random().nextInt(2000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        return map;
    }

    //保存用户
    @PostMapping("/save")
    public String createUser(@RequestBody User user){

        try {
            System.out.println("保存用户:"+user);
            userService.createUser(user);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    //修改用户

    @PutMapping("/update/{id}")
    public String updateUser(@PathVariable("id") Long id, @RequestBody User user){
        try {
            userService.udateUser(id,user);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }
    //删除用户

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        try {
            userService.deleteUser(id);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
    }

    //查询用户

    @GetMapping("/find/{id}")
    public User findUserById(@PathVariable("id") Long id){
        User user=null;

        try {
            user= userService.getUserById(id);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
