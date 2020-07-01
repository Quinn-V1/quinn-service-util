package com.quinn.util.constant.enums;

/**
 * 表单元素输入控制
 *
 * @author Qunhua.Liao
 * @since 2020-06-12
 */
public enum FormElementRequireEnum {

    // 不渲染
    NONE(0),

    // 隐藏
    HIDDEN(10),

    // 禁用
    DISABLED(20),

    // 只读
    READABLE(30),

    // 可写
    WRITABLE(40),

    // 必填
    REQUIRED(50),

    ;

    /**
     * 数字等级
     */
    public final int code;

    FormElementRequireEnum(int code) {
        this.code = code;
    }

}
