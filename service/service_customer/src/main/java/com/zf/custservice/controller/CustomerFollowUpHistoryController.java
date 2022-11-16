package com.zf.custservice.controller;


import com.zf.commonutils.JwtUtils;
import com.zf.commonutils.R;
import com.zf.custservice.entity.CustomerFollowUpHistory;
import com.zf.custservice.entity.dto.CustomerQuery;
import com.zf.custservice.service.CustomerFollowUpHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * <p>
 * 客户跟进历史表 前端控制器
 * </p>
 *
 * @author ZF
 * @since 2022-10-31
 */
@Api(description = "客户跟进历史管理")
@RestController
@RequestMapping("/custService/history")
public class CustomerFollowUpHistoryController {
    @Autowired
    private CustomerFollowUpHistoryService customerFollowUpHistoryService;

    @ApiOperation("分页条件查询跟进历史")
    @PostMapping("list/{pageNo}/{pageSize}")
    public R list(@PathVariable int pageNo, @PathVariable int pageSize, @RequestBody(required = false) CustomerQuery customerQuery) {
        Map<String, Object> map = customerFollowUpHistoryService.selectDetailCustomerFollowUpHistory(pageNo, pageSize, customerQuery);
        return R.success().data(map);
    }

    @ApiOperation("添加跟进历史")
    @PostMapping("add")
    public R add(@RequestBody CustomerFollowUpHistory customerFollowUpHistory, HttpServletRequest request) {
        //添加创建人，为当前登录用户
        String employeeId = JwtUtils.getMemberIdByJwtToken(request);
        customerFollowUpHistory.setInputUser(employeeId);
        customerFollowUpHistoryService.save(customerFollowUpHistory);
        return R.success();
    }

    @ApiOperation("根据id查询跟进历史明细")
    @GetMapping("getDetailById/{id}")
    public R getDetailById(@PathVariable String id) {
        CustomerFollowUpHistory customerFollowUpHistory = customerFollowUpHistoryService.selectCustomerFollowUpHistory(id);
        return R.success().data("customerFollowUpHistory", customerFollowUpHistory);
    }

    @ApiOperation("修改跟进历史")
    @PostMapping("update")
    public R update(@RequestBody CustomerFollowUpHistory customerFollowUpHistory) {
        //不用修改客户名称及创建人
        CustomerFollowUpHistory followUpHistory = new CustomerFollowUpHistory();
        followUpHistory.setId(customerFollowUpHistory.getId());

        followUpHistory.setTraceTime(customerFollowUpHistory.getTraceTime());
        followUpHistory.setTraceDetails(customerFollowUpHistory.getTraceDetails());
        followUpHistory.setTraceType(customerFollowUpHistory.getTraceType());
        followUpHistory.setTraceResult(customerFollowUpHistory.getTraceResult());
        followUpHistory.setComment(customerFollowUpHistory.getComment());
        followUpHistory.setType(customerFollowUpHistory.getType());

        customerFollowUpHistoryService.updateById(followUpHistory);
        return R.success();
    }
}

