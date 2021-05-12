package com.ruoyi.common.constant;

/**
 * @author sun li
 * @Date 2021/10/25 15:24
 * @Description
 */
public class IntegralConstants {

    /** 积分日志里撤销积分*/
    public static final Integer INTEGRALLOG_CHE_XIAO = 0 ;
    /** 积分日志里回撤销积分*/
    public static final Integer INTEGRALLOG_HUI_CHE = 1 ;


    /** 审批通过添加日志默认状态 0 */
    public static final Integer INTEGRALLOG_DEFULLT = 0;

    /** 审批积分管理  0 审批中， 1 审批通过 2.审批不通过 3.撤销审批*/
    public static final Integer SQ_SUCCESS = 1;
    public static final Integer SQ_FAIL = 2;
    public static final Integer SQ_CX = 3;

   /** 管理员自由添加积分状态 */
    public static final Integer ZY_INTEGRAL = 2;

    /** 积分菜单正常状态*/
    public static final Integer INTEGRAL_MENU = 0 ;

    /** 会员卡名称是否唯一的返回结果码 */
    public final static String INTEGRAL_MENU_UNIQUE = "0";
    public final static String INTEGRAL_MENU_NOT_UNIQUE = "1";

    /** 积分餐单通过parentId添加积分类型 */
    public static final Integer INTEGRAL_MENU_PARENTID = 0;
    public static final Integer INTEGRAL_A_PARENTID  = 1;
    public static final Integer INTEGRAL_B_PARENTID = 2;
    public static final Integer INTEGRAL_C_PARENTID = 3;

    /** 积分禁用*/
    public static final Integer INTEGRAL_JIN_YONG = 1;
    public static final Integer SQ_PARENT = 2;

    /** 是否参加积分排名  1.参与  2 是不参与 */
    public static final String USER_INTEGRAL_PAI_MING = "1";
    public static final String USER_NOT_CAN_YU  = "2";

    /** 用户是否离职状态 */
    public static final String YES_LI_ZHI = "1";
    public static final String NO_LI_ZHI = "0";

    /** 设置审批人状态  3，普通员工 1，管理员 ，2 高级超级管理 */
    public static final String COMMON_APP = "3";
    public static final String ADMIN_APP = "1";
    public static final String SUPER_APP = "2";

}
