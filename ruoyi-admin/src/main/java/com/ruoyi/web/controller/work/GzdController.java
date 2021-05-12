package com.ruoyi.web.controller.work;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.web.core.base.BaseController;
import com.ruoyi.work.domain.Gzd;
import com.ruoyi.work.service.IGzdService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 消费管理 信息操作处理
 *
 * @date 2021-10-24
 */
@Controller
@RequestMapping("/work/gzd")
public class GzdController extends BaseController {
    private String prefix = "work/gzd";

    @Autowired
    private IGzdService gzdService;

    @RequiresPermissions("work:gzd:view")
    @GetMapping()
    public String gzd() {
        return prefix + "/gzd";
    }

    /**
     * 查询消费管理列表
     */
    @RequiresPermissions("work:gzd:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Gzd gzd) {
        startPage();
        List<Gzd> list = gzdService.selectGzdList(gzd);
        return getDataTable(list);
    }

    /**
     * 新增消费管理
     */
    @GetMapping("/add")
    public String add() {



        return prefix + "/add";
    }

    /**
     * 新增保存消费管理
     */
    @RequiresPermissions("work:gzd:add")
    @Log(title = "消费管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Gzd gzd) {
        if (null == gzd) {
            return AjaxResult.error("参数有误");
        }
        gzd.setCreateTime(new Date());
        gzd.setUpdateTime(new Date());
        gzd.setStatus(0);
        return toAjax(gzdService.insertGzd(gzd));
    }

//    /**
//     * 新增保存消费管理
//     * 上传图片
//     */
//    @RequiresPermissions("work:gzd:add")
//    @Log(title = "消费管理", businessType = BusinessType.INSERT)
//    @PostMapping("/add")
//    @ResponseBody
//    public Map<String, Object> addSave(Gzd gzd, HttpServletRequest request, HttpServletResponse response) {
//
//        // ResourceBundle bundle = PropertyResourceBundle.getBundle("application");
//        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
//        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
//        //  String rootPath = bundle.getString("upLoadUrl");
//        String filePath = "D:/";
//        Map<String, Object> map = new HashMap<>();
//        map = uploadFiles(filePath, fileMap);
//        return map;
//    }
//
//    public Map<String, Object> uploadFiles(String savePath, Map<String, MultipartFile> fiLeMap) {
//        Map<String, Object> map = new HashMap<>();
//        try {
//            String fileName = "";
//            if (fiLeMap != null) {
//                for (Map.Entry<String, MultipartFile> entity : fiLeMap.entrySet()) {
//                    MultipartFile f = entity.getValue();
//                    if (f != null && !f.isEmpty()) {
//                        String uuid = UUID.randomUUID().toString();
//                        fileName = uuid + "#" + f.getOriginalFilename();
//                        File newFile = new File(savePath + "/" + fileName);
//                        f.transferTo(newFile);
//                    }
//                }
//            }
//            map.put("success", true);
//            map.put("fileName", fileName);
//            return map;
//        } catch (Exception e) {
//            map.put("success", false);
//            return map;
//        }
//    }

    /**
     * 修改消费管理
     */
    @GetMapping("/edit/{gztId}")
    public String edit(@PathVariable("gztId") Integer gztId, ModelMap mmap) {
        Gzd gzd = gzdService.selectGzdById(gztId);
        mmap.put("gzd", gzd);
        return prefix + "/edit";
    }

    /**
     * 修改保存消费管理
     */
    @RequiresPermissions("work:gzd:edit")
    @Log(title = "消费管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Gzd gzd) {
        return toAjax(gzdService.updateGzd(gzd));
    }

    /**
     * 删除消费管理
     */
    @RequiresPermissions("work:gzd:remove")
    @Log(title = "消费管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(gzdService.deleteGzdByIds(ids));
    }

}
