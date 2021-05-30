package com.ruoyi.integral.service;

import com.ruoyi.integral.domain.SysRecharge;
import com.ruoyi.system.domain.SysUser;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 充值记录 服务层
 *
 * @author sunli
 * @date 2021-05-30
 */
public interface ISysRechargeService {
    /**
     * 查询充值记录信息
     *
     * @param id 充值记录ID
     * @return 充值记录信息
     */
    public SysRecharge selectSysRechargeById(Integer id);

    /**
     * 查询充值记录列表
     *
     * @param sysRecharge 充值记录信息
     * @return 充值记录集合
     */
    public List<SysRecharge> selectSysRechargeList(SysRecharge sysRecharge);

    /**
     * 新增充值记录
     *
     * @param sysRecharge 充值记录信息
     * @return 结果
     */
    int insertSysRecharge(SysRecharge sysRecharge,SysUser sysUser,HttpServletRequest request);

    /**
     * 修改充值记录
     *
     * @param sysRecharge 充值记录信息
     * @return 结果
     */
    public int updateSysRecharge(SysRecharge sysRecharge);

    /**
     * 删除充值记录信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysRechargeByIds(String ids);

}
