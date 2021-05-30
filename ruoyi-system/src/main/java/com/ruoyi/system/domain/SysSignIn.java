package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.base.BaseEntity;
import java.util.Date;

/**
 * 签到记录表 sys_sign_in
 * 
 * @author sunli
 * @date 2021-05-30
 */
public class SysSignIn extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Integer id;
	/**  */
	private Long userId;
	/** 签到日期 */
	private String signInDate;
	/** 签到时间 */
	private Date createTime;

	public void setId(Integer id) 
	{
		this.id = id;
	}

	public Integer getId() 
	{
		return id;
	}
	public void setUserId(Long userId)
	{
		this.userId = userId;
	}

	public Long getUserId()
	{
		return userId;
	}
	public void setSignInDate(String signInDate)
	{
		this.signInDate = signInDate;
	}

	public String getSignInDate()
	{
		return signInDate;
	}
	public void setCreateTime(Date createTime) 
	{
		this.createTime = createTime;
	}

	public Date getCreateTime() 
	{
		return createTime;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("signInDate", getSignInDate())
            .append("createTime", getCreateTime())
            .toString();
    }
}
