package com.zf.sysservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zf.commonutils.JwtUtils;
import com.zf.commonutils.R;
import com.zf.sysservice.entity.Employee;
import com.zf.sysservice.entity.dto.Login;
import com.zf.sysservice.entity.vo.EmployeeDetail;
import com.zf.sysservice.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @ClassName: SysLoginController
 * @Description: 用户登录
 * @Author: ZF
 * @date: 2022/10/25 14:09
 */
@Slf4j
@Api(description = "登录管理")
@RestController
@RequestMapping("/sysService/user")
public class SysLoginController {
    @Autowired
    private EmployeeService employeeService;

    //login
    @ApiOperation("登录")
    @PostMapping("login")
    public R login(@RequestBody Login login) {
        String name = login.getUsername();
        String password = login.getPassword();
        LambdaQueryWrapper<Employee> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Employee::getName, name)
                .eq(Employee::getPassword, password);
        Employee employee1 = employeeService.getOne(lqw);
        if (ObjectUtils.isEmpty(employee1)) {
            return R.error().Message("用户名或密码错误！");
        }
        if (employee1.getState() == 0) {
            return R.error().Message("该员工已离职！");
        }
        //生成token  （id name）
        String token = JwtUtils.getJwtToken(employee1.getId(), employee1.getName());
        System.out.println(token);
        log.info(token);
//更新登录时间
        Employee employee2 = new Employee();
        employee2.setLoginTime(new Date());
        employee2.setId(employee1.getId());
        employeeService.updateById(employee2);
        return R.success().data("token", token);
    }

    //info
    @ApiOperation("根据token获取用户信息")
    @GetMapping("info")
    public R info(HttpServletRequest request) {
//        String token = request.getHeader("X-Token");
//        log.info(token);
        //获取token的id，也就是当前登录用户的id
        String employeeId = JwtUtils.getMemberIdByJwtToken(request);
        //根据id获取用户信息
        EmployeeDetail employeeDetail = employeeService.getEmployeeDetailById(employeeId);
        String name = employeeDetail.getName();
        String roleNames = employeeDetail.getRoleNames();
        return R.success().data("roles", roleNames).data("name", name).data("avatar", "https://tse3-mm.cn.bing.net/th/id/OIP-C.rmpwfloiacUcbDFsFPMn_gHaJe?w=144&h=185&c=7&r=0&o=5&dpr=1.3&pid=1.7").data("employeeId", employeeId);
    }
    @PostMapping("/logout")
    public R logout() {
        return R.success();
    }
}
