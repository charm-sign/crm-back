package com.zf.custservice.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zf.custservice.entity.CustomerFollowUpHistory;
import com.zf.custservice.entity.dto.CustomerQuery;
import com.zf.custservice.entity.vo.CustomerFollowUpHistoryDetail;
import com.zf.custservice.mapper.CustomerFollowUpHistoryMapper;
import com.zf.custservice.service.CustomerFollowUpHistoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 客户跟进历史表 服务实现类
 * </p>
 *
 * @author ZF
 * @since 2022-10-31
 */
@Service
public class CustomerFollowUpHistoryServiceImpl extends ServiceImpl<CustomerFollowUpHistoryMapper, CustomerFollowUpHistory> implements CustomerFollowUpHistoryService {

    @Override
    public Map<String, Object> selectDetailCustomerFollowUpHistory(int pageNo, int pageSize, CustomerQuery customerQuery) {
        PageHelper.startPage(pageNo,pageSize);
        List<CustomerFollowUpHistoryDetail> customerFollowUpHistoryDetailList = baseMapper.selectCustomerFollowUpHistoryDetailList(customerQuery);
        PageInfo<CustomerFollowUpHistoryDetail> pageInfo = new PageInfo<>(customerFollowUpHistoryDetailList);
        long total = pageInfo.getTotal();
        HashMap<String, Object> map = new HashMap<>();
        map.put("customerFollowUpHistoryDetailList",customerFollowUpHistoryDetailList);
        map.put("total",total);
        return map;
    }

    @Override
    public CustomerFollowUpHistory selectCustomerFollowUpHistory(String id) {
        return baseMapper.selectCustomerFollowUpHistory(id);
    }
}
