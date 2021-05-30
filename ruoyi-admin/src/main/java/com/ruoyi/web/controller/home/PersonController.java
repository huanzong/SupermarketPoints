package com.ruoyi.web.controller.home;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysUserService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
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

    /**
     * 跳转个人中心页面
     *
     * @param mmap
     * @return
     */
    @GetMapping("/personCenter")
    public String personCenter(ModelMap mmap) {

        return "home/person-center";
    }

    /**
     * 个人信息
     *
     * @param mmap
     * @return
     */
    @GetMapping("/personCenterMessage")
    public String personCenterInfo(ModelMap mmap) {

        return "home/person-center-message";
    }


    /**
     * 充值记录
     *
     * @param mmap
     * @return
     */
    @GetMapping("/personCenterRecord")
    public String chongzhi(ModelMap mmap) {

        return "home/person-center-recharge-record";
    }


    /**
     * 消费记录
     *
     * @param mmap
     * @return
     */
    @GetMapping("/personOrder")
    public String personOrder(ModelMap mmap) {

        return "home/person-center-myorder";
    }


    /**
     * 商品兑换
     *
     * @param mmap
     * @return
     */
    @GetMapping("/choiceMenus")
    public String integral(ModelMap mmap) {

        return "home/choice-menus";
    }

    /**
     * 充值
     *
     * @param mmap
     * @return
     */
    @GetMapping("/payment")
    public String payment(ModelMap mmap) {

        return "home/payment";
    }






    private String encryptPassword(String username, String password, String salt) {
        return new Md5Hash(username + password + salt).toHex();
    }
}
