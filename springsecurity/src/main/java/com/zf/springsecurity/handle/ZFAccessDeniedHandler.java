package com.zf.springsecurity.handle;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zf.commonutils.R;
import com.zf.servicebase.exceptionhandler.CrmException;
import com.zf.servicebase.exceptionhandler.GlobalExceptionHandler;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PipedWriter;
import java.io.PrintWriter;
import java.util.HashMap;

/**
 * @ClassName: ZFAccessDeniedHandler
 * @Description: 自定义授权失败处理器
 * @Author: ZF
 * @date: 2022/11/24 14:58
 */
@Component
public class ZFAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        HashMap<String, String> map = new HashMap<>(2);
        map.put("uri", request.getRequestURI());
        map.put("msg", "您没有该权限..........");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setCharacterEncoding("utf-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        ObjectMapper objectMapper = new ObjectMapper();
        String resBody = objectMapper.writeValueAsString(map);
        PrintWriter printWriter = response.getWriter();
        printWriter.print(resBody);
        printWriter.flush();
        printWriter.close();
    }
}
