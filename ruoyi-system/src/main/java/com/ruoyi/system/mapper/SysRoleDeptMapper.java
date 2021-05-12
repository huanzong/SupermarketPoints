package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.SysRoleDept;

/**
 * 角色与会员卡关联表 数据层
 * 
 *
 */
public interface SysRoleDeptMapper
{
    /**
     * 通过角色ID删除角色和会员卡关联
     * 
     * @param roleId 角色ID
     * @return 结果
     */
    public int deleteRoleDeptByRoleId(Long roleId);

    /**
     * 批量删除角色会员卡关联信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteRoleDept(Long[] ids);

    /**
     * 查询会员卡使用数量
     * 
     * @param deptId 会员卡ID
     * @return 结果
     */
    public int selectCountRoleDeptByDeptId(Long deptId);

    /**
     * 批量新增角色会员卡信息
     * 
     * @param roleDeptList 角色会员卡列表
     * @return 结果
     */
    public int batchRoleDept(List<SysRoleDept> roleDeptList);
}
