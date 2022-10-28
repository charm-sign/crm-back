package com.zf.sysservice.mapper;

import com.zf.sysservice.entity.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zf.sysservice.entity.dto.EmployeeQuery;
import com.zf.sysservice.entity.vo.EmployeeDetail;

import java.util.List;

/**
 * <p>
 * 职工表 Mapper 接口
 * </p>
 *
 * @author ZF
 * @since 2022-10-25
 */
public interface EmployeeMapper extends BaseMapper<Employee> {
//查询员工明细列表
    List<EmployeeDetail> selectEmployeeDetailList(EmployeeQuery employeeQuery);
}
