package com.zf.sysservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zf.commonutils.R;
import com.zf.sysservice.entity.*;
import com.zf.sysservice.service.PermissionService;
import com.zf.sysservice.service.RolePermissionService;
import com.zf.sysservice.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author ZF
 * @since 2022-10-28
 */
@RestController
@RequestMapping("/sysService/role")
@Api(description = "角色管理")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private RolePermissionService rolePermissionService;

    @Autowired
    PermissionService permissionService;

    @ApiOperation("添加角色(权限)")
    @PostMapping("add")
    @PreAuthorize("hasAuthority('role:addOrUpdate')")
    public R add(@RequestBody Role role, @RequestParam(value = "ids", required = false) String[] permissionIds) {
//        System.out.println(permissionIds);
        roleService.saveRoleWithPermission(role, permissionIds);
        return R.success();
    }

    @ApiOperation("删除角色")
    @DeleteMapping("delete/{id}")
    @PreAuthorize("hasAuthority('role:delete')")
    public R delete(@PathVariable String id) {
        roleService.removeRoleWithPermissionById(id);
        return R.success();
    }


    @ApiOperation("修改角色")
    @PostMapping("update")
    @PreAuthorize("hasAuthority('role:addOrUpdate')")
    public R update(@RequestBody Role role, @RequestParam(value = "ids", required = false) String[] permissionIds) {
        roleService.updateRoleById(role, permissionIds);
        return R.success();
    }

    @ApiOperation("分页查询角色列表")
    @GetMapping("list/{pageNo}/{pageSize}")
    @PreAuthorize("hasAuthority('role:list')")
    public R list(@PathVariable int pageNo, @PathVariable int pageSize) {
        Page<Role> page = new Page<>(pageNo, pageSize);
        LambdaQueryWrapper<Role> lqw = new LambdaQueryWrapper<>();
        lqw.orderByDesc(Role::getCreateTime);
        IPage<Role> page1 = roleService.page(page, lqw);
        long total = page1.getTotal();
        List<Role> roleList = page1.getRecords();
        return R.success().data("roleList", roleList).data("total", total);
    }

    @ApiOperation("根据id查询角色信息(权限)")
    @GetMapping("getInfoById/{id}")
    public R getInfoById(@PathVariable String id) {
        Role role = roleService.getById(id);
        List<Permission> permissionList = permissionService.selectRoleWithPermissionById(id);
        return R.success().data("role", role).data("permissionList", permissionList);
    }

    @ApiOperation("查询角色列表")
    @GetMapping("getRoleList")
    public R getRoleList() {
        List<Role> roleList = roleService.list(null);
        return R.success().data("roleList", roleList);
    }
}

