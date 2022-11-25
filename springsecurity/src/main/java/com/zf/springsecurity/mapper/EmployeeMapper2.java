package com.zf.springsecurity.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zf.springsecurity.entity.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

/**
 * <p>
 * 职工表 Mapper 接口
 * </p>
 *
 * @author ZF
 * @since 2022-10-25
 */
//@Mapper
public interface EmployeeMapper2 extends BaseMapper<Employee> {

}
