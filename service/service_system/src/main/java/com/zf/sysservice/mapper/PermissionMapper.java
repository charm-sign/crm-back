package com.zf.sysservice.mapper;

import com.zf.sysservice.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 权限表 Mapper 接口
 * </p>
 *
 * @author ZF
 * @since 2022-10-29
 */
public interface PermissionMapper extends BaseMapper<Permission> {
    /**
     * 根据角色id获取其权限列表
     * @param roleId
     * @return
     */
    List<Permission> selectRoleWithPermissionById(String roleId);
    //查询当前用户所具有的所有权限
    List<String> selectPermsByEmployeeId(String id);
}
