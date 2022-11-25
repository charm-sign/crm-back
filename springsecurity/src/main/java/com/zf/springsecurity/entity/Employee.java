package com.zf.springsecurity.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 职工表
 * </p>
 *
 * @author ZF
 * @since 2022-10-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Employee对象", description="职工表")
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "密码")
    private String password;

    @TableField(exist = false)
    @ApiModelProperty(value = "确认密码")
    private String checkPassword;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "年龄")
    private Integer age;

    @ApiModelProperty(value = "部门")
    private String dept;

    @ApiModelProperty(value = "入职时间")
    private Date hireDate;

    @ApiModelProperty(value = "状态 1正常 0离职")
    private Integer state;

    @ApiModelProperty(value = "超级管理员身份 1超管 0普通")
    private Integer admin;

    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
    private Integer isDeleted;

    @ApiModelProperty(value = "登录时间")
    private Date loginTime;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "修改时间")
    private Date modifiedTime;


}
