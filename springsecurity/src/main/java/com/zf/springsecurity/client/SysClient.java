package com.zf.springsecurity.client;

import com.zf.commonutils.R;
import com.zf.springsecurity.entity.EmployeeDetail;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @ClassName: SysClient
 * @Description: 远程调用
 * @Author: ZF
 * @date: 2022/11/22 17:45
 */
@Component
@FeignClient("service-sys")
public interface SysClient {
    @GetMapping("/sysService/permission/getPermsByEmployeeId/{id}")
   public List<String> getPermsByEmployeeId(@PathVariable("id") String id);

}
