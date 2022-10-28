package com.zf.sysservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zf.commonutils.R;
import com.zf.sysservice.entity.Employee;
import com.zf.sysservice.entity.dto.EmployeeQuery;
import com.zf.sysservice.entity.vo.EmployeeDetail;
import com.zf.sysservice.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 职工表 前端控制器
 * </p>
 *
 * @author ZF
 * @since 2022-10-25
 */
@RestController
@RequestMapping("/sysService/employee")
@Api(description = "员工管理")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @ApiOperation("添加员工")
    @PostMapping("add")
    public R add(@RequestBody Employee employee) {
        employeeService.save(employee);
        return R.success();
    }

    @ApiOperation("删除员工")
    @DeleteMapping("delete/{id}")
    public R delete(@PathVariable String id) {
        boolean b = employeeService.removeById(id);
        if (b) {
            return R.success();
        } else {
            return R.error();
        }
    }

    @ApiOperation("修改员工")
    @PostMapping("update")
    public R update(@RequestBody Employee employee) {
        employeeService.updateById(employee);
        return R.success();
    }

    @ApiOperation("分页条件查询员工")
    //有表单提交，用post
    @PostMapping("conditionList/{pageNo}/{pageSize}")
    public R conditionList(@PathVariable int pageNo, @PathVariable int pageSize,@RequestBody(required = false) EmployeeQuery employeeQuery) {
        Map<String, Object> map = employeeService.selectEmployeeDetailList(pageNo, pageSize, employeeQuery);
        return R.success().data(map);
    }

    @ApiOperation("根据id查询员工")
    @GetMapping("getInfoById/{id}")
    public R getInfoById(@PathVariable String id) {
        Employee employee = employeeService.getById(id);
        return R.success().data("employee", employee);
    }
}

