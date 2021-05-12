package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysPost;

/**
 * 会员等级信息 数据层
 * 
 *
 */
public interface SysPostMapper
{
    /**
     * 查询会员等级数据集合
     * 
     * @param post 会员等级信息
     * @return 会员等级数据集合
     */
    public List<SysPost> selectPostList(SysPost post);

    /**
     * 查询所有会员等级
     * 
     * @return 会员等级列表
     */
    public List<SysPost> selectPostAll();

    /**
     * 根据用户ID查询会员等级
     * 
     * @param userId 用户ID
     * @return 会员等级列表
     */
    public List<SysPost> selectPostsByUserId(Long userId);

    /**
     * 通过会员等级ID查询会员等级信息
     * 
     * @param postId 会员等级ID
     * @return 角色对象信息
     */
    public SysPost selectPostById(Long postId);

    /**
     * 批量删除会员等级信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deletePostByIds(Long[] ids);

    /**
     * 修改会员等级信息
     * 
     * @param post 会员等级信息
     * @return 结果
     */
    public int updatePost(SysPost post);

    /**
     * 新增会员等级信息
     * 
     * @param post 会员等级信息
     * @return 结果
     */
    public int insertPost(SysPost post);

    /**
     * 校验会员等级名称
     * 
     * @param postName 会员等级名称
     * @return 结果
     */
    public SysPost checkPostNameUnique(String postName);

    /**
     * 校验会员等级编码
     * 
     * @param postCode 会员等级编码
     * @return 结果
     */
    public SysPost checkPostCodeUnique(String postCode);
}
