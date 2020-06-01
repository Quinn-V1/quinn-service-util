package com.quinn.util.base.enums;

import com.quinn.util.base.handler.EnumMessageResolver;
import com.quinn.util.constant.MessageEnumFlag;

import java.util.Locale;

import static com.quinn.util.constant.CommonMessageConstant.DESC_ALL_DATA_PASSED;
import static com.quinn.util.constant.CommonMessageConstant.DESC_NOTHING_HAPPENED;

/**
 * 系统错误枚举类
 *
 * @author Qunhua.Liao
 * @since 2020-03-28
 */
public enum NotifyEnum implements MessageEnumFlag {

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

    @Override
    public String defaultDesc() {
        return defaultDesc;
    }

    @Override
    public String[] paramNames() {
        return paramNames;
    }

    static {
        EnumMessageResolver.addContent(Locale.SIMPLIFIED_CHINESE, NotifyEnum.values());
    }
}
