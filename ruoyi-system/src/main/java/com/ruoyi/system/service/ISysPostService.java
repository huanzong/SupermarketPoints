package com.ruoyi.system.service;

import com.ruoyi.system.domain.SysPost;

import java.util.List;

/**
 * 会员等级信息 服务层
 * 
 *
 */
public interface ISysPostService
{
    /**
     * 查询会员等级信息集合
     * 
     * @param post 会员等级信息
     * @return 会员等级信息集合
     */
    List<SysPost> selectPostList(SysPost post);

    /**
     * 查询所有会员等级
     * 
     * @return 会员等级列表
     */
    List<SysPost> selectPostAll();

    /**
     * 根据用户ID查询会员等级
     * 
     * @param userId 用户ID
     * @return 会员等级列表
     */
    List<SysPost> selectPostsByUserId(Long userId);

    /**
     * 通过会员等级ID查询会员等级信息
     * 
     * @param postId 会员等级ID
     * @return 角色对象信息
     */
    SysPost selectPostById(Long postId);

    /**
     * 批量删除会员等级信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     * @throws Exception 异常
     */
    int deletePostByIds(String ids) throws Exception;

    /**
     * 新增保存会员等级信息
     * 
     * @param post 会员等级信息
     * @return 结果
     */
    int insertPost(SysPost post);

    /**
     * 修改保存会员等级信息
     * 
     * @param post 会员等级信息
     * @return 结果
     */
    int updatePost(SysPost post);

    /**
     * 通过会员等级ID查询会员等级使用数量
     * 
     * @param postId 会员等级ID
     * @return 结果
     */
    int countUserPostById(Long postId);

    /**
     * 校验会员等级名称
     * 
     * @param post 会员等级信息
     * @return 结果
     */
    String checkPostNameUnique(SysPost post);

    /**
     * 校验会员等级编码
     * 
     * @param post 会员等级信息
     * @return 结果
     */
    String checkPostCodeUnique(SysPost post);

    /**
     * 根据职位类型查询字典数据信息
     *
     * @return 参数键值
     */
    List<SysPost> selectPost();
}
