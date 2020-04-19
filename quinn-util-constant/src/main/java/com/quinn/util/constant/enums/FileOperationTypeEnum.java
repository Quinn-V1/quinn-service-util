package com.quinn.util.constant.enums;

/**
 * 文件操作类型枚举
 *
 * @author Qunhua.Liao
 * @since 2020-04-19
 */
public enum FileOperationTypeEnum {

    // 删除
    DELETE("删除", 100),

    // 拼接
    APPEND("拼接", 90),

    // 创建
    CREATE("创建", 80),

    // 复制
    COPY("复制", 70),

    // 执行
    EXECUTE("执行", 20),

    // 读取
    READ("读取", 10),

    ;

    /**
     * 描述
     */
    public final String desc;

    /**
     * 编码
     */
    public final int code;

    FileOperationTypeEnum(String desc, int code) {
        this.desc = desc;
        this.code = code;
    }

}
