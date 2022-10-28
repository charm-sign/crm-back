package com.zf.sysservice.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zf.sysservice.entity.Employee;
import com.zf.sysservice.entity.dto.EmployeeQuery;
import com.zf.sysservice.entity.vo.EmployeeDetail;
import com.zf.sysservice.mapper.EmployeeMapper;
import com.zf.sysservice.service.EmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public Map<String, Object> selectEmployeeDetailList(int pageNo, int pageSize, EmployeeQuery employeeQuery) {
        PageHelper.startPage(pageNo, pageSize);
        List<EmployeeDetail> employeeDetailList = baseMapper.selectEmployeeDetailList(employeeQuery);
        PageInfo<EmployeeDetail> pageInfo = new PageInfo<>(employeeDetailList);
        long total = pageInfo.getTotal();
//        List<EmployeeDetail> list = pageInfo.getList();等价于employeeDetailList
        HashMap<String, Object> map = new HashMap<>();
        map.put("total",total);
        map.put("employeeDetailList",employeeDetailList);
        return map;
    }
}
