package com.ihrm.controller;

import com.ihrm.entity.Result;
import com.ihrm.entity.ResultCode;
import com.ihrm.entity.system.Permission;
import com.ihrm.exception.CommonException;
import com.ihrm.service.PermissionService;
import com.ihrm.utils.BeanMapUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/sys")
public class PermissionController {

  @Resource
  private PermissionService permissionService;

  @GetMapping("/permission")
  public Result findAll(@RequestParam Map<String, Object> map) {
    List<Permission> permissionList = permissionService.findAll(map);
    return new Result(ResultCode.SUCCESS,permissionList);
  }

  @GetMapping("/permission/{id}")
  public Result findById(@PathVariable("id") long id) {
    try {
      Map<String, Object> map = permissionService.findById(id);
      return new Result(ResultCode.SUCCESS,map);
    } catch (Exception e) {
      return new Result(e.getMessage());
    }

  }

  @PostMapping("/permission")
  public Result save(@RequestBody Map<String,Object> map) throws Exception {
    permissionService.save(map);
    return new Result(ResultCode.SUCCESS);
  }

  @DeleteMapping("/permission/{id}")
  public Result deleteById(@PathVariable("id") long id) throws CommonException {
    permissionService.deleteById(id);
    return new Result(ResultCode.SUCCESS);
  }

  @PutMapping("/permission/{id}")
  public Result updateById(@PathVariable("id") long id, Map<String,Object> map) throws Exception {
    map.put("id", id);
    permissionService.updateById(map);
    return new Result(ResultCode.SUCCESS);
  }



}
