package com.ruoyi.integral.service.impl;

import com.ruoyi.common.support.Convert;
import com.ruoyi.integral.domain.Integral;
import com.ruoyi.integral.mapper.IntegralMapper;
import com.ruoyi.integral.service.IIntegralService;
import com.ruoyi.system.domain.SysDept;
import com.ruoyi.system.domain.SysPost;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.domain.SysUserPost;
import com.ruoyi.system.mapper.SysDeptMapper;
import com.ruoyi.system.mapper.SysPostMapper;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.system.mapper.SysUserPostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 积分 服务层实现
 * 
 *
 * @date 2021-10-24
 */
@Service
public class IntegralServiceImpl implements IIntegralService 
{
	@Autowired
	private IntegralMapper integralMapper;
	@Autowired
	private SysDeptMapper sysDeptMapper;
	@Autowired
	private SysPostMapper sysPostMapper;
	@Autowired
	private SysUserPostMapper sysUserPostMapper;
	@Autowired
	private SysUserMapper sysUserMapper;

	/**
     * 查询积分信息
     * 
     * @param integralId 积分ID
     * @return 积分信息
     */
    @Override
	public Integral selectIntegralById(Integer integralId)
	{
	    return integralMapper.selectIntegralById(integralId);
	}
	
	/**
     * 查询积分列表
     * 
     * @param integral 积分信息
     * @return 积分集合
     */
	@Override
	public List<Integral> selectIntegralList(Integral integral)
	{
		List<Integral> integrals =	integralMapper.selectIntegralList(integral);
		for (Integral in : integrals) {

			/*if(integral1.getPostId()!=null){
				integral1.setPostName(sysPostMapper.selectPostById(integral1.getPostId().longValue()).getPostName());
			}
			if (integral1.getDeptId()!=null && integral1.getDeptId()!= 0){
			SysDept dept = sysDeptMapper.selectDeptByName(integral1.getDeptId());
			if(dept !=null){
				integral1.setDeptName(dept.getDeptName());
			}
			}*/
			SysUser user = sysUserMapper.selectUserById(in.getUserId().longValue());
			if (user != null) {
				SysDept dept = sysDeptMapper.selectDeptByName(user.getDeptId().intValue());
				if (dept != null) {
					in.setDeptName(dept.getDeptName());
					in.setDeptId(dept.getDeptId().intValue());
					SysUserPost userPost = sysUserPostMapper.selectUserById(in.getUserId().longValue());
						if(userPost !=null ){
							in.setPostId(userPost.getPostId().intValue());
							SysPost post = sysPostMapper.selectPostById(userPost.getPostId());
							if(post !=null){
								in.setPostName(post.getPostName());
							}


						}
				}
			}
		}
	    return integrals;
	}
	
    /**
     * 新增积分
     * 
     * @param integral 积分信息
     * @return 结果
     */
	@Override
	public int insertIntegral(Integral integral)
	{
	    return integralMapper.insertIntegral(integral);
	}
	
	/**
     * 修改积分
     * 
     * @param integral 积分信息
     * @return 结果
     */
	@Override
	public int updateIntegral(Integral integral)
	{
	    return integralMapper.updateIntegral(integral);
	}

	/**
     * 删除积分对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteIntegralByIds(String ids)
	{
		return integralMapper.deleteIntegralByIds(Convert.toStrArray(ids));
	}

	/**
	 * 查询积分榜信息
	 *
	 * @param integral 查询积分榜信息
	 * @return 结果
	 */
	/*@Override
	public List<Map> selectUserIntegral(Integral integral) {
		return integralMapper.selectUserIntegral(integral);
	}*/
}
