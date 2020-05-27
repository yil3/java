package com.ihrm.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ihrm.entity.Result;
import com.ihrm.entity.ResultCode;
import com.ihrm.entity.system.Role;
import com.ihrm.service.RoleService;
import com.ihrm.utils.ListUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping("/sys")
public class RoleController {
    @Resource
    private RoleService roleService;


    // 分配权限
    @PutMapping("/role/assignPerm")
    public Result assignPerm(@RequestBody Map<String, Object> map) {
        String roleId = (String) map.get("id");
        roleService.deleteRolePermission(Long.valueOf(roleId));
        List<String> permIds = ListUtils.castList(map.get("permIds"), String.class);
        roleService.assignPerms(roleId, permIds);
        return Result.SUCCESS();
    }

    // 删除权限
    // @PutMapping("/role/deletePerm")
    // public Result deletePerm(@RequestBody Map<String,Object> map){
    //   String roleId = (String) map.get("id");
    //   List<String> permIds = ListUtils.castList(map.get("permIds"), String.class);
    //   roleService.deletePerms(roleId, permIds);
    //   return Result.SUCCESS();
    // }

    @PostMapping("/role")
    public Result save(@RequestBody Role role) {
        role.setId(1L);
        roleService.save(role);
        return Result.SUCCESS();
    }

    @PutMapping("/role")
    public Result updateById(@RequestBody Role role) {
        roleService.updateById(role);
        return Result.SUCCESS();
    }

    @DeleteMapping("/role/{id}")
    public Result deleteById(@PathVariable("id") long id) {
        roleService.deleteById(id);
        return Result.SUCCESS();
    }

    @GetMapping("/role/{id}")
    public Result findById(@PathVariable("id") long id) {
        Role role = roleService.findById(id);
        Set<String> permissionIds = roleService.findPermissionsByRoleId(id);
        role.setPermissionIds(permissionIds);
        return new Result(ResultCode.SUCCESS, role);
    }

    @GetMapping("/role/all")
    public Result findAll(Long companyId) {
        companyId = companyId == null ? 1L : companyId;
        List<Role> roleList = roleService.findAll(companyId);
        return new Result(ResultCode.SUCCESS, roleList);
    }

    @GetMapping("/role")
    public Result findByPage(int page, int size, @RequestParam Map<String, Object> map) {
        long companyId = map.containsKey("companyId") ? (long) map.get("companyId") : 1L;

        Page<Role> search = roleService.findByPage(companyId, page, size);
        List<Role> roles = search.getRecords();
        long total = search.getTotal();

        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("roles", roles);
        resultMap.put("total", total);
        return new Result(ResultCode.SUCCESS, resultMap);
    }
}
