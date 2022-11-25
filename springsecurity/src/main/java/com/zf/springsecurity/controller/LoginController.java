package com.zf.springsecurity.controller;

import com.zf.commonutils.JwtUtils;
import com.zf.commonutils.R;
import com.zf.springsecurity.client.SysClient;
import com.zf.springsecurity.entity.Employee;
import com.zf.springsecurity.entity.EmployeeDetail;
import com.zf.springsecurity.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Slf4j
@Api(description = "登录管理")
@RestController
@RequestMapping("/security")
public class LoginController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private SysClient sysClient;
    @PostMapping("login")
    public R login(@RequestBody Employee employee) {
        return loginService.login(employee); //Service进行具体业务操作
    }

    @GetMapping("logout")
    public R logout() {
        return loginService.logout();
    }
}