package com.zf.sysservice.controller;


import com.zf.commonutils.R;
import com.zf.sysservice.entity.Dictionary;
import com.zf.sysservice.entity.DictionaryDetails;
import com.zf.sysservice.service.DictionaryDetailsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ZF
 * @since 2022-10-30
 */
@Api(description = "字典明细管理")
@RestController
@RequestMapping("/sysService/dictionaryDetails")
public class DictionaryDetailsController {
    @Autowired
    private DictionaryDetailsService detailsService;

    @ApiOperation("添加字典明细（根据父ID）")
    @PostMapping("add/{parentId}")
    @PreAuthorize("hasAuthority('dictionaryDetails:addOrUpdate')")
    public R add(@PathVariable String parentId, @RequestBody DictionaryDetails dictionaryDetails) {
        dictionaryDetails.setParentId(parentId);
        detailsService.save(dictionaryDetails);
        return R.success();
    }

    @ApiOperation("根据id查询字典明细信息")
    @GetMapping("getInfoById/{id}")
    public R getInfoById(@PathVariable String id) {
        DictionaryDetails dictionaryDetails = detailsService.getById(id);
        return R.success().data("dictionaryDetails", dictionaryDetails);
    }

    @ApiOperation("修改明细信息")
    @PostMapping("update")
    @PreAuthorize("hasAuthority('dictionaryDetails:addOrUpdate')")
    public R update(@RequestBody DictionaryDetails dictionaryDetails){
        detailsService.updateById(dictionaryDetails);
        return R.success();
    }
}

