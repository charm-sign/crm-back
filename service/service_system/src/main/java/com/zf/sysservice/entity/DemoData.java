package com.zf.sysservice.entity;


import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;

import lombok.Data;



/**
 * @ClassName: ExcelDown
 * @Description: Excel导出
 * @Author: ZF
 * @date: 2022/11/6 20:08
 */
@ExcelIgnoreUnannotated
@Data
public class DemoData {
//    @ExcelProperty("部门")
//    private String departmentName;
    @ExcelProperty("名称")
    private String name;
//    @ColumnWidth(25)
//    @ExcelProperty("角色")
//    private String roleNames;
    @ColumnWidth(25)
    @ExcelProperty("密码")
    private String password;
    @ExcelProperty("年龄")
    private Integer age;
    @ExcelProperty("Email")
    @ColumnWidth(25)
    private String email;


}
