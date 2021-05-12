package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.SysDept;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 会员卡管理 数据层
 * 
 *
 */
public interface SysDeptMapper {
    /**
     * 查询会员卡人数
     *
     * @param dept 会员卡信息
     * @return 结果
     */
    public int selectDeptCount(SysDept dept);

    /**
     * 查询会员卡是否存在用户
     *
     * @param deptId 会员卡ID
     * @return 结果
     */
    public int checkDeptExistUser(Long deptId);

    /**
     * 查询会员卡管理数据
     *
     * @param dept 会员卡信息
     * @return 会员卡信息集合
     */
    public List<SysDept> selectDeptList(SysDept dept);

    /**
     * 删除会员卡管理信息
     *
     * @param deptId 会员卡ID
     * @return 结果
     */
    public int deleteDeptById(Long deptId);

    /**
     * 新增会员卡信息
     *
     * @param dept 会员卡信息
     * @return 结果
     */
    public int insertDept(SysDept dept);

    /**
     * 修改会员卡信息
     *
     * @param dept 会员卡信息
     * @return 结果
     */
    public int updateDept(SysDept dept);

    /**
     * 修改子元素关系
     *
     * @param depts 子元素
     * @return 结果
     */
    public int updateDeptChildren(@Param("depts") List<SysDept> depts);

    /**
     * 根据会员卡ID查询信息
     *
     * @param deptId 会员卡ID
     * @return 会员卡信息
     */
    public SysDept selectDeptById(Long deptId);

    /**
     * 校验会员卡名称是否唯一
     *
     * @param deptName 会员卡名称
     * @param parentId 父会员卡ID
     * @return 结果
     */
    public SysDept checkDeptNameUnique(@Param("deptName") String deptName, @Param("parentId") Long parentId);

    /**
     * 根据角色ID查询会员卡
     *
     * @param roleId 角色ID
     * @return 会员卡列表
     */
    public List<String> selectRoleDeptTree(Long roleId);

    /**
     * 根据角色ID查询会员卡
     *
     * @return 结果
     */
    SysDept selectDeptByName(@Param("deptId") Integer deptId);
}