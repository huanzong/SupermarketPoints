package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.SysUserPost;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户与会员等级 表 数据层
 * 
 *
 */
public interface SysUserPostMapper
{
    /**
     * 通过用户ID删除用户和会员等级关联
     * 
     * @param userId 用户ID
     * @return 结果
     */
    public int deleteUserPostByUserId(Long userId);
    
    /**
     * 通过会员等级ID查询会员等级使用数量
     * 
     * @param postId 会员等级ID
     * @return 结果
     */
    public int countUserPostById(Long postId);
    
    /**
     * 批量删除用户和会员等级关联
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteUserPost(Long[] ids);

    /**
     * 批量新增用户会员等级信息
     * 
     * @param userPostList 用户角色列表
     * @return 结果
     */
    public int batchUserPost(List<SysUserPost> userPostList);

    /**
     *
     *
     *  通过用户id 查询职位id
     * @return 结果
     */
    SysUserPost selectUserById(@Param("userId") Long userId);
}
