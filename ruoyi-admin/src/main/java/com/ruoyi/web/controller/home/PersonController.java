package com.ruoyi.web.controller.home;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.integral.domain.IntegralGoods;
import com.ruoyi.integral.domain.IntegralRecord;
import com.ruoyi.integral.domain.SysRecharge;
import com.ruoyi.integral.service.IIntegralGoodsService;
import com.ruoyi.integral.service.IIntegralRecordService;
import com.ruoyi.integral.service.ISysRechargeService;
import com.ruoyi.integral.service.ISysSignInService;
import com.ruoyi.system.domain.SysNotice;
import com.ruoyi.system.domain.SysSignIn;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysNoticeService;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.web.core.base.BaseController;
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

    @Autowired
    private IIntegralGoodsService integralGoodsService;

    @Autowired
    private IIntegralRecordService iIntegralRecordService;

    @Autowired
    private ISysNoticeService noticeService;


    @PostMapping(value = "login")
    public String equals(String loginName, String password, HttpServletRequest request) {
        if (StringUtils.isEmpty(loginName) || StringUtils.isEmpty(password)) {
            request.getSession().setAttribute("MESSAGE", "?????????????????????????????????");
            return "home/index";
        }
        // ??????????????????
        SysUser user = userService.selectUserByLoginName(loginName);
        if (null == user) {
            request.getSession().setAttribute("MESSAGE", "??????????????????");
            return "home/index";
        }
        if (!user.getPassword().equals(encryptPassword(user.getLoginName(), password, user.getSalt()))) {
            request.getSession().setAttribute("MESSAGE", "???????????????????????????");
            return "home/index";
        }
        request.getSession().setAttribute("USER", user);
        request.getSession().setAttribute("MESSAGE", "???????????????");
        return "home/person-center";
    }

    /**
     * ????????????????????????
     *
     * @param mmap
     * @return
     */
    @GetMapping("/personCenter")
    public String personCenter(ModelMap mmap) {

        return "home/person-center";
    }

    /**
     * ????????????
     *
     * @param mmap
     * @return
     */
    @GetMapping("/personCenterMessage")
    public String personCenterInfo(ModelMap mmap) {

        return "home/person-center-message";
    }

    /**
     * ????????????
     *
     * @param mmap
     * @return
     */
    @GetMapping("/liuyan")
    public String liuyan(ModelMap mmap) {

        return "home/liuyan";
    }


    /**
     * ????????????????????????
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
     * ??????????????????
     */
    @PostMapping("/addLiuYan")
    @ResponseBody
    public AjaxResult addSave(SysNotice notice, HttpServletRequest request) {
        SysUser sysUser = (SysUser) request.getSession().getAttribute("USER");
        if (null == sysUser) {
            return AjaxResult.error("??????????????????");
        }
        notice.setCreateBy(sysUser.getLoginName());
        notice.setStatus("0");
        notice.setNoticeTitle(sysUser.getUserName() + "?????????????????????");
        notice.setNoticeType("1");
        return toAjax(noticeService.insertNotice(notice));
    }


    /**
     * ??????????????????
     */
    @PostMapping("/duihuan")
    @ResponseBody
    public TableDataInfo duihuan(HttpServletRequest request) {
        SysUser sysUser = (SysUser) request.getSession().getAttribute("USER");
        if (null == sysUser) {
            TableDataInfo tableDataInfo = new TableDataInfo();
            tableDataInfo.setCode(500);
            return tableDataInfo;
        }
        startPage();
        IntegralRecord integralRecord = new IntegralRecord();
        integralRecord.setUserId(Integer.parseInt(String.valueOf(sysUser.getUserId())));
        List<IntegralRecord> list = iIntegralRecordService.selectIntegralRecordList(integralRecord);
        return getDataTable(list);
    }


    /**
     * ????????????
     */
    @PostMapping("/change")
    @ResponseBody
    public AjaxResult change(Integer productId, HttpServletRequest request) {
        SysUser sysUser = (SysUser) request.getSession().getAttribute("USER");
        if (null == sysUser) {
            return AjaxResult.error(500, "??????");
        }
        IntegralGoods integralGoods = integralGoodsService.selectIntegralGoodsById(productId);
        if (null == integralGoods) {
            return AjaxResult.error(500, "??????");
        }
        if (integralGoods.getDhIntegral() > sysUser.getJiChuIntegral()) {
            return AjaxResult.error(500, "????????????");
        }
        integralGoods.setGoodKc(integralGoods.getGoodKc() - 1);
        if (!StringUtils.isEmpty(integralGoods.getGoodCount())) {
            int count = Integer.parseInt(integralGoods.getGoodCount()) + 1;
            integralGoods.setGoodCount(String.valueOf(count));
        } else {
            integralGoods.setGoodCount("1");
        }
        integralGoodsService.updateIntegralGoods(integralGoods);

        IntegralRecord integralRecord = new IntegralRecord();
        integralRecord.setRecordName(integralGoods.getGoodName());
        integralRecord.setRecordImg(integralGoods.getGoodImg());
        integralRecord.setDhIntegral(integralGoods.getDhIntegral());
        integralRecord.setUserName(sysUser.getUserName());
        integralRecord.setUserId(Integer.parseInt(String.valueOf(sysUser.getUserId())));
        integralRecord.setUserPhone(Long.parseLong(sysUser.getPhonenumber()));
        int sy = sysUser.getJiChuIntegral() - integralGoods.getDhIntegral();
        integralRecord.setSyIntegral(sy);
        integralRecord.setDhCreateTime(new Date());
        integralRecord.setgId(integralGoods.getGoodId());
        integralRecord.setDeptId(Integer.parseInt(String.valueOf(sysUser.getDeptId())));
        integralRecord.setDeptName(sysUser.getDept().getDeptName());
        iIntegralRecordService.insertIntegralRecord(integralRecord);
        sysUser.setJiChuIntegral(sy);
        userService.updateUserInfo(sysUser);
        return AjaxResult.success("??????");
    }


    /**
     * ????????????????????????
     */
    @PostMapping("/goodList")
    @ResponseBody
    public TableDataInfo list(String type) {
        IntegralGoods integralGoods = new IntegralGoods();
        integralGoods.setType(type);
        startPage();
        List<IntegralGoods> list = integralGoodsService.selectIntegralGoodsList(integralGoods);
        return getDataTable(list);
    }


    /**
     * ??????????????????
     */
    @PostMapping("/savePerson")
    @ResponseBody
    public AjaxResult savePerson(SysUser sysUser, HttpServletRequest request) {

        SysUser sysUser1 = (SysUser) request.getSession().getAttribute("USER");
        if (null == sysUser1) {
            return AjaxResult.error("?????????????????????");
        }
        sysUser.setUserId(sysUser1.getUserId());
        userService.updateUserInfo(sysUser);
        SysUser sysUser2 = userService.selectUserById(sysUser1.getUserId());
        request.getSession().setAttribute("USER", sysUser2);
        return toAjax(1);
    }

    /**
     * ????????????????????????
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
     * ????????????????????????
     */
    @Log(title = "????????????", businessType = BusinessType.INSERT)
    @PostMapping("/addSignIn")
    @ResponseBody
    public AjaxResult addSave(HttpServletRequest request) {
        SysUser sysUser = (SysUser) request.getSession().getAttribute("USER");
        if (null == sysUser) {
            return AjaxResult.error("?????????????????????");
        }
        SysSignIn query = new SysSignIn();
        query.setUserId(sysUser.getUserId());
        query.setSignInDate(DateUtils.getDate());
        List<SysSignIn> list = sysSignInService.selectSysSignInList(query);
        if (!CollectionUtils.isEmpty(list)) {
            return AjaxResult.error(500, "????????????");
        }
        SysSignIn sysSignIn = new SysSignIn();
        sysSignIn.setUserId(sysUser.getUserId());
        sysSignIn.setCreateTime(new Date());
        sysSignIn.setSignInDate(DateUtils.getDate());
        return toAjax(sysSignInService.insertSysSignIn(sysSignIn, sysUser, request));
    }


    /**
     * ????????????
     *
     * @param mmap
     * @return
     */
    @GetMapping("/personCenterRecord")
    public String chongzhi(ModelMap mmap) {

        return "home/person-center-recharge-record";
    }


    /**
     * ????????????
     *
     * @param mmap
     * @return
     */
    @GetMapping("/personOrder")
    public String personOrder(ModelMap mmap) {

        return "home/person-center-myorder";
    }


    /**
     * ????????????
     *
     * @param mmap
     * @return
     */
    @GetMapping("/choiceMenus")
    public String integral(ModelMap mmap) {

        return "home/choice-menus";
    }

    /**
     * ??????
     *
     * @param mmap
     * @return
     */
    @GetMapping("/payment")
    public String payment(ModelMap mmap) {

        return "home/payment";
    }

    /**
     * ????????????
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
