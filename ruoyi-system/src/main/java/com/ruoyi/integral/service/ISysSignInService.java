package com.ruoyi.integral.service;

import com.ruoyi.system.domain.SysSignIn;
import com.ruoyi.system.domain.SysUser;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 签到记录 服务层
 *
 * @author sunli
 * @date 2021-05-30
 */
public interface ISysSignInService {
    /**
     * 查询签到记录信息
     *
     * @param id 签到记录ID
     * @return 签到记录信息
     */
    public SysSignIn selectSysSignInById(Integer id);

    /**
     * 查询签到记录列表
     *
     * @param sysSignIn 签到记录信息
     * @return 签到记录集合
     */
    public List<SysSignIn> selectSysSignInList(SysSignIn sysSignIn);

    /**
     * 新增签到记录
     *
     * @param sysSignIn 签到记录信息
     * @return 结果
     */
    int insertSysSignIn(SysSignIn sysSignIn,SysUser sysUser,HttpServletRequest request);

    /**
     * 修改签到记录
     *
     * @param sysSignIn 签到记录信息
     * @return 结果
     */
    public int updateSysSignIn(SysSignIn sysSignIn);

    /**
     * 删除签到记录信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysSignInByIds(String ids);

}
