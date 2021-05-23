package com.ruoyi.web.controller.config;

import com.ruoyi.system.domain.SysUser;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConfigPathInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("执行了拦截器");
        //统一拦截（查询当前session是否存在user）(这里user会在每次登陆成功后，写入session)
        SysUser user = (SysUser) request.getSession().getAttribute("USER");
        if (user != null) {
            return true;
        }
        response.sendRedirect(request.getContextPath() + "/home/login");
        return false;
    }
}
