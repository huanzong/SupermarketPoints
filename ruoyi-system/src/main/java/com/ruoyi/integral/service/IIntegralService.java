package com.ruoyi.integral.service;

import com.ruoyi.integral.domain.Integral;

import java.util.List;

/**
 * 积分 服务层
 * 
 *
 * @date 2021-10-24
 */
public interface IIntegralService 
{
	/**
     * 查询积分信息
     * 
     * @param integralId 积分ID
     * @return 积分信息
     */
	public Integral selectIntegralById(Integer integralId);
	
	/**
     * 查询积分列表
     * 
     * @param integral 积分信息
     * @return 积分集合
     */
	public List<Integral> selectIntegralList(Integral integral);
	
	/**
     * 新增积分
     * 
     * @param integral 积分信息
     * @return 结果
     */
	public int insertIntegral(Integral integral);
	
	/**
     * 修改积分
     * 
     * @param integral 积分信息
     * @return 结果
     */
	public int updateIntegral(Integral integral);
		
	/**
     * 删除积分信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteIntegralByIds(String ids);

	/**
	 * 查询积分榜信息
	 *
	 * @param integral 查询积分榜信息
	 * @return 结果
	 */
	//List<Map> selectUserIntegral(Integral integral);
}
