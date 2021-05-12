package com.ruoyi.system.service;

import com.ruoyi.system.domain.SysDept;
import com.ruoyi.system.domain.SysRole;

import java.util.List;
import java.util.Map;

/**
 * 会员卡管理 服务层
 * 
 *
 */
public interface ISysDeptService
{
    /**
     * 查询会员卡管理数据
     * 
     * @param dept 会员卡信息
     * @return 会员卡信息集合
     */
    public List<SysDept> selectDeptList(SysDept dept);

    /**
     * 查询会员卡管理树
     * 
     * @return 所有会员卡信息
     */
    public List<Map<String, Object>> selectDeptTree();

    /**
     * 根据角色ID查询菜单
     *
     * @param role 角色对象
     * @return 菜单列表
     */
    public List<Map<String, Object>> roleDeptTreeData(SysRole role);

    /**
     * 查询会员卡人数
     * 
     * @param parentId 父会员卡ID
     * @return 结果
     */
    public int selectDeptCount(Long parentId);

    /**
     * 查询会员卡是否存在用户
     * 
     * @param deptId 会员卡ID
     * @return 结果 true 存在 false 不存在
     */
    public boolean checkDeptExistUser(Long deptId);

    /**
     * 删除会员卡管理信息
     * 
     * @param deptId 会员卡ID
     * @return 结果
     */
    public int deleteDeptById(Long deptId);

    /**
     * 新增保存会员卡信息
     * 
     * @param dept 会员卡信息
     * @return 结果
     */
    public int insertDept(SysDept dept);

    /**
     * 修改保存会员卡信息
     * 
     * @param dept 会员卡信息
     * @return 结果
     */
    public int updateDept(SysDept dept);

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
     * @param dept 会员卡信息
     * @return 结果
     */
    public String checkDeptNameUnique(SysDept dept);

    /**
     * 根据会员卡类型查询字典数据信息
     *
     * @return 参数键值
     */
    List<SysDept> selectDeptName();

    /**
     * 根据会员卡类id 查询会员卡名称
     *
     * @return 参数键值
     */
    SysDept selectDeptByIdGetDeptName(Integer deptId);
}
