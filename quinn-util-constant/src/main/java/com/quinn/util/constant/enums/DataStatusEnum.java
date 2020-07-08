package com.quinn.util.constant.enums;

import com.quinn.util.constant.MessageEnumFlag;

/**
 * 默认选项枚举
 *
 * @author Qunhua.Liao
 * @since 2020-03-31
 */
public enum DataStatusEnum implements MessageEnumFlag {

    // 全部
    UNREADY("未启用", 0),

    // 归档
    SYS_DISABLE("系统禁用", 5),

    // 全部
    BIZ_DISABLE("业务禁用", 9),

    // 无
    NORMAL("正常启用", 10),

    // 归档
    SYS_INIT("系统初始化", 10000),
    ;

    /**
     * 描述
     */
    public final String desc;

    /**
     * 编码
     */
    public final int code;

    DataStatusEnum(String desc, int code) {
        this.desc = desc;
        this.code = code;
    }

    @Override
    public String defaultDesc() {
        return desc;
    }

    @Override
    public String[] paramNames() {
        return null;
    }
}
