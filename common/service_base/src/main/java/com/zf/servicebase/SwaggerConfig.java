package com.zf.servicebase;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName: SwaggerConfig
 * @Description: TODO
 * @Author: ZF
 * @date: 2022/9/28 14:12
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket webApiConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("webApi")//随意起名
                .apiInfo(webApiInfo())//信息，也可随意
                .select()
//                .paths(Predicates.not(PathSelectors.regex("/admin/.*")))//默认 有此路径，swagger就不会显示（所以测试有admin路径时应注释掉）
                .paths(Predicates.not(PathSelectors.regex("/error.*")))//默认
                .build();
    }

    private ApiInfo webApiInfo(){
        return new ApiInfoBuilder()
                .title("客户关系管理系统API文档")
                .description("本文档描述了CRM系统微服务接口定义")
                .version("1.0")
                .contact(new Contact("sign", "http://zf.com", "1736213678@qq.com"))
                .build();
    }
}