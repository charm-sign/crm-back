package com.zf.custservice;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @ClassName: CustApplication
 * @Description: TODO
 * @Author: ZF
 * @date: 2022/10/25 14:01
 */
@Slf4j
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.zf.custservice.mapper")
@ComponentScan(basePackages = {"com.zf"})
@EnableTransactionManagement
public class CustApplication {
    public static void main(String[] args) {
        SpringApplication.run(CustApplication.class,args);
        log.info("Cust项目启动成功...");
    }
}
