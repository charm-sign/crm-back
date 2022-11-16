package com.zf.sysservice.service;

import com.zf.sysservice.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 权限表 服务类
 * </p>
 *
 * @author ZF
 * @since 2022-10-29
 */
public interface PermissionService extends IService<Permission> {
    /**
     * 根据角色id获取其权限列表
     * @param roleId
     * @return
     */
    List<Permission> selectRoleWithPermissionById(String roleId);
}
