package com.zf.servicebase.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class CrmException extends RuntimeException {
//    @ApiModelProperty(value = "状态码")
    private Integer code;
    private String msg;

    @Override
    public String toString() {
        return "GuliException{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}