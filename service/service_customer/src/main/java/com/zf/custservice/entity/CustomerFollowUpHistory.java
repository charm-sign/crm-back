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
 * 客户跟进历史表
 * </p>
 *
 * @author ZF
 * @since 2022-10-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="CustomerFollowUpHistory对象", description="客户跟进历史表")
public class CustomerFollowUpHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

    @ApiModelProperty(value = "跟进时间")
    private Date traceTime;

    @ApiModelProperty(value = "跟进内容 计划的详细内容")
    private String traceDetails;

    @ApiModelProperty(value = "跟进方式 计划采用如电话、邀约上门等  数据字典")
    private String traceType;

    @ApiModelProperty(value = "跟进效果 优----3、中----2、差----1")
    private String traceResult;

    @ApiModelProperty(value = "跟进客户 编辑时不可编辑 潜在客户对象/客户对象")
    private String customerId;

    @ApiModelProperty(value = "创建人 自动填入当前登录用户，用户不可更改 员工对象")
    private String inputUser;

    @ApiModelProperty(value = "跟进类型 0:潜在开发计划 1:客户跟进历史")
    private String type;

    @ApiModelProperty(value = "评论")
    private String comment;

    @ApiModelProperty(value = "逻辑删除 1（true）已删除， 0（false）未删除")
    private Integer isDeleted;

    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty(value = "修改时间")
    private Date modifiedTime;


}
