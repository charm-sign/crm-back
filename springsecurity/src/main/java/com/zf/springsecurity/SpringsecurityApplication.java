package com.zf.springsecurity;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@Slf4j
@EnableDiscoveryClient
@MapperScan("com.zf.springsecurity.mapper")
@ComponentScan(basePackages = {"com.zf"})
@EnableTransactionManagement
@EnableFeignClients
public class SpringsecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringsecurityApplication.class, args);
        log.info("Security项目启动成功...");

    }

}
