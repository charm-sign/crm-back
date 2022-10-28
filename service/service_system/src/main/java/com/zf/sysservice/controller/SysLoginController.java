package com.zf.sysservice.controller;


import com.zf.commonutils.R;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: SysLoginController
 * @Description: TODO
 * @Author: ZF
 * @date: 2022/10/25 14:09
 */
@Api(description = "登录管理")
@RestController
@RequestMapping("/sysService/user")
public class SysLoginController {
    //login
    @PostMapping("login")
    public R login() {
        return R.success().data("token","admin");
    }

    //info
    @GetMapping("info")
    public R info() {
        return R.success().data("roles", "admin").data("name", "admin").data("avatar", "https://5b0988e595225.cdn.sohucs.com/images/20180626/9259c1ea465a4207b9bc0f36614aa65f.gif");

    }
}
