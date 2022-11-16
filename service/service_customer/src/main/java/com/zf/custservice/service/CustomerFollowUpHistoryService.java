package com.zf.custservice.service;

import com.zf.custservice.entity.CustomerFollowUpHistory;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zf.custservice.entity.dto.CustomerQuery;

import java.util.Map;

/**
 * <p>
 * 客户跟进历史表 服务类
 * </p>
 *
 * @author ZF
 * @since 2022-10-31
 */
public interface CustomerFollowUpHistoryService extends IService<CustomerFollowUpHistory> {

    Map<String, Object> selectDetailCustomerFollowUpHistory(int pageNo, int pageSize, CustomerQuery customerQuery);
    CustomerFollowUpHistory selectCustomerFollowUpHistory(String id);
}
