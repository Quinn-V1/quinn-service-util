package com.quinn.util.constant.enums;

/**
 * 消息参数枚举
 *
 * @author Qunhua.Liao
 * @since 2020-02-08
 */
public enum ParamTypeEnum {

    // JSON
    JSON(10),

    // SQL
    SQL(20),

    // 接口
    HTTP(30);

    /**
     * 状态编码，具有顺序含义
     */
    public final int code;

    ParamTypeEnum(int code) {
        this.code = code;
    }

    /**
     * 编码转为名称
     *
     * @param code  编码
     * @return      名称
     */
    public static String codeToName(int code) {
        for (ParamTypeEnum e : values()) {
            if (code == e.code) {
                return e.name();
            }
        }
        return null;
    }

    /**
     * 名称转换为编码
     *
     * @param name  名称
     * @return      编码
     */
    public static int nameToCode(String name) {
        for (ParamTypeEnum e : values()) {
            if (e.name().equals(name)) {
                return e.code;
            }
        }
        return -1;
    }

    /**
     * 从参数中获取参数内容的键
     */
    public static final String PARAM_KEY_DATA = "paramContent";

}
