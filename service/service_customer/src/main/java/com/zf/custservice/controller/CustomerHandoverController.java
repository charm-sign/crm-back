package com.zf.custservice.controller;


import com.zf.commonutils.R;
import com.zf.custservice.entity.CustomerHandover;
import com.zf.custservice.entity.dto.CustomerQuery;
import com.zf.custservice.service.CustomerHandoverService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 * 客户移交历史表 前端控制器
 * </p>
 *
 * @author ZF
 * @since 2022-10-31
 */
@Api(description = "移交历史管理")
@RestController
@RequestMapping("/custService/handover")
public class CustomerHandoverController {
    @Autowired
    private CustomerHandoverService customerHandoverService;

    @ApiOperation("分页条件查询移交历史")
    @PostMapping("list/{pageNo}/{pageSize}")
    @PreAuthorize("hasAuthority('transferHistory:list')")
    public R list (@PathVariable int pageNo, @PathVariable int pageSize, @RequestBody(required = false) CustomerQuery customerQuery){
        Map<String,Object> map =customerHandoverService.selectDetailCustomerHandoverHistory(pageNo,pageSize,customerQuery);
        return R.success().data(map);
    }


    @ApiOperation("添加移交记录并修改客户（营销人员）")
    @PostMapping("add")
    @PreAuthorize("hasAuthority('CH:add')")
    public R add(@RequestBody CustomerHandover customerHandover, HttpServletRequest request){
        customerHandoverService.saveHandoverWithUpdateCustomer(customerHandover,request);
        return R.success();
    }

}

