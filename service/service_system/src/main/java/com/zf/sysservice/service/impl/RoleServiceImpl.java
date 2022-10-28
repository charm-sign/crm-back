package com.zf.sysservice.service.impl;

import com.zf.sysservice.entity.Role;
import com.zf.sysservice.mapper.RoleMapper;
import com.zf.sysservice.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author ZF
 * @since 2022-10-28
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
