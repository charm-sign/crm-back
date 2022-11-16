package com.zf.custservice.service;

import com.zf.custservice.entity.CustomerHandover;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zf.custservice.entity.dto.CustomerQuery;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 * 客户移交历史表 服务类
 * </p>
 *
 * @author ZF
 * @since 2022-10-31
 */
public interface CustomerHandoverService extends IService<CustomerHandover> {

    Map<String, Object> selectDetailCustomerHandoverHistory(int pageNo, int pageSize, CustomerQuery customerQuery);

    void saveHandoverWithUpdateCustomer(CustomerHandover customerHandover, HttpServletRequest request);
}
