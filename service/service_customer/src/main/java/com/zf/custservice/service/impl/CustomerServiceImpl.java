package com.zf.custservice.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zf.custservice.entity.Customer;
import com.zf.custservice.entity.vo.CustomerDetail;
import com.zf.custservice.mapper.CustomerMapper;
import com.zf.custservice.service.CustomerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ZF
 * @since 2022-10-30
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {

    @Override
    public Map<String,Object> selectCustomerDetailList(int pageNo, int pageSize, Customer customer) {
        PageHelper.startPage(pageNo,pageSize);
        List<CustomerDetail> customerList = baseMapper.selectCustomerDetailList(customer);
        PageInfo<CustomerDetail> pageInfo = new PageInfo<>(customerList);
        long total = pageInfo.getTotal();
        HashMap<String, Object> map = new HashMap<>();
        map.put("customerList",customerList);
        map.put("total",total);

        return map;
    }

    @Override
    public List<CustomerDetail> selectCustById(String id) {
        return baseMapper.selectCustById(id);
    }
}
