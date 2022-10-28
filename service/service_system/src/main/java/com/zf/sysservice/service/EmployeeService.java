package com.zf.sysservice.service;

import com.zf.sysservice.entity.Employee;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zf.sysservice.entity.dto.EmployeeQuery;
import com.zf.sysservice.entity.vo.EmployeeDetail;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 职工表 服务类
 * </p>
 *
 * @author ZF
 * @since 2022-10-25
 */
public interface EmployeeService extends IService<Employee> {
    //查询员工明细列表
    Map<String,Object> selectEmployeeDetailList(int pageNo, int pageSize, EmployeeQuery employeeQuery);
}
