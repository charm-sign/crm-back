package com.zf.sysservice.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: EmployeeQuery
 * @Description: TODO
 * @Author: ZF
 * @date: 2022/10/27 21:52
 */
@Data

public class EmployeeQuery implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "员工名称,模糊查询")
    private String name;
    @ApiModelProperty(value = "员工部门,模糊查询")
    private Integer dept;
    private Date startTime;
    private Date endTime;
    private Integer type;

    private String employeeId;
}
