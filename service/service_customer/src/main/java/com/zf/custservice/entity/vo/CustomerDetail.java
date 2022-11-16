package com.zf.custservice.entity.vo;

import com.zf.custservice.entity.Customer;
import lombok.Data;

/**
 * @ClassName: CustomerVo
 * @Description: TODO
 * @Author: ZF
 * @date: 2022/10/30 16:04
 */
@Data
public class CustomerDetail extends Customer {
   private String jobs;
   private String sources;
   private String sellers;
}
