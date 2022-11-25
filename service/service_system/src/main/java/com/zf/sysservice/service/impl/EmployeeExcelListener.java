package com.zf.sysservice.service.impl;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.read.listener.ReadListener;
import com.alibaba.fastjson.JSON;
import com.zf.servicebase.exceptionhandler.CrmException;
import com.zf.sysservice.entity.DemoData;
import com.zf.sysservice.entity.Employee;
import com.zf.sysservice.service.EmployeeService;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: EmployeeExcelListener
 * @Description: easyExcel读取数据并写入的监听器
 * @Author: ZF
 * @date: 2022/11/8 19:32
 */
@Slf4j
public class EmployeeExcelListener extends AnalysisEventListener<DemoData> {
    public EmployeeService employeeService;

    public EmployeeExcelListener() {
    }
    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 5;
    List<DemoData> list = new ArrayList<DemoData>();
    public EmployeeExcelListener(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    //    这个每一条数据解析都会来调用
    @Override
    public void invoke(DemoData data, AnalysisContext analysisContext) {
        if (data == null) {
            throw new CrmException(20001, "请导入有数据的文件！");
        }
       log.info ("解析到一条数据:{}", JSON.toJSONString(data));
        Employee employee = new Employee();
        employee.setName(data.getName());
        employee.setPassword(data.getPassword());
        employee.setAge(data.getAge());
        employee.setEmail(data.getEmail());
        employeeService.save(employee);
//        list.add(data);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
/*        if (list.size() >= BATCH_COUNT) {
            saveData();
            // 存储完成清理 list
            list.clear();
        }*/
    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
// 这里也要保存数据，确保最后遗留的数据也存储到数据库
//        saveData();
    }
    /**
     * 加上存储数据库
     */
 /*   private void saveData() {
        log.info("{}条数据，开始存储数据库！", list.size());
        employeeService.saveBatchExcel(list);
        log.info("存储数据库成功！");
    }*/
}
