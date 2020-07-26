package com.quinn.util.constant.enums;

import com.quinn.util.base.NumberUtil;
import com.quinn.util.base.handler.EnumMessageResolver;
import com.quinn.util.constant.MessageEnumFlag;
import com.quinn.util.constant.NumberConstant;

import java.util.ArrayList;
import java.util.List;
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

    /**
     * 变成字符串数组
     *
     * @param dealTypes 支持操作类型的合成整型
     * @return 支持操作类型
     */
    public static List<String> asStrings(Integer dealTypes) {
        if (NumberUtil.isEmptyInFrame(dealTypes)) {
            return null;
        }

        List<String> results = new ArrayList<>();
        for (MessageTypeEnum dealTypeEnum : MessageTypeEnum.values()) {
            if ((dealTypeEnum.code & dealTypes) > 0) {
                results.add(dealTypeEnum.name());
            }
        }

        return results;
    }

    /**
     * 合成整型
     *
     * @param dealTypesStr 支持操作类型数组
     * @return 合成整型
     */
    public static Integer asInteger(String[] dealTypesStr) {
        if (dealTypesStr == null) {
            return NumberConstant.INT_ZERO;
        }

        Integer result = 0;
        for (String dealType : dealTypesStr) {
            for (MessageTypeEnum dealTypeEnum : MessageTypeEnum.values()) {
                if (dealTypeEnum.name().equals(dealType)) {
                    result = result | dealTypeEnum.code;
                    break;
                }
            }
        }

        return result;
    }

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
