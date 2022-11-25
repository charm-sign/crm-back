package com.zf.sysservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zf.commonutils.R;
import com.zf.sysservice.entity.Permission;
import com.zf.sysservice.service.PermissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 权限表 前端控制器
 * </p>
 *
 * @author ZF
 * @since 2022-10-29
 */
@Slf4j
@Api(description = "权限管理")
@RestController
@RequestMapping("/sysService/permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;
    @ApiOperation("分页查询权限列表数据")
    @GetMapping("list/{pageNo}/{pageSize}")
    @PreAuthorize("hasAuthority('permission:list')")
    public R list(@PathVariable int pageNo, @PathVariable int pageSize, HttpServletRequest request){
        String token2 = request.getHeader("X-Token");
        log.info("token2="+token2);
        Page<Permission> page = new Page<>(pageNo, pageSize);
        LambdaQueryWrapper<Permission> lqw = new LambdaQueryWrapper<>();
        lqw.orderByDesc(Permission::getCreateTime);
        IPage<Permission> page1 = permissionService.page(page, lqw);
        long total = page1.getTotal();
        List<Permission> permissionList = page1.getRecords();
        return R.success().data("permissionList",permissionList).data("total",total);
    }

    @ApiOperation("删除权限")
    @DeleteMapping("delete/{id}")
    @PreAuthorize("hasAuthority('permission:delete')")
    public R delete(@PathVariable String id){
        permissionService.removeById(id);
        return R.success();
    }

    @ApiOperation("获取权限列表")
    @GetMapping("getPermissionList")
    public R getPermissionList(){
        List<Permission> list = permissionService.list(null);
        return R.success().data("list",list);
    }
    @ApiOperation("根据id获取当前用户权限")
    @GetMapping("getPermsByEmployeeId/{id}")
    public List<String> getPermsByEmployeeId(@PathVariable String id){
        List<String> perms = permissionService.selectPermsByEmployeeId(id);
        return perms;
    }

}

