package com.zf.custservice.config;

/**
 * @ClassName: SysConfig
 * @Description: TODO
 * @Author: ZF
 * @date: 2022/10/27 15:13
 */

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.zf.custservice.mapper")
public class CustConfig {
    //逻辑删除组件  新版本不用配置
    @Bean
    public ISqlInjector sqlInjector(){
        return new LogicSqlInjector();
    }
    //  1..分页插件 (一步即可使用)
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();  }
}
