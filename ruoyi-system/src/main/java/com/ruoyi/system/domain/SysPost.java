package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.base.BaseEntity;

/**
 * 会员等级表 sys_post
 * 
 *
 */
public class SysPost extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 会员等级序号 */
    @Excel(name = "会员等级序号")
    private Long postId;

    /** 会员等级编码 */
    /** @Excel(name = "会员等级编码")*/
    private String postCode;

    /** 会员等级名称 */
    @Excel(name = "会员等级名称")
    private String postName;

    /** 会员等级排序 */
    @Excel(name = "会员等级排序")
    private String postSort;

    /** 状态（0正常 1停用） */
    @Excel(name = "状态")
    private String status;

    /** 崗位積分 */
    @Excel(name = "状态")
    private String integral;

    public String getIntegral() {
        return integral;
    }

    public void setIntegral(String integral) {
        this.integral = integral;
    }


    /** 用户是否存在此会员等级标识 默认不存在 */
    private boolean flag = false;

    public Long getPostId()
    {
        return postId;
    }

    public void setPostId(Long postId)
    {
        this.postId = postId;
    }

    public String getPostCode()
    {
        return postCode;
    }

    public void setPostCode(String postCode)
    {
        this.postCode = postCode;
    }

    public String getPostName()
    {
        return postName;
    }

    public void setPostName(String postName)
    {
        this.postName = postName;
    }

    public String getPostSort()
    {
        return postSort;
    }

    public void setPostSort(String postSort)
    {
        this.postSort = postSort;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public boolean isFlag()
    {
        return flag;
    }

    public void setFlag(boolean flag)
    {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("postId", getPostId())
            .append("postCode", getPostCode())
            .append("postName", getPostName())
            .append("postSort", getPostSort())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
             .append("integral", getIntegral())
            .toString();
    }
}
