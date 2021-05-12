package com.ruoyi.work.domain;

import com.ruoyi.common.base.BaseEntity;

import java.util.Date;

/**
 * 消费管理表 gzd
 *
 * @date 2021-10-24
 */
public class Gzd extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 工作台主键
     */
    private Integer gztId;

    /**
     * 消费概述
     */
    private String yyTitle;

    /**
     * 消费描述
     */
    private String yyDesc;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 金额
     */
    private String price;

    /**
     * 备注
     */
    private String remark;

    /**
     * 积分兑换状态
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getGztId() {
        return gztId;
    }

    public void setGztId(Integer gztId) {
        this.gztId = gztId;
    }

    public String getYyTitle() {
        return yyTitle;
    }

    public void setYyTitle(String yyTitle) {
        this.yyTitle = yyTitle;
    }

    public String getYyDesc() {
        return yyDesc;
    }

    public void setYyDesc(String yyDesc) {
        this.yyDesc = yyDesc;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public Date getUpdateTime() {
        return updateTime;
    }

    @Override
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
