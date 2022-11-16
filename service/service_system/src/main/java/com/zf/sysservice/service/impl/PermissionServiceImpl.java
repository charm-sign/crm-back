package com.zf.sysservice.service.impl;

import com.zf.sysservice.entity.Permission;
import com.zf.sysservice.mapper.PermissionMapper;
import com.zf.sysservice.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author ZF
 * @since 2022-10-29
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

    @Override
    public List<Permission> selectRoleWithPermissionById(String roleId) {
        return baseMapper.selectRoleWithPermissionById(roleId);
    }
}
