package com.zf.springsecurity.entity;

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
