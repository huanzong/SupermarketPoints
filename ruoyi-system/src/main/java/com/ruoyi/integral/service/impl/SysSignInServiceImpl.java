package com.ruoyi.integral.service.impl;

import com.ruoyi.integral.domain.IntegralJk;
import com.ruoyi.integral.service.IIntegralJkService;
import com.ruoyi.system.domain.SysSignIn;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.integral.mapper.SysSignInMapper;
import com.ruoyi.integral.service.ISysSignInService;
import com.ruoyi.common.support.Convert;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 签到记录 服务层实现
 *
 * @author sunli
 * @date 2021-05-30
 */
@Service
public class SysSignInServiceImpl implements ISysSignInService {
    @Autowired
    private SysSignInMapper sysSignInMapper;

    @Autowired
    private ISysUserService iSysUserService;

    @Autowired
    private IIntegralJkService integralJkService;

    /**
     * 查询签到记录信息
     *
     * @param id 签到记录ID
     * @return 签到记录信息
     */
    @Override
    public SysSignIn selectSysSignInById(Integer id) {
        return sysSignInMapper.selectSysSignInById(id);
    }

    /**
     * 查询签到记录列表
     *
     * @param sysSignIn 签到记录信息
     * @return 签到记录集合
     */
    @Override
    public List<SysSignIn> selectSysSignInList(SysSignIn sysSignIn) {
        return sysSignInMapper.selectSysSignInList(sysSignIn);
    }

    /**
     * 新增签到记录
     *
     * @param sysSignIn 签到记录信息
     * @return 结果
     */
    @Override
    public int insertSysSignIn(SysSignIn sysSignIn,SysUser sysUser,HttpServletRequest request) {

        IntegralJk integralJk = new IntegralJk();
        integralJk.setJkTitle("签到加积分");
        integralJk.setJkDescribe("签到加积分");
        integralJk.setTypeId(1);
        integralJk.setjIntegral(1);
        integralJk.setIds(String.valueOf(sysUser.getUserId()));
        int total = integralJkService.insertIntegralUserList(integralJk);
        sysUser.setJiChuIntegral(total);
        iSysUserService.updateUserInfo(sysUser);
        request.getSession().setAttribute("USER", sysUser);
        return sysSignInMapper.insertSysSignIn(sysSignIn);
    }

    /**
     * 修改签到记录
     *
     * @param sysSignIn 签到记录信息
     * @return 结果
     */
    @Override
    public int updateSysSignIn(SysSignIn sysSignIn) {
        return sysSignInMapper.updateSysSignIn(sysSignIn);
    }

    /**
     * 删除签到记录对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSysSignInByIds(String ids) {
        return sysSignInMapper.deleteSysSignInByIds(Convert.toStrArray(ids));
    }

}
