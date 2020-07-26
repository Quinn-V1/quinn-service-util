package com.quinn.util.constant.enums;

/**
 * 过滤器顺序组
 *
 * @author Qunhua.Liao
 * @since 2020-04-13
 */
public enum FilterOrderGroupEnum {

    // 顶层：优先级最高
    TOP_FILTER(100),

    // 追踪：优先级其次
    TRACE_FILTER(200),

    // 安全：优先级再次
    SECURITY_FILTER(300),

    // 会话：优先级最低
    SESSION_FILTER(1000),

    ;

    public final int order;

    FilterOrderGroupEnum(int order) {
            this.order = order;
    }

}
