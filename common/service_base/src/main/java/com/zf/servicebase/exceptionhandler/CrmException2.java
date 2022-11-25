package com.zf.servicebase.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.security.sasl.AuthenticationException;

/**
 * @ClassName: GuliException
 * @Description: TODO
 * @Author: ZF
 * @date: 2022/10/25 21:55
 * 自定义异常类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CrmException2 extends AuthenticationException {
//    @ApiModelProperty(value = "状态码")
    private Integer code;
    private String msg;

    @Override
    public String toString() {
        return "CrmException{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}