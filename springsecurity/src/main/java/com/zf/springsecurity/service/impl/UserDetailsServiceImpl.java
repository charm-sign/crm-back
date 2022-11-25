package com.zf.springsecurity.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zf.commonutils.R;
import com.zf.servicebase.exceptionhandler.CrmException;
import com.zf.servicebase.exceptionhandler.GlobalExceptionHandler;
import com.zf.springsecurity.client.SysClient;
import com.zf.springsecurity.entity.Employee;

import com.zf.springsecurity.entity.LoginUser;
import com.zf.springsecurity.mapper.EmployeeMapper2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
* @ClassName: UserDetailsServiceImpl
* @Description: 重写方法，改为从数据库查询用户信息，根据用户名查的
* @Author: ZF
* @date: 2022/11/20 14:26
*/
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private EmployeeMapper2 employeeMapper;
    @Autowired
    private SysClient sysClient;
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        //根据用户名查询用户信息
        LambdaQueryWrapper<Employee> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Employee::getName,userName)
                .eq(Employee::getState,1);
        Employee user = employeeMapper.selectOne(lqw);
        if (Objects.isNull(user)){
            throw new CrmException(20001,"请输入正确的用户名或密码！"); //此处只是抛出，并没有捕获并响应
        }
        //TODO 根据用户查询权限信息  添加到LoginUser中
        List<String> perms = sysClient.getPermsByEmployeeId(user.getId());
        //封装成UserDetails对象返回
        return new LoginUser(user,perms);
    }
}