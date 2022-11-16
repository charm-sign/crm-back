package com.zf.sysservice.entity;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import io.swagger.models.auth.In;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName: ExcelDown
 * @Description: Excel导出
 * @Author: ZF
 * @date: 2022/11/6 20:08
 */
@ExcelIgnoreUnannotated
@Data
public class ExcelDown {
    @ExcelProperty("部门")
    private String departmentName;
    @ExcelProperty("名称")
    private String name;
    @ColumnWidth(25)
    @ExcelProperty("角色")
    private String roleNames;
    @ExcelProperty("年龄")
    private Integer age;
    @ExcelProperty("Email")
    @ColumnWidth(25)
    private String email;
    //==================
    @ExcelIgnore
    private String id;

    @ExcelIgnore
    private String password;
    @ExcelIgnore
    private Date hireDate;
    @ExcelIgnore
    private Integer state;
    @ExcelIgnore
    private Integer admin;
    @ExcelIgnore
    private Integer isDeleted;
    @ExcelIgnore
    private Date loginTime;
    @ExcelIgnore
    private Date createTime;
    @ExcelIgnore
    private Date modifiedTime;
    @ExcelIgnore
    private String dept;
    @ExcelIgnore
    private String role;

}
