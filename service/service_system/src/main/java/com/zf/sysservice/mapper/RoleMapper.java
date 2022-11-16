package com.zf.sysservice.mapper;

import com.zf.sysservice.entity.Permission;
import com.zf.sysservice.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 *
 * @author ZF
 * @since 2022-10-28
 */
public interface RoleMapper extends BaseMapper<Role> {
    /**
     * 根据员工id获取其角色列表
     * @param employeeId
     * @return
     */
    List<Role> selectEmployeeWithRoleById(String employeeId);

}
