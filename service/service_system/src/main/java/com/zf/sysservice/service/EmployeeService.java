package com.zf.sysservice.service;

import com.zf.commonutils.R;
import com.zf.sysservice.entity.DemoData;
import com.zf.sysservice.entity.Employee;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zf.sysservice.entity.dto.EmployeeQuery;
import com.zf.sysservice.entity.vo.EmployeeDetail;
import com.zf.sysservice.entity.vo.Statistical;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 职工表 服务类
 * </p>
 *
 * @author ZF
 * @since 2022-10-25
 */
public interface EmployeeService extends IService<Employee> {
    //查询员工明细列表
    Map<String,Object> selectEmployeeDetailList(int pageNo, int pageSize, EmployeeQuery employeeQuery);

    void saveEmployeeWithRole(Employee employee, String[] roleIds);

    void updateEmployeeById(Employee employee, String[] roleIds);

    void removeEmployeeWithRoleById(String id);
    Employee getDetailById(String employeeId);

    void removeEmployeeWithRoleByIds(List<String> employeeIds);

    void exportExcel(HttpServletResponse response,List<String> employeeIds) throws IOException;

    void downExcel(HttpServletResponse response) throws Exception;

    void importEmployeeData(MultipartFile file, EmployeeService employeeService);

    void saveBatchExcel(List<DemoData> data);
    //根据id查询员工明细信息
    EmployeeDetail getEmployeeDetailById(String employeeId);
    List<Statistical> selectStatistical(EmployeeQuery employeeQuery,String id);

    List<Statistical> selectAllCustomerStatus();
}
