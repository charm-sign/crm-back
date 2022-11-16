package com.zf.custservice.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author ZF
 * @since 2022-10-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="Customer对象", description="")
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "客户姓名")
    private String name;

    @ApiModelProperty(value = "客户年龄")
    private Integer age;

    @ApiModelProperty(value = "客户性别 页面为下拉框 1男 0女")
    private String gender;

    @ApiModelProperty(value = "电话号码")
    private String tel;

    @ApiModelProperty(value = "QQ号")
    private String qq;

    @ApiModelProperty(value = "职业")
    private String job;

    @ApiModelProperty(value = "客户来源")
    private String source;

    @ApiModelProperty(value = "负责人 填写为当前登录用户")
    private String seller;

    @ApiModelProperty(value = "创建人 填写为当前登录用户")
    private String inputUser;

    @ApiModelProperty(value = "-2:流失 -1:开发失败 0:潜在客户 1:正式客户 2:资源池客户")
    private String status;

    @ApiModelProperty(value = "转正时间")
    private Date positiveTime;

    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
    private Integer isDeleted;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "修改时间")
    private Date modifiedTime;


}
