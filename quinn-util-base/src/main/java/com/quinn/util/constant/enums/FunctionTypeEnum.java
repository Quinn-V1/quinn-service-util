package com.quinn.util.constant.enums;

import com.quinn.util.base.NumberUtil;
import com.quinn.util.base.convertor.BaseConverter;

/**
 * 角色类型枚举类
 *
 * @author Qunhua.Liao
 * @since 2020-05-23
 */
public enum FunctionTypeEnum {

    // 模块
    MODULE(10),

    // 菜单
    MENU(20),

    // 按钮
    BUTTON(30),

    // 接口
    INTERFACE(40),

    // 用于匹配接口（通常用于接口权限控制）
    MATCH(80);

    /**
     * 数字编码
     */
    public final int code;

    FunctionTypeEnum(int code) {
        this.code = code;
    }

    /**
     * 是否为接口
     *
     * @param functionType 功能类型
     * @return 就接口：true
     */
    public static boolean isInterface(Integer functionType) {
        if (functionType == null) {
            return false;
        }
        return functionType.intValue() >= INTERFACE.code;
    }

    /**
     * 是否为接口
     *
     * @param functionType 功能类型
     * @return 就接口：true
     */
    public static boolean isMenu(Integer functionType) {
        if (functionType == null) {
            return false;
        }
        return functionType.intValue() <= MENU.code;
    }

    /**
     * 根据字符串获取编码
     *
     * @param type 类型（编码、名称）
     * @return 编码
     */
    public static Integer nameToCode(String type) {
        if (NumberUtil.isInteger(type)) {
            return BaseConverter.staticConvert(type, Integer.class);
        }

        for (FunctionTypeEnum typeEnum : FunctionTypeEnum.values()) {
            if (typeEnum.name().equals(type)) {
                return typeEnum.code;
            }
        }

        return null;
    }

    /**
     * 根据编码获取名称
     *
     * @param type 类型（编码、名称）
     * @return 编码
     */
    public static String codeToName(Integer type) {
        if (type == null) {
            return null;
        }

        for (FunctionTypeEnum typeEnum : FunctionTypeEnum.values()) {
            if (typeEnum.code == type.intValue()) {
                return typeEnum.name();
            }
        }

        return null;
    }
}
