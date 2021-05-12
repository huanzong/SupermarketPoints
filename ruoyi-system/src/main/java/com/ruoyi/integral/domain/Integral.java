package com.ruoyi.integral.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.base.BaseEntity;

/**
 * 积分表 integral
 * 
 *
 * @date 2021-10-24
 */
public class Integral extends BaseEntity
{
	private static final long serialVersionUID = 1L;

	/** 积分主键 */
	private Integer integralId;
	/** 关联那个员工 */
	private Integer userId;
	/** 员工姓名 */
	private String userName;
	/** 员工手机号 */
	private String userPhone;
	/** 总积分 */
	private Integer countIntegral;
	/** 扣除积分 */
	private Integer delIntegral;
	/** 奖励积分 */
	private Integer addIntegral;
	/** 类型 */
	private Integer typeId;
	/** 职位id */
	private Integer postId;
	/** 会员卡id */
	private Integer deptId;
	/** 职位名称 */
	private String postName;
	/** 会员卡名称 */
	private String deptName;

	public Integer getIntegralId() {
		return integralId;
	}

	public void setIntegralId(Integer integralId) {
		this.integralId = integralId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public Integer getCountIntegral() {
		return countIntegral;
	}

	public void setCountIntegral(Integer countIntegral) {
		this.countIntegral = countIntegral;
	}

	public Integer getDelIntegral() {
		return delIntegral;
	}

	public void setDelIntegral(Integer delIntegral) {
		this.delIntegral = delIntegral;
	}

	public Integer getAddIntegral() {
		return addIntegral;
	}

	public void setAddIntegral(Integer addIntegral) {
		this.addIntegral = addIntegral;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public Integer getPostId() {
		return postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String toString() {
		return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
				.append("integralId", getIntegralId())
				.append("userId", getUserId())
				.append("userName", getUserName())
				.append("userPhone", getUserPhone())
				.append("countIntegral", getCountIntegral())
				.append("delIntegral", getDelIntegral())
				.append("addIntegral", getAddIntegral())
				.append("typeId", getTypeId())
				.append("postId", getPostId())
				.append("deptId", getDeptId())
				.toString();
	}
}
