package com.quinn.util.base.enums;

import com.quinn.util.base.handler.EnumMessageResolver;
import com.quinn.util.constant.MessageEnumFlag;

import java.util.Locale;

/**
 * 数据操作类型
 *
 * @author Qunhua.Liao
 * @since 2020-03-29
 */
public enum DataOperateTypeEnum implements MessageEnumFlag {

    // 增
    INSERT("新增"),

    // 删
    DELETE("删除"),

    // 改
    UPDATE("修改"),

    // 查
    QUERY("查找"),

    // 操作
    OPERATE("操作"),

    ;

    public final String defaultDesc;

    DataOperateTypeEnum(String defaultDesc) {
        this.defaultDesc = defaultDesc;
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
        EnumMessageResolver.addContent(Locale.SIMPLIFIED_CHINESE, DataOperateTypeEnum.values());
    }
}
