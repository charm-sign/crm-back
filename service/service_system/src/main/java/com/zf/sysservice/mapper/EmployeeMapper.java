package com.zf.sysservice.mapper;

import com.zf.sysservice.entity.DemoData;
import com.zf.sysservice.entity.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zf.sysservice.entity.dto.EmployeeQuery;
import com.zf.sysservice.entity.vo.EmployeeDetail;
import com.zf.sysservice.entity.vo.Statistical;

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
    //根据id查询员工信息
    Employee getDetailById(String employeeId);
    //根据id查询员工明细信息
    EmployeeDetail getEmployeeDetailById(String employeeId);

    void saveBatchExcel(List<DemoData> data);
//根据id统计客户数量
   List<Statistical> selectStatistical(EmployeeQuery employeeQuery,String id);

    List<Statistical> selectAllCustomerStatus();
}
