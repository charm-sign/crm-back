package com.zf.sysservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zf.commonutils.R;
import com.zf.sysservice.entity.Employee;
import com.zf.sysservice.entity.ExcelDown;
import com.zf.sysservice.entity.Role;
import com.zf.sysservice.entity.dto.EmployeeQuery;
import com.zf.sysservice.entity.vo.EmployeeDetail;
import com.zf.sysservice.service.EmployeeService;
import com.zf.sysservice.service.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

    @Autowired
    private RoleService roleService;

    @ApiOperation("添加员工(员工角色)")
    @PostMapping("add")
    public R add(@RequestBody Employee employee, @RequestParam(value = "ids", required = false) String[] roleIds) {
        employeeService.saveEmployeeWithRole(employee, roleIds);
        return R.success();
    }

    @ApiOperation("删除员工")
    @DeleteMapping("delete/{id}")
    public R delete(@PathVariable String id) {
        employeeService.removeEmployeeWithRoleById(id);
        return R.success();
    }

    @ApiOperation("修改员工")
    @PostMapping("update")
    public R update(@RequestBody Employee employee, @RequestParam(value = "ids", required = false) String[] roleIds) {
        employeeService.updateEmployeeById(employee, roleIds);
        return R.success();
    }

    @ApiOperation("分页条件查询员工")
    //有表单提交，用post
    @PostMapping("conditionList/{pageNo}/{pageSize}")
    public R conditionList(@PathVariable(required = false) int pageNo, @PathVariable(required = false) int pageSize, @RequestBody(required = false) EmployeeQuery employeeQuery) {
        Map<String, Object> map = employeeService.selectEmployeeDetailList(pageNo, pageSize, employeeQuery);
        return R.success().data(map);
    }

    @ApiOperation("根据id查询员工")
    @GetMapping("getInfoById/{employeeId}")
    public R getInfoById(@PathVariable String employeeId) {
        Employee employee = employeeService.getDetailById(employeeId);

        employee.setCheckPassword(employee.getPassword());
        List<Role> roleList = roleService.selectEmployeeWithRoleById(employeeId);
        return R.success().data("employee", employee).data("roleList", roleList);
    }

    @ApiOperation("批量删除员工(角色)")
    @PostMapping("deletedBatch")
    public R deletedBatch(@RequestParam(value = "employeeIds") List<String> employeeIds) {
        employeeService.removeEmployeeWithRoleByIds(employeeIds);
        return R.success();
    }

    @ApiOperation("导出Excel数据")
    @GetMapping("exportExcel")
    public R exportExcel(HttpServletResponse response, @RequestParam(value = "Ids") List<String> employeeIds) throws IOException {
        employeeService.exportExcel(response, employeeIds);
        return R.success();
    }

    @ApiOperation("下载Excel模板")
    @GetMapping("downExcel")
    public R downExcel(HttpServletResponse response) throws Exception {
        employeeService.downExcel(response);
        return R.success();
    }

    @ApiOperation("导入文件")
    @PostMapping("uploadExcel")
    public R uploadExcel(MultipartFile file) throws Exception {
        employeeService.importEmployeeData(file,employeeService); //直接手动注入，供监听器使用
        //判断返回集合是否为空
        return R.success();
    }

    @ApiOperation("查询所有客户")
    @GetMapping("getEmployee")
    public R getEmployee(){
        List<Employee> employeeList = employeeService.list(null);
        return R.success().data("employeeList",employeeList);
    }


}

