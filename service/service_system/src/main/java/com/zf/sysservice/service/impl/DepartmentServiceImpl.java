package com.zf.sysservice.service.impl;

import com.zf.sysservice.entity.Department;
import com.zf.sysservice.mapper.DepartmentMapper;
import com.zf.sysservice.service.DepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 部门表 服务实现类
 * </p>
 *
 * @author ZF
 * @since 2022-10-27
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {

}
