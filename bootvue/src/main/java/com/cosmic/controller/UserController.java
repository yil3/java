package com.cosmic.controller;

import com.cosmic.entity.User;
import com.cosmic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/findAll")
    public Map<String,Object> findAll(){
        HashMap<String , Object> map = new HashMap<>();
        try {
            List<User> user = userService.findAll();
            map.put("success", true);
            map.put("user",user);
        }catch (Exception e){
            map.put("success", false);
            map.put("message", "查询失败!");
        }
        return map;
    }

    @PostMapping("/save")
    public Map<String,Object> save(@RequestBody User user){
        HashMap<String , Object> map = new HashMap<>();
        try {
            userService.save(user);
            map.put("success", true);
        }catch (Exception e){
            map.put("success", false);
            map.put("message", "添加用户失败!");
        }
        return map;
    }

    @DeleteMapping("/deleteById/{id}")
    public Map<String, Object> deleteById(@PathVariable int id){
        Map<String, Object> map = new HashMap<>();
        try {
            userService.deleteById(id);
            map.put("success", true);
        }catch (Exception e){
            map.put("success", false);
            map.put("message", "删除用户失败!");
        }
        return map;
    }

    @GetMapping("/findByName/{name}")
    public Map<String, Object> findByName(@PathVariable String name){
        Map<String, Object> map = new HashMap<>();
        try {
            List<User> user = userService.findByName(name);
            if (user.size()>0){
                map.put("success", true);
                map.put("user", user);
            }else {
                map.put("success", false);
            }
        }catch (Exception e){
            map.put("success", false);
            map.put("message", "无匹配数据!");
        }
        return map;
    }

    @GetMapping("/findById/{id}")
    public Map<String, Object> findById(@PathVariable int id){
        Map<String, Object> map = new HashMap<>();
        try {
            User user = userService.findById(id);
            map.put("success", true);
            map.put("user", user);
        }catch (Exception e){
            map.put("success", false);
            map.put("message", "用户不存在!");
        }
        return map;
    }

    @PutMapping("/update")
    public Map<String, Object> update(@RequestBody User user){
        Map<String, Object> map = new HashMap<>();
        try {
            userService.update(user);
            map.put("success", true);
        }catch (Exception e){
            map.put("success", false);
            map.put("message", "修改失败!");
        }
        return map;
    }


}
