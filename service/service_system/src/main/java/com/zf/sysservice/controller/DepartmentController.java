package com.zf.sysservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zf.commonutils.R;
import com.zf.servicebase.exceptionhandler.CrmException;
import com.zf.sysservice.entity.Department;
import com.zf.sysservice.service.DepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 部门表 前端控制器
 * </p>
 *
 * @author ZF
 * @since 2022-10-27
 */
@RestController
@RequestMapping("/sysService/department")
@Api(description = "部门管理")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @ApiOperation("添加部门")
    @PostMapping("add")
    public R add(@RequestBody Department department) {
        departmentService.save(department);
        return R.success();
    }

    @ApiOperation("删除部门")
    @DeleteMapping("delete/{id}")
    public R delete(@PathVariable String id) {
        boolean b = departmentService.removeById(id);
        if (b) {
            return R.success();
        } else {
            return R.error();
        }
    }

    @ApiOperation("修改部门")
    @PostMapping("update")
    public R update(@RequestBody Department department) {
        departmentService.updateById(department);
        return R.success();
    }

    @ApiOperation("分页查询部门列表")
    @GetMapping("list/{pageNo}/{pageSize}")
    public R list(@PathVariable int pageNo, @PathVariable int pageSize) {
        Page<Department> page = new Page<>(pageNo, pageSize);
        LambdaQueryWrapper<Department> lqw = new LambdaQueryWrapper<>();
        lqw.orderByDesc(Department::getCreateTime);
        IPage<Department> page1 = departmentService.page(page, lqw);
        long total = page1.getTotal();
        List<Department> departmentList = page1.getRecords();
        return R.success().data("departmentList", departmentList).data("total", total);
    }

    @ApiOperation("根据id查询部门信息")
    @GetMapping("getInfoById/{id}")
    public R getInfoById(@PathVariable String id) {
        Department department = departmentService.getById(id);
        return R.success().data("department", department);
    }

    @ApiOperation("查询所有部门")
    @GetMapping("lists")
    public R lists() {
        List<Department> lists = departmentService.list(null);
        return R.success().data("lists", lists);
    }
}

