package com.zf.commonutils;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: R
 * @Description: TODO
 * @Author: ZF
 * @date: 2022/9/28 15:55
 */
@Data
public class R {
    @ApiModelProperty(value = "是否成功")
    private Boolean Success;
    @ApiModelProperty(value = "返回码")
    private Integer Code;
    @ApiModelProperty(value = "返回消息")
    private String Message;
    @ApiModelProperty(value = "返回数据")
    private Map<String, Object> data = new HashMap<String, Object>();

    //将构造方法私有化，是此类不能new ,只能用静态方法
    public R(){};
//成功静态方法
    public static R success(){    //数据data在下面定义
        R r = new R();
       r.setSuccess(true);
       r.setCode(ResultCode.SUCCESS);
       r.setMessage("成功");
       return r;
    }
    //失败静态方法
    public static R error(){    //数据data在下面定义
        R r = new R();
       r.setSuccess(false);
       r.setCode(ResultCode.ERROR);
       r.setMessage("失败");
       return r;
    }
//    ----------------------- //以下方法最终都     return  this (返回给调用方) 链式编程----------------------------
    public R Success(Boolean Success){
        this.setSuccess(Success);
        return this;
    }
    public R Message(String Message){
        this.setMessage(Message);
        return this;
    }
    public R Code(Integer Code){
        this.setCode(Code);
        return this;
    }
    public R data(String key, Object value){
        this.data.put(key, value);
        return this;
    }
    public R data(Map<String, Object> map){
        this.setData(map);
        return this;
    }


}
