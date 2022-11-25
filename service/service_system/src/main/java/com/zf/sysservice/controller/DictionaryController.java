package com.zf.sysservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zf.commonutils.R;
import com.zf.sysservice.entity.Dictionary;
import com.zf.sysservice.entity.DictionaryDetails;
import com.zf.sysservice.service.DictionaryDetailsService;
import com.zf.sysservice.service.DictionaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 字典表 前端控制器
 * </p>
 *
 * @author ZF
 * @since 2022-10-29
 */
@Api(description = "字典管理")
@RestController
@RequestMapping("/sysService/dictionary")
public class DictionaryController {
    @Autowired
    private DictionaryService dictionaryService;

    @Autowired
    private DictionaryDetailsService detailsService;
    @ApiOperation("添加字典")
    @PostMapping("add")
    @PreAuthorize("hasAuthority('dictionaryContents:addOrUpdate')")
    public R add(@RequestBody Dictionary dictionary){
        boolean save = dictionaryService.save(dictionary);
        if (save){
            return R.success();
        }else {
            return R.error();
        }
    }
    @ApiOperation("分页条件查询字典列表")
    @PostMapping("conditionList/{pageNo}/{pageSize}")
    @PreAuthorize("hasAuthority('dictionaryContents:list')")
    public R conditionList(@PathVariable int pageNo, @PathVariable int pageSize, @RequestBody(required = false) Dictionary dictionary) {
        Page<Dictionary> page = new Page<>(pageNo, pageSize);
        LambdaQueryWrapper<Dictionary> lqw = new LambdaQueryWrapper<>();
        lqw.like(ObjectUtils.isNotEmpty(dictionary.getName()), Dictionary::getName, dictionary.getName())
                .orderByDesc(Dictionary::getCreateTime);
        IPage<Dictionary> page1 = dictionaryService.page(page, lqw);
        List<Dictionary> dictionaryList = page1.getRecords();
        long total = page1.getTotal();
        return R.success().data("dictionaryList", dictionaryList).data("total", total);
    }

    @ApiOperation("根据id查询字典信息")
    @GetMapping("getInfoById/{id}")
    public R getInfoById(@PathVariable String id) {
        Dictionary dictionary = dictionaryService.getById(id);
        return R.success().data("dictionary", dictionary);
    }

    @ApiOperation("修改字典信息")
    @PostMapping("update")
    @PreAuthorize("hasAuthority('dictionaryContents:addOrUpdate')")
    public R update(@RequestBody Dictionary dictionary){
        dictionaryService.updateById(dictionary);
        return R.success();
    }

    @ApiOperation("根据父ID查询字典明细")
    @GetMapping("getDetail/{parentId}")
    public R getDetail(@PathVariable String parentId){
        LambdaQueryWrapper<DictionaryDetails> lqw = new LambdaQueryWrapper<>();
        lqw.eq(DictionaryDetails::getParentId,parentId);
        List<DictionaryDetails> detailsList = detailsService.list(lqw);
        return R.success().data("detailsList",detailsList);
    }

    @ApiOperation("查询字典明细列表")
    @GetMapping("detailList")
    public R detailList(){
        List<Dictionary> dictionaryDetailList=dictionaryService.selectDictionaryDetailList();
        return R.success().data("dictionaryDetailList",dictionaryDetailList);
    }

    @GetMapping("detailList2")
    @PreAuthorize("hasAnyAuthority('dictionaryDetails:list')")
    public R detailList2(){
        List<Dictionary> dictionaryDetailList=dictionaryService.selectDictionaryDetailList();
        return R.success().data("dictionaryDetailList",dictionaryDetailList);
    }

}

