package com.ruoyi.web.controller.home;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.integral.domain.SysRecharge;
import com.ruoyi.integral.service.ISysRechargeService;
import com.ruoyi.integral.service.ISysSignInService;
import com.ruoyi.system.domain.SysSignIn;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.web.core.base.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(value = "person")
public class PersonController extends BaseController {

    @Autowired
    private ISysUserService userService;

    @Autowired
    private ISysSignInService sysSignInService;

    @Autowired
    private ISysRechargeService sysRechargeService;


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
     * 查询签到记录列表
     */
    @PostMapping("/signList")
    @ResponseBody
    public TableDataInfo signList(HttpServletRequest request) {

        SysUser sysUser = (SysUser) request.getSession().getAttribute("USER");
        if (null == sysUser) {
            TableDataInfo tableDataInfo = new TableDataInfo();
            tableDataInfo.setCode(500);
            return tableDataInfo;
        }
        SysSignIn sysSignIn = new SysSignIn();
        sysSignIn.setUserId(sysUser.getUserId());
        startPage();
        List<SysSignIn> list = sysSignInService.selectSysSignInList(sysSignIn);
        return getDataTable(list);
    }



    /**
     * 保存个人信息
     */
    @PostMapping("/savePerson")
    @ResponseBody
    public AjaxResult savePerson(SysUser sysUser,HttpServletRequest request) {

        SysUser sysUser1 = (SysUser) request.getSession().getAttribute("USER");
        if (null == sysUser1) {
            return AjaxResult.error("请登录后操作！");
        }
        sysUser.setUserId(sysUser1.getUserId());
        userService.updateUserInfo(sysUser);
        SysUser sysUser2 = userService.selectUserById(sysUser1.getUserId());
        request.getSession().setAttribute("USER", sysUser2);
        return toAjax(1);
    }

    /**
     * 查询充值记录列表
     */
    @PostMapping("/rechargeList")
    @ResponseBody
    public TableDataInfo rechargeList(HttpServletRequest request) {

        SysUser sysUser = (SysUser) request.getSession().getAttribute("USER");
        if (null == sysUser) {
            TableDataInfo tableDataInfo = new TableDataInfo();
            tableDataInfo.setCode(500);
            return tableDataInfo;
        }
        SysRecharge sysRecharge = new SysRecharge();
        sysRecharge.setUserId(sysUser.getUserId());
        startPage();
        List<SysRecharge> list = sysRechargeService.selectSysRechargeList(sysRecharge);
        return getDataTable(list);
    }

    /**
     * 新增保存签到记录
     */
    @Log(title = "签到记录", businessType = BusinessType.INSERT)
    @PostMapping("/addSignIn")
    @ResponseBody
    public AjaxResult addSave(HttpServletRequest request) {
        SysUser sysUser = (SysUser) request.getSession().getAttribute("USER");
        if (null == sysUser) {
            return AjaxResult.error("请登录后操作！");
        }
        SysSignIn query = new SysSignIn();
        query.setUserId(sysUser.getUserId());
        query.setSignInDate(DateUtils.getDate());
        List<SysSignIn> list = sysSignInService.selectSysSignInList(query);
        if (!CollectionUtils.isEmpty(list)) {
            return AjaxResult.error(500, "您已签到");
        }
        SysSignIn sysSignIn = new SysSignIn();
        sysSignIn.setUserId(sysUser.getUserId());
        sysSignIn.setCreateTime(new Date());
        sysSignIn.setSignInDate(DateUtils.getDate());
        return toAjax(sysSignInService.insertSysSignIn(sysSignIn, sysUser, request));
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

    /**
     * 支付成功
     *
     * @param mmap
     * @return
     */
    @GetMapping("/paymentSuccess")
    public String paymentSuccess(ModelMap mmap) {

        return "home/payment-success";
    }


    private String encryptPassword(String username, String password, String salt) {
        return new Md5Hash(username + password + salt).toHex();
    }
}
