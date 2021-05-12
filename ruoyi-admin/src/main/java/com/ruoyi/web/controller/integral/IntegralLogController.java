package com.ruoyi.web.controller.integral;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.integral.domain.IntegralLog;
import com.ruoyi.integral.service.IIntegralLogService;
import com.ruoyi.web.core.base.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 积分日志 信息操作处理
 * 
 *
 * @date 2021-10-24
 */
@Controller
@RequestMapping("/integral/integralLog")
public class IntegralLogController extends BaseController
{
    private String prefix = "integral/integralLog";
	
	@Autowired
	private IIntegralLogService integralLogService;
	
	@RequiresPermissions("integral:integralLog:view")
	@GetMapping()
	public String integralLog()
	{
	    return prefix + "/integralLog";
	}
	
	/**
	 * 查询积分日志列表
	 */
	@RequiresPermissions("integral:integralLog:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(IntegralLog integralLog)
	{
		startPage();
        List<IntegralLog> list = integralLogService.selectIntegralLogList(integralLog);
		return getDataTable(list);
	}
	
	/**
	 * 新增积分日志
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存积分日志
	 */
	@RequiresPermissions("integral:integralLog:add")
	@Log(title = "积分日志", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(IntegralLog integralLog)
	{		
		return toAjax(integralLogService.insertIntegralLog(integralLog));
	}

	/**
	 * 修改积分日志
	 */
	@GetMapping("/edit/{logId}")
	public String edit(@PathVariable("logId") String logId, ModelMap mmap)
	{
		IntegralLog integralLog = integralLogService.selectIntegralLogById(Integer.parseInt(logId));
		mmap.put("integralLog", integralLog);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存积分日志
	 */
	@RequiresPermissions("integral:integralLog:edit")
	@Log(title = "积分日志", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(IntegralLog integralLog)
	{		
		return toAjax(integralLogService.updateIntegralLog(integralLog));
	}
	
	/**
	 * 删除积分日志
	 */
	@RequiresPermissions("integral:integralLog:remove")
	@Log(title = "积分日志", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(integralLogService.deleteIntegralLogByIds(ids));
	}

    /**
     * 积分日志里撤销审批
     */
    @RequiresPermissions("integral:integralLog:undo")
    @Log(title = "审批管理", businessType = BusinessType.OTHER)
    @GetMapping("/undo/{approvalId}/{status}")
    @ResponseBody
    public AjaxResult undo(@PathVariable("approvalId") String approvalId ,@PathVariable("status") String status)
    {

        return toAjax(integralLogService.undo(Integer.parseInt(approvalId),Integer.parseInt(status)));
    }


}
