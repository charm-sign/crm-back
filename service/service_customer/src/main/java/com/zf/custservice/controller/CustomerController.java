package com.zf.custservice.controller;


import com.zf.commonutils.JwtUtils;
import com.zf.commonutils.R;
import com.zf.custservice.entity.Customer;
import com.zf.custservice.entity.vo.CustomerDetail;
import com.zf.custservice.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ZF
 * @since 2022-10-30
 */
@Slf4j
@Api(description = "客户管理")
@RestController
@RequestMapping("/custService/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @ApiOperation("分页条件查询客户列表")
    @PostMapping("list/{pageNo}/{pageSize}")
    public R list(@PathVariable int pageNo, @PathVariable int pageSize, @RequestBody(required = false) Customer customer, HttpServletRequest request) {
        String memberIdByJwtToken = JwtUtils.getMemberIdByJwtToken(request);
        log.info("当前用户" + memberIdByJwtToken);

        Map<String, Object> map = customerService.selectCustomerDetailList(pageNo, pageSize, customer);
        return R.success().data(map);

    }

    @ApiOperation("添加客户")
    @PostMapping("add")
    public R add(@RequestBody Customer customer, HttpServletRequest request) {
        //获取当前登陆的账户id
        String employeeId = JwtUtils.getMemberIdByJwtToken(request);
        customer.setSeller(employeeId);
        customer.setInputUser(employeeId);
        customerService.save(customer);
        return R.success();
    }

    @ApiOperation("根据id获取客户信息")
    @GetMapping("getInfoById/{customerId}")
    public R getInfoById(@PathVariable String customerId,HttpServletRequest request) {
        String tokenId = JwtUtils.getMemberIdByJwtToken(request);
        Customer customer = customerService.getById(customerId);
        return R.success().data("customer", customer).data("tokenId",tokenId);

    }

    @ApiOperation("修改客户")
    @PostMapping("update")
    public R update(@RequestBody Customer customer, HttpServletRequest request) {
//        //获取当前登陆的账户id
//        String employeeId = JwtUtils.getMemberIdByJwtToken(request);
//        customer.setSeller(employeeId);
//        customer.setInputUser(employeeId);
        if ("1".equals(customer.getStatus())) {
            customer.setPositiveTime(new Date());
        }
        customerService.updateById(customer);
        return R.success();
    }

    @ApiOperation("查询由当前用户负责的客户")
    @GetMapping("getCustById")
    public R getCustById(HttpServletRequest request){
        String id = JwtUtils.getMemberIdByJwtToken(request);
        List<CustomerDetail> customerDetailList=customerService.selectCustById(id);
        return R.success().data("customerDetailList",customerDetailList);
    }

}

