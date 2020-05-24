package com.quinn.util.constant;

import java.util.Locale;

/**
 * 消息枚举
 *
 * @author Qunhua.Liao
 * @since 2020-05-24
 */
public interface MessageEnum {

    /**
     * 默认描述
     *
     * @return 描述
     */
    String name();

    /**
     * 默认描述
     *
     * @return 描述
     */
    String defaultDesc();

    /**
     * 参数名
     *
     * @return 参数名
     */
    String[] paramNames();

}
