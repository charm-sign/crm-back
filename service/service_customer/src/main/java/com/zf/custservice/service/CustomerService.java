package com.zf.custservice.service;

import com.zf.custservice.entity.Customer;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zf.custservice.entity.vo.CustomerDetail;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ZF
 * @since 2022-10-30
 */
public interface CustomerService extends IService<Customer> {
    //分页条件查询
    Map<String,Object> selectCustomerDetailList(int pageNo, int pageSize, Customer customer);

    List<CustomerDetail> selectCustById(String id);
}
