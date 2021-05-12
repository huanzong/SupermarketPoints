package com.ruoyi.system.service.impl;

import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.constant.IntegralConstants;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.support.Convert;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.integral.domain.Integral;
import com.ruoyi.integral.mapper.IntegralMapper;
import com.ruoyi.system.domain.*;
import com.ruoyi.system.mapper.*;
import com.ruoyi.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户 业务层处理
 *
 *
 */
@Service
public class SysUserServiceImpl implements ISysUserService {
    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private SysRoleMapper roleMapper;

    @Autowired
    private SysPostMapper postMapper;

    @Autowired
    private SysUserPostMapper userPostMapper;

    @Autowired
    private SysUserRoleMapper userRoleMapper;

    @Autowired
    private IntegralMapper integralMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;

    /**
     * 根据条件分页查询用户对象
     *
     * @param user 用户信息
     * @return 用户信息集合信息
     */
    @Override
    @DataScope(tableAlias = "u")
    public List<SysUser> selectUserList(SysUser user) {
        List<SysUser> sysUserList = userMapper.selectUserList(user);
        for (SysUser sysUser : sysUserList) {
            SysUserRole userRole = userRoleMapper.selectByUserGetRoleId(sysUser.getUserId());
            SysRole role = sysRoleMapper.selectRoleById(userRole.getRoleId());
            sysUser.setRoleName(role.getRoleName());
        }
        return sysUserList;
    }

    /**
     * 通过用户名查询用户
     *
     * @param userName 用户名
     * @return 用户对象信息
     */
    @Override
    public SysUser selectUserByLoginName(String userName) {
        return userMapper.selectUserByLoginName(userName);
    }

    /**
     * 通过手机号码查询用户
     *
     * @param phoneNumber 用户名
     * @return 用户对象信息
     */
    @Override
    public SysUser selectUserByPhoneNumber(String phoneNumber) {
        return userMapper.selectUserByPhoneNumber(phoneNumber);
    }

    /**
     * 通过邮箱查询用户
     *
     * @param email 邮箱
     * @return 用户对象信息
     */
    @Override
    public SysUser selectUserByEmail(String email) {
        return userMapper.selectUserByEmail(email);
    }

    /**
     * 通过用户ID查询用户
     *
     * @param userId 用户ID
     * @return 用户对象信息
     */
    @Override
    public SysUser selectUserById(Long userId) {
        return userMapper.selectUserById(userId);
    }

    /**
     * 通过用户ID删除用户
     *
     * @param userId 用户ID
     * @return 结果
     */
    @Override
    public int deleteUserById(Long userId) {
        // 删除用户与角色关联
        userRoleMapper.deleteUserRoleByUserId(userId);
        // 删除用户与会员等级表
        userPostMapper.deleteUserPostByUserId(userId);
        return userMapper.deleteUserById(userId);
    }

    /**
     * 批量删除用户信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteUserByIds(String ids) throws Exception {
        Long[] userIds = Convert.toLongArray(ids);
        for (Long userId : userIds) {
            if (SysUser.isAdmin(userId)) {
                throw new Exception("不允许删除超级管理员用户");
            }
        }
        return userMapper.deleteUserByIds(userIds);
    }

    /**
     * 新增保存用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int insertUser(SysUser user) {
        //添加员工不是审批人
        user.setIsApprover(Integer.parseInt(IntegralConstants.COMMON_APP));
        //默认参加积分排名
        user.setIntegralStatus(Integer.parseInt(IntegralConstants.USER_INTEGRAL_PAI_MING));
        // 新增用户信息
        int rows = userMapper.insertUser(user);
        // 新增用户会员等级关联
        insertUserPost(user);
        // 新增用户与角色管理
        insertUserRole(user);
        //添加用户对应的积分数据
        addIntegral(user);
        return rows;
    }

    /**
     * 添加用户对应的积分数据
     *
     * @param user 用户对象
     */
    private void addIntegral(SysUser user) {
        Integral integral = new Integral();
        integral.setCountIntegral(user.getJiChuIntegral());
        integral.setUserPhone(user.getPhonenumber());
        integral.setUserName(user.getUserName());
        integral.setUserId(user.getUserId().intValue());
        int row = integralMapper.insertIntegral(integral);
    }

    /**
     * 修改保存用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int updateUser(SysUser user) {
        Long userId = user.getUserId();
        // 删除用户与角色关联
        userRoleMapper.deleteUserRoleByUserId(userId);
        // 新增用户与角色管理
        insertUserRole(user);
        // 删除用户与会员等级关联
        userPostMapper.deleteUserPostByUserId(userId);
        // 新增用户与会员等级
        insertUserPost(user);
        return userMapper.updateUser(user);
    }

    /**
     * 修改用户个人详细信息
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int updateUserInfo(SysUser user) {
        return userMapper.updateUser(user);
    }

    /**
     * 修改用户密码
     *
     * @param user 用户信息
     * @return 结果
     */
    @Override
    public int resetUserPwd(SysUser user) {
        return updateUserInfo(user);
    }

    /**
     * 新增用户角色信息
     *
     * @param user 用户对象
     */
    public void insertUserRole(SysUser user) {
        // 新增用户与角色管理
        List<SysUserRole> list = new ArrayList<SysUserRole>();
        for (Long roleId : user.getRoleIds()) {
            SysUserRole ur = new SysUserRole();
            ur.setUserId(user.getUserId());
            ur.setRoleId(roleId);
            list.add(ur);
        }
        if (list.size() > 0) {
            userRoleMapper.batchUserRole(list);
        }
    }

    /**
     * 新增用户会员等级信息
     *
     * @param user 用户对象
     */
    public void insertUserPost(SysUser user) {
        // 新增用户与会员等级
        List<SysUserPost> list = new ArrayList<SysUserPost>();
        for (Long postId : user.getPostIds()) {
            SysUserPost up = new SysUserPost();
            up.setUserId(user.getUserId());
            up.setPostId(postId);
            list.add(up);
        }
        if (list.size() > 0) {
            userPostMapper.batchUserPost(list);
        }
    }

    /**
     * 校验用户名称是否唯一
     *
     * @param loginName 用户名
     * @return
     */
    @Override
    public String checkLoginNameUnique(String loginName) {
        int count = userMapper.checkLoginNameUnique(loginName);
        if (count > 0) {
            return UserConstants.USER_NAME_NOT_UNIQUE;
        }
        return UserConstants.USER_NAME_UNIQUE;
    }

    /**
     * 校验用户名称是否唯一
     *
     * @param phonenumber 用户名
     * @return
     */
    @Override
    public String checkPhoneUnique(SysUser user) {
        Long userId = StringUtils.isNull(user.getUserId()) ? -1L : user.getUserId();
        SysUser info = userMapper.checkPhoneUnique(user.getPhonenumber());
        if (StringUtils.isNotNull(info) && info.getUserId().longValue() != userId.longValue()) {
            return UserConstants.USER_PHONE_NOT_UNIQUE;
        }
        return UserConstants.USER_PHONE_UNIQUE;
    }

    /**
     * 校验email是否唯一
     *
     * @param user 用户名
     * @return
     */
    @Override
    public String checkEmailUnique(SysUser user) {
        Long userId = StringUtils.isNull(user.getUserId()) ? -1L : user.getUserId();
        SysUser info = userMapper.checkEmailUnique(user.getEmail());
        if (StringUtils.isNotNull(info) && info.getUserId().longValue() != userId.longValue()) {
            return UserConstants.USER_EMAIL_NOT_UNIQUE;
        }
        return UserConstants.USER_EMAIL_UNIQUE;
    }

    /**
     * 查询用户所属角色组
     *
     * @param userId 用户ID
     * @return 结果
     */
    @Override
    public String selectUserRoleGroup(Long userId) {
        List<SysRole> list = roleMapper.selectRolesByUserId(userId);
        StringBuffer idsStr = new StringBuffer();
        for (SysRole role : list) {
            idsStr.append(role.getRoleName()).append(",");
        }
        if (StringUtils.isNotEmpty(idsStr.toString())) {
            return idsStr.substring(0, idsStr.length() - 1);
        }
        return idsStr.toString();
    }

    /**
     * 查询用户所属会员等级组
     *
     * @param userId 用户ID
     * @return 结果
     */
    @Override
    public String selectUserPostGroup(Long userId) {
        List<SysPost> list = postMapper.selectPostsByUserId(userId);
        StringBuffer idsStr = new StringBuffer();
        for (SysPost post : list) {
            idsStr.append(post.getPostName()).append(",");
        }
        if (StringUtils.isNotEmpty(idsStr.toString())) {
            return idsStr.substring(0, idsStr.length() - 1);
        }
        return idsStr.toString();
    }

    /**
     * 根据用户 userId查询更新状态
     *
     * @param userId 用户id
     * @return 结果
     */
    @Override
    public int departureUser(Long userId, String status) {
        SysUser user = userMapper.selectUserById(userId);
        /** 如果传来1 设为在职 0 */
        if (status.equals(IntegralConstants.NO_LI_ZHI)) {
            user.setStatus(IntegralConstants.YES_LI_ZHI);
        }
        /** 如果传来0 设为在职 1 */
        if (status.equals(IntegralConstants.YES_LI_ZHI)) {
            user.setStatus(IntegralConstants.NO_LI_ZHI);
        }
        return userMapper.updateUser(user);
    }

    /**
     * 根据用户 userId 查询更新是否要参与积分活动
     *
     * @param userId 用户id
     * @return 结果
     */
    @Override
    public int integralUser(Long userId, String integralStatus) {
        SysUser user = userMapper.selectUserById(userId);
        if (integralStatus.equals(IntegralConstants.USER_INTEGRAL_PAI_MING)) {
            //设为不参与
            user.setIntegralStatus(2);
        }
        if (integralStatus.equals(IntegralConstants.USER_NOT_CAN_YU)) {
            //设为参与
            user.setIntegralStatus(1);
        }
        return userMapper.updateUser(user);
    }

    /**
     * 查询所用本会员卡员工
     */
    @Override
    public List<SysUser> selectDeptUser(String deptId) {
        return userMapper.selectDeptUser(deptId);
    }

    /**
     * 设置 普通审批人和高级审批人
     */
    @Override
    public int sysAppPel(Long userId, String isApprover) {
        SysUser sysUser = userMapper.selectUserById(userId);
        /** 传过来是 1 改为普通审批人 */
        if (isApprover.equals(IntegralConstants.ADMIN_APP)) {
            sysUser.setIsApprover(Integer.parseInt(IntegralConstants.ADMIN_APP));
        }
        /** 传过来是 2 改为高级审批人 */
        if (isApprover.equals(IntegralConstants.SUPER_APP)) {
            sysUser.setIsApprover(Integer.parseInt(IntegralConstants.SUPER_APP));
        }
        /** 传过来是 0 改为普通人 */
        if (isApprover.equals(IntegralConstants.COMMON_APP)) {
            sysUser.setIsApprover(Integer.parseInt(IntegralConstants.COMMON_APP));
        }
        return userMapper.updateUser(sysUser);
    }
}
