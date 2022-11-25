package com.zf.springsecurity.service.impl;

import com.zf.commonutils.JwtUtils;
import com.zf.commonutils.R;
import com.zf.commonutils.RedisCache;
import com.zf.springsecurity.entity.Employee;
import com.zf.springsecurity.entity.LoginUser;
import com.zf.springsecurity.mapper.EmployeeMapper2;
import com.zf.springsecurity.service.LoginService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

/**
* @ClassName: LoginServiceImpl
* @Description: 登录业务逻辑代码
* @Author: ZF
* @date: 2022/11/20 17:39
*/
@Service
    public class LoginServiceImpl implements LoginService {
        @Autowired
        private AuthenticationManager authenticationManager; //进行用户认证,需要在配置类中重写并注入
        @Autowired
        private RedisCache redisCache;
        @Autowired
        private EmployeeMapper2 employeeMapper2;
//登录
        @Override
        public R login(Employee employee) {
            //AuthenticationManager authenticationManager 进行用户认证
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(employee.getName(), employee.getPassword());
            Authentication authenticate = authenticationManager.authenticate(authenticationToken);//会调用自定义的UserDetailService查询数据库
            //        如果认证未通过，给出提示
            if (Objects.isNull(authenticate)) {
//                throw new RuntimeException("用户名或密码错误");
                return R.error().Message("用户名或密码错误");
            }
            //        认证通过，使用userId生成 jwt token,
            //        但怎么获取id呢，可以用断点调试 if 方法查看在哪个属性里  在配置文件中重写方法放开登录接口
            LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
            System.out.println(loginUser);
//            String id = loginUser.getUser().getId().toString();
            String id = loginUser.getEmployee().getId();
            String name = loginUser.getEmployee().getName();
            String token = JwtUtils.getJwtToken(id,name);
            if (Objects.isNull(loginUser)) {
            throw new RuntimeException("不存在");
        }
            //        将完整用户信息存入Redis
            redisCache.setCacheObject("login" + id, loginUser);

            //        返回token
            HashMap<String, Object> map = new HashMap<>();
            map.put("token", token);
            //更新登录时间
            Employee employee1 = new Employee();
            employee1.setLoginTime(new Date());
            employee1.setId(id);
            employeeMapper2.updateById(employee1);
            return R.success().Message("登录成功").data(map);
        }
//退出
    @Override
    public R logout() {
        //获取SecurityContextHolder中的用户id  （不需要删除这个里面的值，因为是单独的请求，其它请求访问来又是新的请求，此时key已经不能用）
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String id = loginUser.getEmployee().getId();
        //删除Redis中的数据
        String redisKey="login"+id;
        redisCache.deleteObject(redisKey);
        return R.success().Message("注销成功");
    }
}