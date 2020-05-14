package com.quinn.util.base.enums;

import com.quinn.util.base.model.BaseResult;
import com.quinn.util.base.model.BatchResult;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 数据类型枚举类
 *
 * @author Qunhua.Liao
 * @since 2020-04-27
 */
public enum DataTypeEnum {

    // 字符串
    STRING(String.class),

    // 整型
    INTEGER(Integer.class),

    // 短整型
    SHORT(Short.class),

    // 长整型
    LONG(Long.class),

    // 数字
    BIG_DECIMAL(BigDecimal.class),

    // 双精度
    DOUBLE(Double.class),

    // 布尔
    BOOLEAN(Boolean.class),

    // 日期
    DATE(LocalDate.class),

    // 日期时间
    DATETIME(LocalDateTime.class),

    // 基础结果类
    BASE_RESULT(BaseResult.class),

    // 批处理结果类
    BATCH_RESULT(BatchResult.class),

    ;

    public final Class clazz;

    DataTypeEnum(Class clazz) {
        this.clazz = clazz;
    }

    /**
     * 获取数据类型对应的Java类
     *
     * @param dataType  数据类型
     * @return Java类
     */
    public static Class classOf(String dataType) {
        if (dataType == null) {
            return String.class;
        }

        for (DataTypeEnum dataTypeEnum : DataTypeEnum.values()) {
            if (dataTypeEnum.name().equals(dataType)) {
                return dataTypeEnum.clazz;
            }
        }

        try {
            return Class.forName(dataType);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }
}
