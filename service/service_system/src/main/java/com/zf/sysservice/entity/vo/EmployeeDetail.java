package com.zf.sysservice.entity.vo;

import com.zf.sysservice.entity.Employee;
import lombok.Data;

/**
 * @ClassName: EmployeeDetail
 * @Description: TODO
 * @Author: ZF
 * @date: 2022/10/28 16:00
 * 封装员工的明细信息
 */
@Data
public class EmployeeDetail extends Employee {
    private String departmentName;
    private String roleNames;
}
