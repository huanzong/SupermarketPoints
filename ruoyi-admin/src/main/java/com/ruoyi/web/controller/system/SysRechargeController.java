package com.ruoyi.web.controller.system;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.integral.domain.SysRecharge;
import com.ruoyi.integral.service.ISysRechargeService;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.web.core.base.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 充值记录 信息操作处理
 *
 * @author sunli
 * @date 2021-05-30
 */
@Controller
@RequestMapping("/integral/sysRecharge")
public class SysRechargeController extends BaseController {
    private String prefix = "integral/sysRecharge";

    @Autowired
    private ISysRechargeService sysRechargeService;

    @GetMapping()
    public String sysRecharge() {
        return prefix + "/sysRecharge";
    }

    /**
     * 查询充值记录列表
     */
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysRecharge sysRecharge) {
        startPage();
        List<SysRecharge> list = sysRechargeService.selectSysRechargeList(sysRecharge);
        return getDataTable(list);
    }

    /**
     * 新增充值记录
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存充值记录
     */
    @Log(title = "充值记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SysRecharge sysRecharge, HttpServletRequest request) {
        SysUser sysUser = (SysUser) request.getSession().getAttribute("USER");
        if (null == sysUser) {
            return AjaxResult.error("请登录后操作！");
        }
        sysRecharge.setUserId(sysUser.getUserId());
        return toAjax(sysRechargeService.insertSysRecharge(sysRecharge,sysUser,request));
    }

    /**
     * 修改充值记录
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        SysRecharge sysRecharge = sysRechargeService.selectSysRechargeById(id);
        mmap.put("sysRecharge", sysRecharge);
        return prefix + "/edit";
    }

    /**
     * 修改保存充值记录
     */
    @Log(title = "充值记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysRecharge sysRecharge) {
        return toAjax(sysRechargeService.updateSysRecharge(sysRecharge));
    }

    /**
     * 删除充值记录
     */
    @Log(title = "充值记录", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(sysRechargeService.deleteSysRechargeByIds(ids));
    }

}
