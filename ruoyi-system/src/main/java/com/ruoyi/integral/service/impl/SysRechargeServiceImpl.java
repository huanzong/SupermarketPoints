package com.ruoyi.integral.service.impl;

import com.ruoyi.common.support.Convert;
import com.ruoyi.integral.domain.IntegralJk;
import com.ruoyi.integral.domain.SysRecharge;
import com.ruoyi.integral.mapper.SysRechargeMapper;
import com.ruoyi.integral.service.IIntegralJkService;
import com.ruoyi.integral.service.ISysRechargeService;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * 充值记录 服务层实现
 *
 * @author sunli
 * @date 2021-05-30
 */
@Service
public class SysRechargeServiceImpl implements ISysRechargeService {
    @Autowired
    private SysRechargeMapper sysRechargeMapper;

    @Autowired
    private ISysUserService iSysUserService;

    @Autowired
    private IIntegralJkService integralJkService;

    /**
     * 查询充值记录信息
     *
     * @param id 充值记录ID
     * @return 充值记录信息
     */
    @Override
    public SysRecharge selectSysRechargeById(Integer id) {
        return sysRechargeMapper.selectSysRechargeById(id);
    }

    /**
     * 查询充值记录列表
     *
     * @param sysRecharge 充值记录信息
     * @return 充值记录集合
     */
    @Override
    public List<SysRecharge> selectSysRechargeList(SysRecharge sysRecharge) {
        return sysRechargeMapper.selectSysRechargeList(sysRecharge);
    }

    /**
     * 新增充值记录
     *
     * @param sysRecharge 充值记录信息
     * @return 结果
     */
    @Override
    public int insertSysRecharge(SysRecharge sysRecharge, SysUser sysUser, HttpServletRequest request) {
        int price = 0;
        if (null != sysUser.getIntegral()) {
            price = sysUser.getIntegral();
        }
        price += sysRecharge.getMoney();
        IntegralJk integralJk = new IntegralJk();
        integralJk.setJkTitle("充值加积分");
        integralJk.setJkDescribe("充值加积分");
        integralJk.setTypeId(2);
        integralJk.setjIntegral(sysRecharge.getMoney());
        integralJk.setIds(String.valueOf(sysUser.getUserId()));
        int total = integralJkService.insertIntegralUserList(integralJk);
        sysUser.setIntegral(price);
        sysUser.setJiChuIntegral(total);
        iSysUserService.updateUserInfo(sysUser);
        request.getSession().setAttribute("USER", sysUser);
        sysRecharge.setCreateTime(new Date());
        return sysRechargeMapper.insertSysRecharge(sysRecharge);
    }

    /**
     * 修改充值记录
     *
     * @param sysRecharge 充值记录信息
     * @return 结果
     */
    @Override
    public int updateSysRecharge(SysRecharge sysRecharge) {
        return sysRechargeMapper.updateSysRecharge(sysRecharge);
    }

    /**
     * 删除充值记录对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSysRechargeByIds(String ids) {
        return sysRechargeMapper.deleteSysRechargeByIds(Convert.toStrArray(ids));
    }

}
