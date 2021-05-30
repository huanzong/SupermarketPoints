package com.ruoyi.web.controller.system;

import java.util.List;

import com.ruoyi.system.domain.SysSignIn;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.integral.service.ISysSignInService;
import com.ruoyi.web.core.base.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.common.base.AjaxResult;

/**
 * 签到记录 信息操作处理
 *
 * @author sunli
 * @date 2021-05-30
 */
@Controller
@RequestMapping("/integral/sysSignIn")
public class SysSignInController extends BaseController {
    private String prefix = "integral/sysSignIn";

    @Autowired
    private ISysSignInService sysSignInService;

    @RequiresPermissions("integral:sysSignIn:view")
    @GetMapping()
    public String sysSignIn() {
        return prefix + "/sysSignIn";
    }

    /**
     * 查询签到记录列表
     */
    @RequiresPermissions("integral:sysSignIn:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysSignIn sysSignIn) {
        startPage();
        List<SysSignIn> list = sysSignInService.selectSysSignInList(sysSignIn);
        return getDataTable(list);
    }

    /**
     * 新增签到记录
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

//    /**
//     * 新增保存签到记录
//     */
//    @RequiresPermissions("integral:sysSignIn:add")
//    @Log(title = "签到记录", businessType = BusinessType.INSERT)
//    @PostMapping("/add")
//    @ResponseBody
//    public AjaxResult addSave(SysSignIn sysSignIn) {
//        return toAjax(sysSignInService.insertSysSignIn(sysSignIn));
//    }

    /**
     * 修改签到记录
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap) {
        SysSignIn sysSignIn = sysSignInService.selectSysSignInById(id);
        mmap.put("sysSignIn", sysSignIn);
        return prefix + "/edit";
    }

    /**
     * 修改保存签到记录
     */
    @RequiresPermissions("integral:sysSignIn:edit")
    @Log(title = "签到记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysSignIn sysSignIn) {
        return toAjax(sysSignInService.updateSysSignIn(sysSignIn));
    }

    /**
     * 删除签到记录
     */
    @RequiresPermissions("integral:sysSignIn:remove")
    @Log(title = "签到记录", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(sysSignInService.deleteSysSignInByIds(ids));
    }

}
