package com.zf.springsecurity.filter;


import com.zf.commonutils.JwtUtils;
import com.zf.commonutils.RedisCache;
import com.zf.springsecurity.entity.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
* @ClassName: JwtAuthenticationTokenFilter
* @Description: 认证过滤器
* @Author: ZF
* @date: 2022/11/20 19:29
*/
@Component
    public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
        @Autowired
        private RedisCache redisCache;
        @Override
        protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

            //        获取token
            String token = request.getHeader("X-Token");
//            String id = JwtUtils.getMemberIdByJwtToken(request);
            if (!StringUtils.hasText(token)) { //如果为空，直接放行，为什么？因为下面的都是解析的，没有token就没必要向下走，而且后面还有一系列过滤器，没有token会执行相应的异常
                filterChain.doFilter(request, response);
                return;//来去都不进入下面代码
            }
            //        解析token 获取用户id
            String id;
            try {
//                Claims claims = JwtUtil.parseJWT(token);
//                id = claims.getSubject();  //用户id
                id=JwtUtils.getMemberIdByJwtToken(request);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("token非法");
            }
            //        从redis中获取用户信息
            System.out.println(id);
            String redisKey = "login" + id;
            LoginUser loginUser = redisCache.getCacheObject(redisKey);  //与存入类型一致
//            System.out.println("+++++++"+loginUser);
            if (Objects.isNull(loginUser)){
                throw new RuntimeException("用户未登录");
            }
            //        存入SecurityContextHolder供后面过滤器判断是否认证
            //2个参数是用户名密码。3个参数（set是否已认证）
            //        this.principal = principal; 主要的
            //        this.credentials = credentials;
            //        super.setAuthenticated(true);是否已认证
            //TODO 获取权限信息，封装进Authentication
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser,null,loginUser.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            //放行
            filterChain.doFilter(request,response);

        }
    }