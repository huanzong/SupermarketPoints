package com.ruoyi.integral.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.base.BaseEntity;
import java.util.Date;

/**
 * 商品兑换记录日志表 integral_record_log
 * 
 *
 * @date 2021-11-03
 */
public class IntegralRecordLog extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键 */
	private Integer recordLogId;
	/** 记录商品名称 */
	private String recordName;
	/** 记录商品封面 */
	private String recordImg;
	/** 兑换积分 */
	private Integer dhIntegral;
	/** 兑换姓名 */
	private String userName;
	/** 兑换人手机号 */
	private Long userPhone;
	/** 剩余积分 */
	private Integer syIntegral;
	/** 兑换时间 */
	private Date dhCreateTime;
	/** 审核时间 */
	private Date shTime;
	/** 状态(0 审核中 1,审核通过 ，2审核不通过) */
	private Integer status;
	/** 商品id */
	private Integer gId;
	/** 备注 */
	private String remark;

	public void setRecordLogId(Integer recordLogId) 
	{
		this.recordLogId = recordLogId;
	}

	public Integer getRecordLogId() 
	{
		return recordLogId;
	}
	public void setRecordName(String recordName) 
	{
		this.recordName = recordName;
	}

	public String getRecordName() 
	{
		return recordName;
	}
	public void setRecordImg(String recordImg) 
	{
		this.recordImg = recordImg;
	}

	public String getRecordImg() 
	{
		return recordImg;
	}
	public void setDhIntegral(Integer dhIntegral) 
	{
		this.dhIntegral = dhIntegral;
	}

	public Integer getDhIntegral() 
	{
		return dhIntegral;
	}
	public void setUserName(String userName) 
	{
		this.userName = userName;
	}

	public String getUserName() 
	{
		return userName;
	}

	public Long getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(Long userPhone) {
		this.userPhone = userPhone;
	}

	public Integer getgId() {
		return gId;
	}

	public void setgId(Integer gId) {
		this.gId = gId;
	}

	public void setSyIntegral(Integer syIntegral)
	{
		this.syIntegral = syIntegral;
	}

	public Integer getSyIntegral() 
	{
		return syIntegral;
	}
	public void setDhCreateTime(Date dhCreateTime) 
	{
		this.dhCreateTime = dhCreateTime;
	}

	public Date getDhCreateTime() 
	{
		return dhCreateTime;
	}
	public void setShTime(Date shTime) 
	{
		this.shTime = shTime;
	}

	public Date getShTime() 
	{
		return shTime;
	}
	public void setStatus(Integer status) 
	{
		this.status = status;
	}

	public Integer getStatus() 
	{
		return status;
	}
	public void setGId(Integer gId) 
	{
		this.gId = gId;
	}

	public Integer getGId() 
	{
		return gId;
	}
	public void setRemark(String remark) 
	{
		this.remark = remark;
	}

	public String getRemark() 
	{
		return remark;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("recordLogId", getRecordLogId())
            .append("recordName", getRecordName())
            .append("recordImg", getRecordImg())
            .append("dhIntegral", getDhIntegral())
            .append("userName", getUserName())
            .append("userPhone", getUserPhone())
            .append("syIntegral", getSyIntegral())
            .append("dhCreateTime", getDhCreateTime())
            .append("shTime", getShTime())
            .append("status", getStatus())
            .append("gId", getGId())
            .append("remark", getRemark())
            .toString();
    }
}
