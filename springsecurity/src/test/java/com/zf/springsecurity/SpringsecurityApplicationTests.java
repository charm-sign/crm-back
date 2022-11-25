package com.zf.springsecurity;

import com.zf.springsecurity.client.SysClient;
import com.zf.springsecurity.entity.Employee;
import com.zf.springsecurity.mapper.EmployeeMapper2;
import com.zf.springsecurity.mapper.EmployeeMapper2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@SpringBootTest
class SpringsecurityApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private EmployeeMapper2 employeeMapper;
    @Autowired
    private SysClient sysClient;
    @Test
    public void test(){
        List<String> perms = sysClient.getPermsByEmployeeId("1595050089707999234");
        System.out.println(perms);
    }
    @Test
    public void testUserMapper(){
        List<Employee> users = employeeMapper.selectList(null);
        System.out.println(users);
    }

    @Test
    public void TestBCryptPasswordEncoder(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        //加密方法   相同数据，加密后是不一样的
        String encode = passwordEncoder.encode("123456");
        String encode2 = passwordEncoder.encode("1234");
        System.out.println(encode); //$2a$10$b20wTxoVWg6UQ39CwgbwFeoHgc2YdVp6NqA/F221.jZmz57XfEu/i
        System.out.println(encode2); //$2a$10$R9Bqbsdq8HCmPHF0OPFT7uVXVitrzBxnb/F0DU.wmoJdEXZu/GIDm
        //密码校验方法  登录时用户输入的明文  与数据库中查询出保存的密文
        boolean matches = passwordEncoder.matches("1234", "$2a$10$b20wTxoVWg6UQ39CwgbwFeoHgc2YdVp6NqA/F221.jZmz57XfEu/i");
        System.out.println(matches); //true
    }
}
