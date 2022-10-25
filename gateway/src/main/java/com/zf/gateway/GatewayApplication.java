package com.zf.gateway;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName: GatewayApplication
 * @Description: TODO
 * @Author: ZF
 * @date: 2022/10/25 10:44
 */
@Slf4j
@SpringBootApplication
@EnableDiscoveryClient//nacos注册

public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
        log.info("Gateway项目启动成功...");
    }
}
