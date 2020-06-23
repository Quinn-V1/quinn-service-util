package com.quinn.util.base.enums;

import com.quinn.util.base.handler.EnumMessageResolver;
import com.quinn.util.constant.MessageEnumFlag;

import java.util.Locale;

/**
 * 密级枚举类
 *
 * @author Qunhua.Liao
 * @since 2020-02-09
 */
public enum SecurityLevelEnum implements MessageEnumFlag {

    // 邮件
    PUBLIC("公开", 1),

    // 微信
    AFTER_LOGIN("登录可见", 2),

    // 站内信
    SPECIAL_HIDE("指定不可见", 4),

    // 短信
    SPECIAL_VISIBLE("指定可见", 8),

    // APP
    SELF_VISIBLE("自己可见", 16),

    // APP
    PASSWORD_VISIBLE("输入密码可见", 32),

    ;

    /**
     * 默认
     */
    public final String defaultDesc;

    /**
     * 编码:用于位存储
     */
    public final int code;

    SecurityLevelEnum(String defaultDesc, int code) {
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
        EnumMessageResolver.addContent(Locale.SIMPLIFIED_CHINESE, SecurityLevelEnum.values());
    }

}
