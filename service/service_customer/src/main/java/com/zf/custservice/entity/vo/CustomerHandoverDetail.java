package com.zf.custservice.entity.vo;

import com.zf.custservice.entity.CustomerHandover;
import lombok.Data;

/**
 * @ClassName: CustomerHandover
 * @Description: 客户移交历史明细封装
 * @Author: ZF
 * @date: 2022/10/31 19:13
 */
@Data
public class CustomerHandoverDetail extends CustomerHandover {
    private String customerName;//客户姓名
    private String operator;//操作人
    private String oldMarket;//旧营销人员
    private String newMarket;//新营销人员

}
