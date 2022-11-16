package com.zf.custservice.mapper;

import com.zf.custservice.entity.Customer;
import com.zf.custservice.entity.CustomerFollowUpHistory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zf.custservice.entity.dto.CustomerQuery;
import com.zf.custservice.entity.vo.CustomerDetail;
import com.zf.custservice.entity.vo.CustomerFollowUpHistoryDetail;

import java.util.List;

/**
 * <p>
 * 客户跟进历史表 Mapper 接口
 * </p>
 *
 * @author ZF
 * @since 2022-10-31
 */
public interface CustomerFollowUpHistoryMapper extends BaseMapper<CustomerFollowUpHistory> {
    //分页条件查询
    List<CustomerFollowUpHistoryDetail> selectCustomerFollowUpHistoryDetailList(CustomerQuery customerQuery);
    //根据id查询跟进历史详细信息（下拉框是绑定的，所以只需要客户姓名的明细）
CustomerFollowUpHistory selectCustomerFollowUpHistory(String id);

}
