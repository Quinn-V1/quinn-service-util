package com.quinn.util.constant;

/**
 * 数字常量
 *
 * @author Qunhua.Liao
 * @since 2020-04-04
 */
public interface NumberConstant {

    /**
     * 整数 0
     */
    int INT_ZERO = 0;

    /**
     * 整数 1
     */
    int INT_ONE = 1;

    /**
     * 整数 2
     */
    int INT_TWO = 2;

    /**
     * 整数 3
     */
    int INT_THREE = 3;

    /**
     * 整数 4
     */
    int INT_FOUR = 4;

    /**
     * 整数 10
     */
    int INT_TEN = 10;

    /**
     * 默认安全删除数量
     */
    int DELETE_DANGER_SIZE = 100;

    /**
     * 默认安全查询数量
     */
    int QUERY_DANGER_SIZE = 1000;

    /**
     * 假设最大TPS
     */
    int MAX_TPS = 1000000;

    /**
     * 长整型0
     */
    Long LONG_ONE = 1L;

    /**
     * 长整型0
     */
    Long LONG_ZERO = 0L;

    /**
     * 长整型-1
     */
    Long LONG_ONE_NEGATIVE = -1L;

    /**
     * 长整型-2
     */
    Long LONG_TWO_NEGATIVE = -2L;

    /**
     * 长整型-3
     */
    Long LONG_THREE_NEGATIVE = -3L;

    /**
     * 长整型-4
     */
    Long LONG_FOUR_NEGATIVE = -4L;

    /**
     * 长整型-4
     */
    Long LONG_FIVE_NEGATIVE = -5L;

    /**
     * 数据字典-ID-顶层数据
     */
    Long TOP_OF_DATA_ID = LONG_ZERO;

    /**
     * 数据字典-ID-无
     */
    Long NONE_OF_DATA_ID = LONG_ONE_NEGATIVE;

    /**
     * 数据字典-ID-全部
     */
    Long ALL_OF_DATA_ID = LONG_TWO_NEGATIVE;

    /**
     * 数据字典-ID-默认
     */
    Long DEFAULT_OF_DATA_ID = LONG_THREE_NEGATIVE;

    /**
     * 字典数据-ID-其他
     */
    Long OTHER_OF_DATA_ID = LONG_FOUR_NEGATIVE;

    /**
     * 字典数据-ID-数据类型
     */
    Long DATA_TYPE_ID = LONG_FIVE_NEGATIVE;

}
