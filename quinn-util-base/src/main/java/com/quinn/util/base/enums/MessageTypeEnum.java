package com.quinn.util.base.enums;

import com.quinn.util.base.handler.EnumMessageResolver;
import com.quinn.util.constant.MessageEnumFlag;

import java.util.Locale;

/**
 * 消息类型枚举类
 *
 * @author Qunhua.Liao
 * @since 2020-02-09
 */
public enum MessageTypeEnum implements MessageEnumFlag {

    // 邮件
    EMAIL("邮件", 1),

    // 微信
    WE_CHART("微信", 2),

    // 站内信
    WEB("站内信", 4),

    // 短信
    MOBILE("短信", 8),

    // APP
    APP("APP", 16);

    /**
     * 默认
     */
    public final String defaultDesc;

    /**
     * 编码:用于位存储
     */
    public final int code;

    MessageTypeEnum(String defaultDesc, int code) {
        this.defaultDesc = defaultDesc;
        this.code = code;
    }

    @Override
    public String defaultDesc() {
        return defaultDesc;
    }

    @Override
    public String[] paramNames() {
        return null;
    }

    static {
        EnumMessageResolver.addContent(Locale.SIMPLIFIED_CHINESE, MessageTypeEnum.values());
    }

}
