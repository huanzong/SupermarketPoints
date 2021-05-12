package com.ruoyi.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.SysDept;
import com.ruoyi.system.domain.SysRole;
import com.ruoyi.system.mapper.SysDeptMapper;
import com.ruoyi.system.service.ISysDeptService;

/**
 * 会员卡管理 服务实现
 * 
 *
 */
@Service
public class SysDeptServiceImpl implements ISysDeptService
{
    @Autowired
    private SysDeptMapper deptMapper;

    /**
     * 查询会员卡管理数据
     * 
     * @return 会员卡信息集合
     */
    @Override
    @DataScope(tableAlias = "d")
    public List<SysDept> selectDeptList(SysDept dept)
    {
        return deptMapper.selectDeptList(dept);
    }

    /**
     * 查询会员卡管理树
     * 
     * @return 所有会员卡信息
     */
    @Override
    public List<Map<String, Object>> selectDeptTree()
    {
        List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
        List<SysDept> deptList = selectDeptList(new SysDept());
        trees = getTrees(deptList, false, null);
        return trees;
    }

    /**
     * 根据角色ID查询会员卡（数据权限）
     *
     * @param role 角色对象
     * @return 会员卡列表（数据权限）
     */
    @Override
    public List<Map<String, Object>> roleDeptTreeData(SysRole role)
    {
        Long roleId = role.getRoleId();
        List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
        List<SysDept> deptList = selectDeptList(new SysDept());
        if (StringUtils.isNotNull(roleId))
        {
            List<String> roleDeptList = deptMapper.selectRoleDeptTree(roleId);
            trees = getTrees(deptList, true, roleDeptList);
        }
        else
        {
            trees = getTrees(deptList, false, null);
        }
        return trees;
    }

    /**
     * 对象转会员卡树
     *
     * @param menuList 会员卡列表
     * @param isCheck 是否需要选中
     * @param roleDeptList 角色已存在菜单列表
     * @return
     */
    public List<Map<String, Object>> getTrees(List<SysDept> deptList, boolean isCheck, List<String> roleDeptList)
    {

        List<Map<String, Object>> trees = new ArrayList<Map<String, Object>>();
        for (SysDept dept : deptList)
        {
            if (UserConstants.DEPT_NORMAL.equals(dept.getStatus()))
            {
                Map<String, Object> deptMap = new HashMap<String, Object>();
                deptMap.put("id", dept.getDeptId());
                deptMap.put("pId", dept.getParentId());
                deptMap.put("name", dept.getDeptName());
                deptMap.put("title", dept.getDeptName());
                if (isCheck)
                {
                    deptMap.put("checked", roleDeptList.contains(dept.getDeptId() + dept.getDeptName()));
                }
                else
                {
                    deptMap.put("checked", false);
                }
                trees.add(deptMap);
            }
        }
        return trees;
    }

    /**
     * 查询会员卡人数
     * 
     * @param parentId 会员卡ID
     * @return 结果
     */
    @Override
    public int selectDeptCount(Long parentId)
    {
        SysDept dept = new SysDept();
        dept.setParentId(parentId);
        return deptMapper.selectDeptCount(dept);
    }

    /**
     * 查询会员卡是否存在用户
     * 
     * @param deptId 会员卡ID
     * @return 结果 true 存在 false 不存在
     */
    @Override
    public boolean checkDeptExistUser(Long deptId)
    {
        int result = deptMapper.checkDeptExistUser(deptId);
        return result > 0 ? true : false;
    }

    /**
     * 删除会员卡管理信息
     * 
     * @param deptId 会员卡ID
     * @return 结果
     */
    @Override
    public int deleteDeptById(Long deptId)
    {
        return deptMapper.deleteDeptById(deptId);
    }

    /**
     * 新增保存会员卡信息
     * 
     * @param dept 会员卡信息
     * @return 结果
     */
    @Override
    public int insertDept(SysDept dept)
    {
        SysDept info = deptMapper.selectDeptById(dept.getParentId());
        dept.setAncestors(info.getAncestors() + "," + dept.getParentId());
        return deptMapper.insertDept(dept);
    }

    /**
     * 修改保存会员卡信息
     * 
     * @param dept 会员卡信息
     * @return 结果
     */
    @Override
    public int updateDept(SysDept dept)
    {
        SysDept info = deptMapper.selectDeptById(dept.getParentId());
        if (StringUtils.isNotNull(info))
        {
            String ancestors = info.getAncestors() + "," + dept.getParentId();
            dept.setAncestors(ancestors);
            updateDeptChildren(dept.getDeptId(), ancestors);
        }
        return deptMapper.updateDept(dept);
    }

    /**
     * 修改子元素关系
     * 
     * @param deptId 会员卡ID
     * @param ancestors 元素列表
     */
    public void updateDeptChildren(Long deptId, String ancestors)
    {
        SysDept dept = new SysDept();
        dept.setParentId(deptId);
        List<SysDept> childrens = deptMapper.selectDeptList(dept);
        for (SysDept children : childrens)
        {
            children.setAncestors(ancestors + "," + dept.getParentId());
        }
        if (childrens.size() > 0)
        {
            deptMapper.updateDeptChildren(childrens);
        }
    }

    /**
     * 根据会员卡ID查询信息
     * 
     * @param deptId 会员卡ID
     * @return 会员卡信息
     */
    @Override
    public SysDept selectDeptById(Long deptId)
    {
        return deptMapper.selectDeptById(deptId);
    }

    /**
     * 校验会员卡名称是否唯一
     * 
     * @param dept 会员卡信息
     * @return 结果
     */
    @Override
    public String checkDeptNameUnique(SysDept dept)
    {
        Long deptId = StringUtils.isNull(dept.getDeptId()) ? -1L : dept.getDeptId();
        SysDept info = deptMapper.checkDeptNameUnique(dept.getDeptName(), dept.getParentId());
        if (StringUtils.isNotNull(info) && info.getDeptId().longValue() != deptId.longValue())
        {
            return UserConstants.DEPT_NAME_NOT_UNIQUE;
        }
        return UserConstants.DEPT_NAME_UNIQUE;
    }

    @Override
    public List<SysDept> selectDeptName() {

        return deptMapper.selectDeptList(new SysDept());
    }

    /**
     * 根据会员卡类id 查询会员卡名称
     *
     * @return 参数键值
     */
    @Override
    public SysDept selectDeptByIdGetDeptName(Integer deptId) {
        return deptMapper.selectDeptByName(deptId);
    }
}
