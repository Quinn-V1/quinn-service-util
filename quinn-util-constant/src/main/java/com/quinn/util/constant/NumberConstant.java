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
     * 整数 -1
     */
    int INT_ONE_NEGATIVE = -1;

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
     * 整数 5
     */
    int INT_FIVE = 5;

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
    long LONG_ZERO = 0L;

    /**
     * 长整型1
     */
    long LONG_ONE = 1L;

    /**
     * 长整型2
     */
    long LONG_TOW = 2L;

    /**
     * 长整型-1
     */
    long LONG_ONE_NEGATIVE = -1L;

    /**
     * 长整型-2
     */
    long LONG_TWO_NEGATIVE = -2L;

    /**
     * 长整型-3
     */
    long LONG_THREE_NEGATIVE = -3L;

    /**
     * 长整型-4
     */
    long LONG_FOUR_NEGATIVE = -4L;

    /**
     * 长整型-4
     */
    long LONG_FIVE_NEGATIVE = -5L;

    /**
     * 数据字典-ID-顶层数据
     */
    long TOP_OF_DATA_ID = LONG_ZERO;

    /**
     * 数据字典-ID-无
     */
    long NONE_OF_DATA_ID = LONG_ONE_NEGATIVE;

    /**
     * 数据字典-ID-无
     */
    int NONE_OF_DATA_INT = INT_ONE_NEGATIVE;

    /**
     * 数据字典-ID-全部
     */
    long ALL_OF_DATA_ID = LONG_TWO_NEGATIVE;

    /**
     * 数据字典-ID-默认
     */
    long DEFAULT_OF_DATA_ID = LONG_THREE_NEGATIVE;

    /**
     * 字典数据-ID-其他
     */
    long OTHER_OF_DATA_ID = LONG_FOUR_NEGATIVE;

    /**
     * 字典数据-ID-数据类型
     */
    long DATA_TYPE_ID = LONG_FIVE_NEGATIVE;

    /**
     * 1秒钟对应的微秒数
     */
    long TIME_MILL_ONE_SECOND = 1000;

    /**
     * 1分钟对应的微秒数
     */
    long TIME_MILL_ONE_MINUTE = 60000;

    /**
     * 1分钟对应的微秒数
     */
    long TIME_MILL_ONE_HOUR = 3600000;

    /**
     * 1分钟对应的微数
     */
    long TIME_SEC_ONE_MINUTE = 60;

    /**
     * 1小时对应的秒数
     */
    long TIME_SEC_ONE_HOUR = 3600;

    /**
     * 半天对应的秒数
     */
    long TIME_SEC_HALF_DAY = 3600 * 12;

    /**
     * 一天对应的秒数
     */
    long TIME_SEC_ONE_DAY = 3600 * 24;

}
