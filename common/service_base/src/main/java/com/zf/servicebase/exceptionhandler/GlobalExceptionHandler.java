package com.zf.servicebase.exceptionhandler;
import com.zf.commonutils.ExceptionUtil;
import com.zf.commonutils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName: GlobalExceptionHandler
 * @Description: TODO
 * @Author: ZF
 * @date: 2022/9/28 21:03
 * 全局异常处理器
 */
@Slf4j
//Controller增强器，作用是给Controller控制器添加统一的操作或处理。
@ControllerAdvice //对Controller的增强，默认对所有
public class GlobalExceptionHandler {
    //-----------要添加其它异常处理，和这个里面内容相似-------------
    //指定出现什么异常时执行此方法
    @ExceptionHandler(Exception.class)
    @ResponseBody //只有在controller中数据才会返回，所以加此注解
    public R error(Exception e){
        e.printStackTrace();
        return R.error().Message("执行了全局异常处理...");
    }
     //------------------------------------------------------
     @ExceptionHandler(ArithmeticException.class)
     @ResponseBody
     public R error(ArithmeticException e){    //特定的   这个异常ArithmeticException  还有sql，空指针等（优先级高于全局）
         e.printStackTrace();
         return R.error().Message("执行了自定义异常");
     }
    //------------------------------------------------------
    //---------------------自定义的异常---------------------------------
    @ExceptionHandler(CrmException.class)
    @ResponseBody
    public R error(CrmException e){       //GuliException  自定义的异常
        log.error(ExceptionUtil.getMessage(e));
        e.printStackTrace();
        return R.error().Message(e.getMsg()).Code(e.getCode());
    }


}
