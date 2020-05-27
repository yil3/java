package com.ihrm.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ihrm.entity.Result;
import com.ihrm.entity.ResultCode;
import com.ihrm.entity.system.Role;
import com.ihrm.entity.system.RolePermission;
import com.ihrm.entity.system.User;
import com.ihrm.entity.system.UserRole;
import com.ihrm.exception.CommonException;
import com.ihrm.service.PermissionService;
import com.ihrm.service.RoleService;
import com.ihrm.service.UserService;
import com.ihrm.utils.JwtUtils;
import com.ihrm.utils.ListUtils;
import com.ihrm.utils.PermissionConstants;
import io.jsonwebtoken.Claims;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;


@RestController
@CrossOrigin
@RequestMapping("/sys")
public class UserController {
    @Resource
    private UserService userService;

    @Resource
    private RoleService roleService;

    @Resource
    private PermissionService permissionService;


    @PostMapping("/user")
    public Result save(@RequestBody User user) {
        userService.save(user);
        return Result.SUCCESS();
    }

    // 分配角色
    @PutMapping("/user/assignRoles")
    public Result assignRoles(@RequestBody Map<String, Object> map) {
        String userId = (String) map.get("id");

        List<String> roleIds = map.containsKey("roleIds") ?
                ListUtils.castList(map.get("roleIds"), String.class) : null;

        userService.deleteUserRole(Long.valueOf(userId));

        if (roleIds == null) return Result.SUCCESS();

        userService.assignRoles(userId, roleIds);
        return Result.SUCCESS();
    }

    // 删除角色
    // @PutMapping("/user/deleteRoles")
    // public Result deleteRoles(@RequestBody Map<String,Object> map){
    //   String userId = (String) map.get("id");
    //   List<String> roleIds = ListUtils.castList(map.get("roleIds"), String.class);
    //   userRoleService.deleteUserRole(userId, roleIds);
    //   return Result.SUCCESS();
    // }

    @GetMapping("/user")
    public Result findByPage(int page, int size, @RequestParam Map<String, Object> map) {
        map.put("companyId", 1L);
        Page<User> userPage = userService.findByPage(map, page, size);
        long total = userPage.getTotal();
        long pages = userPage.getPages();
        List<User> userList = userPage.getRecords();
        HashMap<String, Object> resultMap = new HashMap<>();
        resultMap.put("total", total);
        resultMap.put("pages", pages);
        resultMap.put("userList", userList);

        return Result.SUCCESS(resultMap);
    }

    @GetMapping("/user/{id}")
    public Result findById(@PathVariable("id") Long id) {
        User user = userService.findById(id);
        Set<String> roles = userService.findRolesByUserId(id);
        user.setRoleIds(roles);
        System.out.println(user.getRoleIds());
        return Result.SUCCESS(user);
    }

    @PutMapping("/user")
    public Result update(@RequestBody User user) {
        userService.updateById(user);
        return Result.SUCCESS();
    }

    @DeleteMapping("/user/{id}")
    public Result deleteById(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return Result.SUCCESS();
    }

    /**
     * 用户登录
     */
    @PostMapping("/login")
    public Result login(@RequestBody Map<String, String> loginMap) {
        String mobile = loginMap.get("mobile");
        String password = loginMap.get("password");
        User user = userService.findByMobile(mobile);
        if (user == null || !user.getPassword().equals(password)) return Result.ERROR("用户名或者密码错误!");

        HashMap<String, Object> map = new HashMap<>();
        map.put("companyId", user.getCompanyId());
        map.put("companyName", user.getCompanyName());

        String token = JwtUtils.create(user.getId(), user.getUsername(), map);
        return Result.SUCCESS(token);
    }

    /**
     * 返回用户数据
     */

    @PostMapping("/profile")
    public Result profile(HttpServletRequest request) {
        String authorization = request.getHeader("Authorization");
        if (StringUtils.isEmpty(authorization)) return Result.FAIL();

        String token = authorization.replace("Bearer ", "");
        Claims claims = JwtUtils.parse(token);
        String userId = claims.getId();  //token中获取用户id

        User user = userService.findById(Long.parseLong(userId));  // 根据id查询用户信息
        HashMap<String, Object> map = new HashMap<>();

        Set<String> roleIds = userService.findRolesByUserId(Long.valueOf(userId));  // 查询用户所有角色信息

        HashMap<String, Object> roles = new HashMap<>();


        HashSet<String> api = new HashSet<>();   // 创建权限分类集合
        HashSet<String> menu = new HashSet<>();
        HashSet<String> point = new HashSet<>();


        roleIds.stream().map(Long::valueOf).forEach(e -> {
            Set<String> permissionIds = roleService.findPermissionsByRoleId(e);  // 获取用户每个角色下拥有的权限


            permissionIds.stream().map(Long::valueOf)
                    .forEach(p -> {

                        try {
                            Map<String, Object> permission = permissionService.findById(p);
                            Integer type = (Integer) permission.get("type");   // 根据权限type类型分类 添加到Set集合
                            switch (type) {
                                case PermissionConstants.PY_MENU:
                                    menu.add((String) permission.get("code"));
                                    break;
                                case PermissionConstants.PY_API:
                                    api.add((String) permission.get("code"));
                                    break;
                                case PermissionConstants.PY_POINT:
                                    point.add((String) permission.get("code"));
                                    break;
                            }
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }

                    });
        });
        roles.put("apis", api);    // 分类好的权限集合 添加到返回Map
        roles.put("menus", menu);
        roles.put("points", point);


        map.put("mobile", user.getMobile());
        map.put("username", user.getUsername());
        map.put("company", user.getCompanyName());
        map.put("roles", roles);

        return Result.SUCCESS(map);
    }
}
