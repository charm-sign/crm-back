package com.zf.springsecurity.service;

import com.zf.commonutils.R;
import com.zf.springsecurity.entity.Employee;

/**
 * @ClassName: LoginService
 * @Description: TODO
 * @Author: ZF
 * @date: 2022/11/22 18:14
 */
public interface LoginService {

    R login(Employee employee);

    R logout();
}
