package com.zf.sysservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zf.commonutils.R;
import com.zf.servicebase.exceptionhandler.CrmException;
import com.zf.sysservice.entity.*;
import com.zf.sysservice.entity.dto.EmployeeQuery;
import com.zf.sysservice.entity.vo.EmployeeDetail;
import com.zf.sysservice.entity.vo.Statistical;
import com.zf.sysservice.mapper.EmployeeMapper;
import com.zf.sysservice.service.EmployeeRoleService;
import com.zf.sysservice.service.EmployeeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.*;

/**
 * <p>
 * 职工表 服务实现类
 * </p>
 *
 * @author ZF
 * @since 2022-10-25
 */
@Service
@Transactional
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

    @Autowired
    private EmployeeRoleService employeeRoleService;

    @Override
    public Map<String, Object> selectEmployeeDetailList(int pageNo, int pageSize, EmployeeQuery employeeQuery) {
        PageHelper.startPage(pageNo, pageSize);
        List<EmployeeDetail> employeeDetailList = baseMapper.selectEmployeeDetailList(employeeQuery);
        PageInfo<EmployeeDetail> pageInfo = new PageInfo<>(employeeDetailList); //注意需要传入一个List
        long total = pageInfo.getTotal();
//        List<EmployeeDetail> list = pageInfo.getList();等价于employeeDetailList
        HashMap<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("employeeDetailList", employeeDetailList);
        return map;
    }

    /**
     * 添加员工(员工角色)
     *
     * @param employee
     * @param roleIds
     */
    @Override
    public void saveEmployeeWithRole(Employee employee, String[] roleIds) {
        baseMapper.insert(employee);
        String employeeId = employee.getId();
        EmployeeRole employeeRole = null;
        // RolePermission rolePermission= null;
        for (String roleId : roleIds) {
            employeeRole = new EmployeeRole();
            employeeRole.setEmployeeId(employeeId);
            employeeRole.setRoleId(roleId);
            employeeRoleService.save(employeeRole);
        }
    }

    /**
     * 修改员工（员工表+员工角色表）
     *
     * @param employee
     * @param roleIds
     */
    @Override
    public void updateEmployeeById(Employee employee, String[] roleIds) {
        //修改员工表数据
        String employeeId = employee.getId();
        baseMapper.updateById(employee);
        //删除数据 员工 - 角色表
        LambdaQueryWrapper<EmployeeRole> lqw = new LambdaQueryWrapper<>();
        lqw.eq(EmployeeRole::getEmployeeId, employeeId);
        employeeRoleService.remove(lqw);
        //添加数据 员工 - 角色表
        EmployeeRole employeeRole = null;
        for (String roleId : roleIds) {
            employeeRole = new EmployeeRole();
            employeeRole.setEmployeeId(employeeId);
            employeeRole.setRoleId(roleId);
            employeeRoleService.save(employeeRole);
        }
    }

    @Override
    public void removeEmployeeWithRoleById(String id) {
        baseMapper.deleteById(id);
        LambdaQueryWrapper<EmployeeRole> lqw = new LambdaQueryWrapper<>();
        lqw.eq(EmployeeRole::getEmployeeId,id);
        employeeRoleService.remove(lqw);
    }

    @Override
    public Employee getDetailById(String employeeId) {
        return baseMapper.getDetailById(employeeId);
    }

    @Override
    public void removeEmployeeWithRoleByIds(List<String> employeeIds) {
        //删除 员工表 数据
        baseMapper.deleteBatchIds(employeeIds);
        //删除  员工角色表 数据
        for (String employeeId : employeeIds) {
            LambdaQueryWrapper<EmployeeRole> lqw = new LambdaQueryWrapper<>();
            lqw.eq(EmployeeRole::getEmployeeId,employeeId);
            employeeRoleService.remove(lqw);        }
    }

    @Override
    public void exportExcel(HttpServletResponse response,List<String> employeeIds) throws IOException {
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("员工信息", "utf-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), ExcelDown.class).sheet("员工信息表").doWrite(data(employeeIds));
    }

    @Override
    public void downExcel(HttpServletResponse response) throws Exception{
//        response.setContentType("application/vnd.ms-excel");
//        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("员工信息模板", "utf-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), DemoData.class).sheet("员工信息表").doWrite(template());
    }

    /**
     * 文件导入
     * @param file
     * @param employeeService
     */
    @Override
    public void importEmployeeData(MultipartFile file, EmployeeService employeeService) {
        try {
//1 获取文件输入流
            InputStream inputStream = file.getInputStream();
            // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
            EasyExcel.read(inputStream, DemoData.class, new EmployeeExcelListener(employeeService)).sheet().doRead();
        }catch (Exception e){
            throw new CrmException(20002,"添加员工信息失败");
        }
    }

    @Override
    public void saveBatchExcel(List<DemoData> data) {

            // 如果是mybatis,尽量别直接调用多次insert,自己写一个mapper里面新增一个方法batchInsert,所有数据一次性插入
        baseMapper.saveBatchExcel(data);
    }

    @Override
    public EmployeeDetail getEmployeeDetailById(String employeeId) {
        return baseMapper.getEmployeeDetailById(employeeId);
    }

    @Override
    public List<Statistical> selectStatistical(EmployeeQuery employeeQuery,String id) {
        return baseMapper.selectStatistical(employeeQuery,id);
    }

    @Override
    public List<Statistical> selectAllCustomerStatus() {
        return baseMapper.selectAllCustomerStatus();
    }


    private List template() {
        ArrayList<DemoData> list = new ArrayList<>();
        return list;
    }

    /**
     * 查询明细
     * @param employeeIds
     * @return
     */
    private List data(List<String> employeeIds) {
        ArrayList<EmployeeDetail> list = new ArrayList<>();
        for (String employeeId : employeeIds) {
            EmployeeDetail employeeDetail = baseMapper.getEmployeeDetailById(employeeId);
            list.add(employeeDetail);
        }
        return list;
    }

}
