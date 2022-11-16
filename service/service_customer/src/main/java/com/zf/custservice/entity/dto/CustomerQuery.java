package com.zf.custservice.entity.dto;

import lombok.Data;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: CustomerQuery
 * @Description: 客户查询条件封装类
 * @Author: ZF
 * @date: 2022/10/31 13:06
 */
@Data
public class CustomerQuery implements Serializable {
   private static final long serialVersionUID = 1L;
   private String name;
//   @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
   private Date startTime;
//    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
   private Integer type;

}
