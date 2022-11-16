package com.zf.custservice.mapper;

import com.zf.custservice.entity.Customer;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zf.custservice.entity.vo.CustomerDetail;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ZF
 * @since 2022-10-30
 */
public interface CustomerMapper extends BaseMapper<Customer> {
//分页条件查询
List<CustomerDetail> selectCustomerDetailList(Customer customer);
//根据当前用户id获取其负责的客户
    List<CustomerDetail> selectCustById(String id);
}
