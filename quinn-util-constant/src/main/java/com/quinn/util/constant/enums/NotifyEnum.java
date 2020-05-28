package com.quinn.util.constant.enums;

import static com.quinn.util.constant.CommonMessageConstant.*;

/**
 * 系统错误枚举类
 *
 * @author Qunhua.Liao
 * @since 2020-03-28
 */
public enum NotifyEnum {

    // 全部数据通过
    ALL_DATA_PASSED(DESC_ALL_DATA_PASSED),

    // 全部数据通过
    NOTHING_HAPPENED(DESC_NOTHING_HAPPENED),

    ;

    /**
     * 默认描述
     */
    public final String defaultDesc;

    public final String[] paramNames;

    /**
     * 构造器
     *
     * @param defaultDesc 默认描述
     */
    NotifyEnum(String defaultDesc, String... paramNames) {
        this.defaultDesc = defaultDesc;
        this.paramNames = paramNames;
    }
}
