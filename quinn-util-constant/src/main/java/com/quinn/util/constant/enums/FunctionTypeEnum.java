package com.quinn.util.constant.enums;

/**
 * 角色类型枚举类
 *
 * @author Qunhua.Liao
 * @since 2020-05-23
 */
public enum FunctionTypeEnum {

    // 模块
    MODULE,

    // 菜单
    MENU,

    // 按钮
    BUTTON,

    // 接口
    INTERFACE,

    // 用于匹配接口（通常用于接口权限控制）
    MATCH;

    /**
     * 是否为接口
     *
     * @param functionType 功能类型
     * @return  就接口：true
     */
    public static boolean isInterface(String functionType) {
        return INTERFACE.name().equals(functionType) || MATCH.name().equals(functionType);
    }
}
