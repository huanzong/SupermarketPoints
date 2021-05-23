package com.ruoyi.web.controller.home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "home")
public class HomeController {

    /**
     * 跳转个人中心登录页面
     *
     * @param mmap
     * @return
     */
    @GetMapping("/login")
    public String login(ModelMap mmap) {

        return "home/index";
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

}
