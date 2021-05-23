package com.ruoyi.web.controller.home;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysUserService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "person")
public class PersonController {

    @Autowired
    private ISysUserService userService;


    @PostMapping(value = "login")
    public String equals(String loginName, String password, HttpServletRequest request) {
        if (StringUtils.isEmpty(loginName) || StringUtils.isEmpty(password)) {
            request.getSession().setAttribute("MESSAGE", "用户名或密码不能为空！");
            return "home/index";
        }
        // 查询用户信息
        SysUser user = userService.selectUserByLoginName(loginName);
        if (null == user) {
            request.getSession().setAttribute("MESSAGE", "用户不存在！");
            return "home/index";
        }
        if (!user.getPassword().equals(encryptPassword(user.getLoginName(), password, user.getSalt()))) {
            request.getSession().setAttribute("MESSAGE", "用户名或密码错误！");
            return "home/index";
        }
        request.getSession().setAttribute("USER", user);
        request.getSession().setAttribute("MESSAGE", "登录成功！");
        return "home/person-center";
    }

    private String encryptPassword(String username, String password, String salt) {
        return new Md5Hash(username + password + salt).toHex();
    }
}
