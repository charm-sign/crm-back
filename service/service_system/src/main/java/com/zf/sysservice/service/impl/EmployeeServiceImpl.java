package com.zf.sysservice.service.impl;

import com.zf.sysservice.entity.Employee;
import com.zf.sysservice.mapper.EmployeeMapper;
import com.zf.sysservice.service.EmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 职工表 服务实现类
 * </p>
 *
 * @author ZF
 * @since 2022-10-25
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

}
