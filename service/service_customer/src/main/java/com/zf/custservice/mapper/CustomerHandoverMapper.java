package com.zf.custservice.mapper;

import com.zf.custservice.entity.CustomerHandover;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zf.custservice.entity.dto.CustomerQuery;
import com.zf.custservice.entity.vo.CustomerHandoverDetail;

import java.util.List;

/**
 * <p>
 * 客户移交历史表 Mapper 接口
 * </p>
 *
 * @author ZF
 * @since 2022-10-31
 */
public interface CustomerHandoverMapper extends BaseMapper<CustomerHandover> {

//分页条件查询移交历史
    List<CustomerHandoverDetail> selectDetailCustomerHandoverHistory(CustomerQuery customerQuery);
}
