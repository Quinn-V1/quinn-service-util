package com.quinn.util.constant.enums;

/**
 * 排序类型
 *
 * @author Qunhua.Liao
 * @since 2020-04-09
 */
public enum  OrderByTypeEnum {

    ASC(1),

    DESC(-1),

    ;

    /**
     * 默认排序
     */
    public static final String DEFAULT_ORDER = "DESC";

    public final int status;

    OrderByTypeEnum(int status) {
        this.status = status;
    }


}
