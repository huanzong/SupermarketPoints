package com.ruoyi.integral.mapper;

import com.ruoyi.integral.domain.IntegralRecordLog;
import java.util.List;	

/**
 * 商品兑换记录日志 数据层
 * 
 *
 * @date 2021-11-03
 */
public interface IntegralRecordLogMapper 
{
	/**
     * 查询商品兑换记录日志信息
     * 
     * @param recordLogId 商品兑换记录日志ID
     * @return 商品兑换记录日志信息
     */
	public IntegralRecordLog selectIntegralRecordLogById(Integer recordLogId);
	
	/**
     * 查询商品兑换记录日志列表
     * 
     * @param integralRecordLog 商品兑换记录日志信息
     * @return 商品兑换记录日志集合
     */
	public List<IntegralRecordLog> selectIntegralRecordLogList(IntegralRecordLog integralRecordLog);
	
	/**
     * 新增商品兑换记录日志
     * 
     * @param integralRecordLog 商品兑换记录日志信息
     * @return 结果
     */
	public int insertIntegralRecordLog(IntegralRecordLog integralRecordLog);
	
	/**
     * 修改商品兑换记录日志
     * 
     * @param integralRecordLog 商品兑换记录日志信息
     * @return 结果
     */
	public int updateIntegralRecordLog(IntegralRecordLog integralRecordLog);
	
	/**
     * 删除商品兑换记录日志
     * 
     * @param recordLogId 商品兑换记录日志ID
     * @return 结果
     */
	public int deleteIntegralRecordLogById(Integer recordLogId);
	
	/**
     * 批量删除商品兑换记录日志
     * 
     * @param recordLogIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteIntegralRecordLogByIds(String[] recordLogIds);
	
}