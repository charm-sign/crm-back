package com.zf.custservice.entity.vo;

import com.zf.custservice.entity.CustomerFollowUpHistory;
import lombok.Data;

/**
 * @ClassName: CustomerFollowUpHistory
 * @Description: TODO
 * @Author: ZF
 * @date: 2022/10/31 13:46
 */
@Data
public class CustomerFollowUpHistoryDetail extends CustomerFollowUpHistory {
   private String traceTypes;
   private String customerNames;
   private String inputUsers;
}
