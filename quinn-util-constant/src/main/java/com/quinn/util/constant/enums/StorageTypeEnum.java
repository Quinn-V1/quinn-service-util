package com.quinn.util.constant.enums;

/**
 * 文件存储系统枚举类
 *
 * @author Qunhua.Liao
 * @since 2020-04-03
 */
public enum StorageTypeEnum {

    // 全部
    LOCAL("本地"),

    // 无
    FAST_DFS("FAST_FDS"),

    // 无
    MINI_IO("MINI_IO"),

    ;

    /**
     * 描述
     */
    public final String desc;

    StorageTypeEnum(String desc) {
        this.desc = desc;
    }

}
