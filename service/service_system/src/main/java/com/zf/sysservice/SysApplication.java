package com.zf.sysservice;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName: SysApplication
 * @Description: TODO
 * @Author: ZF
 * @date: 2022/10/25 14:01
 */
@Slf4j
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.zf.sysservice.mapper")
@ComponentScan(basePackages = {"com.zf"})

public class SysApplication {
    public static void main(String[] args) {
        SpringApplication.run(SysApplication.class,args);
        log.info("Sys项目启动成功...");
    }
}
