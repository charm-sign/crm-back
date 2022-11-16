package com.zf.custservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zf.commonutils.JwtUtils;
import com.zf.custservice.entity.Customer;
import com.zf.custservice.entity.CustomerHandover;
import com.zf.custservice.entity.dto.CustomerQuery;
import com.zf.custservice.entity.vo.CustomerFollowUpHistoryDetail;
import com.zf.custservice.entity.vo.CustomerHandoverDetail;
import com.zf.custservice.mapper.CustomerHandoverMapper;
import com.zf.custservice.service.CustomerHandoverService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zf.custservice.service.CustomerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 客户移交历史表 服务实现类
 * </p>
 *
 * @author ZF
 * @since 2022-10-31
 */
@Transactional
@Service
public class CustomerHandoverServiceImpl extends ServiceImpl<CustomerHandoverMapper, CustomerHandover> implements CustomerHandoverService {
@Autowired
private CustomerService customerService;
    @Override
    public Map<String, Object> selectDetailCustomerHandoverHistory(int pageNo, int pageSize, CustomerQuery customerQuery) {
        PageHelper.startPage(pageNo,pageSize);
        List<CustomerHandoverDetail> customerHandoverDetailList = baseMapper.selectDetailCustomerHandoverHistory(customerQuery);
        PageInfo<CustomerHandoverDetail> pageInfo = new PageInfo<>(customerHandoverDetailList);
        long total = pageInfo.getTotal();
        HashMap<String, Object> map = new HashMap<>();
        map.put("customerHandoverDetailList",customerHandoverDetailList);
        map.put("total",total);
        return map;
    }

    @Override
    public void saveHandoverWithUpdateCustomer(CustomerHandover customerHandover, HttpServletRequest request) {
        //添加移交记录
        customerHandover.setTransUser(JwtUtils.getMemberIdByJwtToken(request));
        customerHandover.setTransTime(new Date());
        baseMapper.insert(customerHandover);
        //修改客户的营销人员
        String customerId = customerHandover.getCustomerId();
        String newSeller = customerHandover.getNewSeller();
        Customer customer = new Customer();
        customer.setId(customerId);
        customer.setSeller(newSeller);
        customerService.updateById(customer);
    }

}
