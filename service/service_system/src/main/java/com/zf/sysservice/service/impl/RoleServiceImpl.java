package com.zf.sysservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zf.sysservice.entity.Role;
import com.zf.sysservice.entity.RolePermission;
import com.zf.sysservice.mapper.RoleMapper;
import com.zf.sysservice.service.RolePermissionService;
import com.zf.sysservice.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author ZF
 * @since 2022-10-28
 */
@Service
@Transactional
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
@Autowired
    RolePermissionService rolePermissionService;


    /**
     * 添加角色
     * @param role
     * @param permissionIds
     */
    @Override
    public void saveRoleWithPermission(Role role, String[] permissionIds) {
        baseMapper.insert(role);
        String roleId = role.getId();
        RolePermission rolePermission= null;
        if(permissionIds!=null){
        for (String permissionId : permissionIds) {
            rolePermission = new RolePermission();
            rolePermission.setRoleId(roleId);
            rolePermission.setPermissionId(permissionId);
            rolePermissionService.save(rolePermission);
        }
        }
    }

    /**
     * 删除角色
     * @param id
     * @return
     */
    @Override
    public void removeRoleWithPermissionById(String id) {
        baseMapper.deleteById(id);
        LambdaQueryWrapper<RolePermission> lqw = new LambdaQueryWrapper<>();
        lqw.eq(RolePermission::getRoleId,id);
        rolePermissionService.remove(lqw);

    }

    /**
     * 修改角色（角色表+角色权限表）
     * @param role
     * @param permissionIds
     */
    @Override
    public void updateRoleById(Role role, String[] permissionIds) {
        //修改角色表数据
        String roleId = role.getId();
        baseMapper.updateById(role);
        //删除数据 角色 - 权限表
        LambdaQueryWrapper<RolePermission> lqw = new LambdaQueryWrapper<>();
        lqw.eq(RolePermission::getRoleId,roleId);
        rolePermissionService.remove(lqw);
        //添加数据 角色 - 权限表
        RolePermission rolePermission= null;
        if (permissionIds!=null){
        for (String permissionId : permissionIds) {
            rolePermission = new RolePermission();
            rolePermission.setRoleId(roleId);
            rolePermission.setPermissionId(permissionId);
            rolePermissionService.save(rolePermission);
        }
        }
    }

    @Override
    public List<Role> selectEmployeeWithRoleById(String employeeId) {
        return baseMapper.selectEmployeeWithRoleById(employeeId);
    }

}
