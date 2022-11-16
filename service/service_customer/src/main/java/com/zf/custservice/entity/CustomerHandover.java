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
 * 客户移交历史表
 * </p>
 *
 * @author ZF
 * @since 2022-10-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CustomerHandover对象", description="客户移交历史表")
public class CustomerHandover implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "客户 客户对象")
    private String customerId;

    @ApiModelProperty(value = "移交人员 实行移交操作的管理人员")
    private String transUser;

    @ApiModelProperty(value = "移交时间")
    private Date transTime;

    @ApiModelProperty(value = "老市场专员 客户上的原始市场人员")
    private String oldSeller;

    @ApiModelProperty(value = "新市场专员 由公司重新指派后的新市场人员")
    private String newSeller;

    @ApiModelProperty(value = "移交原因")
    private String transReason;

    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
    private Integer isDeleted;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "修改时间")
    private Date modifiedTime;


}
