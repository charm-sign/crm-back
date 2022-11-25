package com.zf.sysservice.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zf.commonutils.JwtUtils;
import com.zf.commonutils.R;
import com.zf.sysservice.entity.dto.EmployeeQuery;
import com.zf.sysservice.entity.vo.EmployeeDetail;
import com.zf.sysservice.entity.vo.Statistical;
import com.zf.sysservice.service.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.eventusermodel.HSSFRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName: StatisticalController
 * @Description: TODO
 * @Author: ZF
 * @date: 2022/11/13 21:22
 */
@Slf4j
@Api(description = "统计分析")
@RestController
@RequestMapping("/sysService/statistical")
public class StatisticalController {
    @Autowired
    private EmployeeService employeeService;
    @ApiOperation("统计分析")
    @PostMapping("statistical/{pageNo}/{pageSize}")
    @PreAuthorize("hasAuthority('statisticalAnalysis:list')")
    public R statistical(@RequestBody(required = false) EmployeeQuery employeeQuery,@PathVariable int pageNo,@PathVariable int pageSize, HttpServletRequest request) {

        String id = JwtUtils.getMemberIdByJwtToken(request);
        EmployeeDetail employeeDetail = employeeService.getEmployeeDetailById(id);
        String roleNames = employeeDetail.getRoleNames();
        //分割字符串为数组
        String[] role = roleNames.split(",");
        List<Statistical> statisticalList=null;
        long total=0;

            for (String s : role) {
                if (s.equals("董事长")) {
//                查询所有的信息
                    PageHelper.startPage(pageNo, pageSize);
                    id=null;
                    statisticalList = employeeService.selectStatistical(employeeQuery,id);
                    PageInfo<Statistical> pageInfo = new PageInfo<>(statisticalList);
                    total = pageInfo.getTotal();
                    return R.success().data("statisticalList", statisticalList).data("total", total);
                }

            }
        //        只能查看自己的
        PageHelper.startPage(pageNo, pageSize);
//        employeeQuery.setEmployeeId(id);
        statisticalList = employeeService.selectStatistical(employeeQuery,id);
        PageInfo<Statistical> pageInfo = new PageInfo<>(statisticalList);
        total = pageInfo.getTotal();
        return R.success().data("statisticalList", statisticalList).data("total", total);

    }

    @ApiOperation("查询所有的客户，按状态分组,且返回当前账号信息")
    @GetMapping("getAllCustomerStatus")
    public R getAllCustomerStatus(HttpServletRequest request){
        //获取当前用户详细信息
        String id = JwtUtils.getMemberIdByJwtToken(request);
        EmployeeDetail employeeDetail = employeeService.getEmployeeDetailById(id);
        //获取客户统计
        List<Statistical>  allCustomerStatus =employeeService.selectAllCustomerStatus();
        return R.success().data("allCustomerStatus",allCustomerStatus).data("employeeDetail",employeeDetail);
    }
}
