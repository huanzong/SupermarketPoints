package com.ruoyi.integral.service.impl;

import com.ruoyi.common.constant.IntegralConstants;
import com.ruoyi.common.support.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.integral.domain.Integral;
import com.ruoyi.integral.domain.IntegralJk;
import com.ruoyi.integral.domain.IntegralLog;
import com.ruoyi.integral.mapper.IntegralJkMapper;
import com.ruoyi.integral.mapper.IntegralLogMapper;
import com.ruoyi.integral.mapper.IntegralMapper;
import com.ruoyi.integral.service.IIntegralJkService;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.domain.SysUserPost;
import com.ruoyi.system.mapper.SysDeptMapper;
import com.ruoyi.system.mapper.SysPostMapper;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.system.mapper.SysUserPostMapper;
import com.ruoyi.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 积分奖扣 服务层实现
 *
 * @date 2021-10-25
 */
@Service
public class IntegralJkServiceImpl implements IIntegralJkService {
    @Autowired
    private IntegralJkMapper integralJkMapper;

    @Autowired
    private IntegralMapper integralMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private IntegralLogMapper integralLogMapper;

    @Autowired
    private SysDeptMapper sysDeptMapper;

    @Autowired
    private SysUserPostMapper sysUserPostMapper;
    @Autowired
    private SysPostMapper sysPostMapper;

    @Autowired
    private ISysUserService iSysUserService;


    /**
     * 查询积分奖扣信息
     *
     * @param jkId 积分奖扣ID
     * @return 积分奖扣信息
     */
    @Override
    public IntegralJk selectIntegralJkById(Integer jkId) {
        return integralJkMapper.selectIntegralJkById(jkId);
    }

    /**
     * 查询积分奖扣列表
     *
     * @param integralJk 积分奖扣信息
     * @return 积分奖扣集合
     */
    @Override
    public List<IntegralJk> selectIntegralJkList(IntegralJk integralJk) {
        return integralJkMapper.selectIntegralJkList(integralJk);
    }

    /**
     * 添加日志积分奖扣信息
     *
     * @param
     * @param integralJk
     * @return 结果
     */
    private int insertIntegralLog(IntegralJk integralJk, SysUser user) {
        SysUserPost userPost = sysUserPostMapper.selectUserById(user.getUserId());

        IntegralLog log = new IntegralLog();
        log.setTypeId(integralJk.getTypeId());
        log.setUserPhone(Long.parseLong(user.getPhonenumber()));
        log.setUserPost(sysPostMapper.selectPostById(userPost.getPostId()).getPostName());
        log.setStatus(IntegralConstants.ZY_INTEGRAL);
        log.setGetTime(integralJk.getJkTime());
        log.setIntegralTitle(integralJk.getJkTitle());
        log.setBianIntegral(integralJk.getjIntegral());
        log.setTypeId(integralJk.getTypeId());
        log.setkIntegral(integralJk.getkIntegral());
        log.setUserName(integralJk.getJkName());
        log.setApprovalNum(integralJk.getJkNum());
        log.setUserDept(integralJk.getDeptName());
        log.setUserImg(integralJk.getJkImg());
        log.setIntegralContent(integralJk.getJkTitle());
        return integralLogMapper.insertIntegralLog(log);
    }

    /**
     * 根据用户名查询积分
     *
     * @param userName 积分奖扣信息
     * @return 结果
     */
    private Integral getUserName(String userName) {

        return integralMapper.selectUserName(userName);
    }

    /**
     * 修改积分奖扣
     *
     * @param integralJk 积分奖扣信息
     * @return 结果
     */
    @Override
    public int updateIntegralJk(IntegralJk integralJk) {
        return integralJkMapper.updateIntegralJk(integralJk);
    }

    /**
     * 删除积分奖扣对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteIntegralJkByIds(String ids) {
        return integralJkMapper.deleteIntegralJkByIds(Convert.toStrArray(ids));
    }

    /**
     * 批量添加员工奖扣
     */
    @Override
    public int insertIntegralUserList(IntegralJk integralJk) {
        Integer total = 0;
        /** 所用的员工的 ids*/
        String[] ids = integralJk.getIds().split(",");
        for (String id : ids) {
            IntegralJk jk = new IntegralJk();
            jk.setJkNum("JKB" + DateUtils.dateTime());
            jk.setJkTime(new Date());
            SysUser user = sysUserMapper.selectUserById(Long.parseLong(id));
            jk.setJkName(user.getUserName());
            jk.setJkPhone(user.getPhonenumber());
            jk.setJkImg(user.getAvatar());
            jk.setJkTitle(integralJk.getJkTitle());
            jk.setJkDescribe(integralJk.getJkDescribe());
            jk.setDeptId(user.getDeptId().intValue());
            jk.setDeptName(sysDeptMapper.selectDeptByName(user.getDeptId().intValue()).getDeptName());
            jk.setTypeId(integralJk.getTypeId());
            jk.setkIntegral(integralJk.getkIntegral());
            /** 总积分 */

            Integral integral = integralMapper.selectByUserId(id);

            if (StringUtils.isNotNull(integralJk.getjIntegral()) && integralJk.getjIntegral() > 0) {
                jk.setjIntegral(integralJk.getjIntegral());
                integral.setCountIntegral(integral.getCountIntegral() + integralJk.getjIntegral());

                if (integral.getAddIntegral() == null) {
                    integral.setAddIntegral(integralJk.getjIntegral());
                } else {
                    integral.setAddIntegral(integral.getAddIntegral() + integralJk.getjIntegral());
                }
            }
            if (StringUtils.isNotNull(integralJk.getkIntegral()) && integralJk.getkIntegral() > 0) {
                jk.setkIntegral(integralJk.getkIntegral());
                if (integral.getDelIntegral() == null) {
                    integral.setDelIntegral(integralJk.getkIntegral());
                } else {
                    integral.setDelIntegral(integral.getDelIntegral() + integralJk.getkIntegral());
                }
                integral.setCountIntegral(integral.getCountIntegral() - integralJk.getkIntegral());
            }

            //修改积分
            int integraNum = integralMapper.updateIntegral(integral);
            //添加日志
            int log = insertIntegralLog(jk, user);

            //添加积分奖扣
            int integralNum = integralJkMapper.insertIntegralJk(jk);

            total = integral.getCountIntegral();


        }
        return total;
    }

    /**
     * 查询会员卡分组人名
     *
     * @return 结果
     */
    /*@Override
	public List<Map<String, String>> jkNameGroup() {
		Map<String, Object> map = new HashMap<>();

		List<Map<String, String>> list = integralJkMapper.selectDeptAndUserName();
		for (Map<String, String> st : list) {
			//对象
			JSONObject str = JSONObject.fromObject(st);

			if (str.has("deptName")) {
				JSONArray tr = str.getJSONArray("deptName");
				System.out.println(tr);

			}
		}

			return null;
		}*/
}


/**
 * 批量新增积分奖扣
 *
 * @param integralJk 积分奖扣信息
 * @param jkNames
 * @return 结果
 */
	/*@Override
	public int insertAllIntegralJk(IntegralJk integralJk, String[] jkNames) {



		return 0;
	}*/


