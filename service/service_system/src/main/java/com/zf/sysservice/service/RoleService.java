package com.zf.sysservice.service;

import com.zf.sysservice.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author ZF
 * @since 2022-10-28
 */
public interface RoleService extends IService<Role> {

    void saveRoleWithPermission(Role role, String[] permissionIds);

    void removeRoleWithPermissionById(String id);

    void updateRoleById(Role role, String[] permissionIds);



    List<Role> selectEmployeeWithRoleById(String employeeId);
}
