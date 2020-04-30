package com.quinn.util.constant;

/**
 * SQL相关常量
 *
 * @author Qunhua.Liao
 * @since 2020-04-05
 */
public interface SqlConstant {

    /**
     * 序列下一个值
     */
    String SEQ_NEXT_VALUE = "nextValue";

    /**
     * 参数：序列名
     */
    String PARAM_SEQ_NAME = "seqName";

    /**
     * 参数：序列值
     */
    String PARAM_SEQ_VALUE = "seqValue";

    /**
     * 参数：序列值
     */
    String PARAM_SEQ_STEP = "seqStep";

    /**
     * 序列下N个序列
     */
    String SEQ_NEXT_N_VALUE = "nextValueOfNum";

    /**
     * 序列个数
     */
    String SEQ_NUMBER = "seqNum";

    /**
     * 序列步长
     */
    String SEQ_STEP = "step";

    /**
     * 数据类型-日期
     */
    String DATA_TYPE_DATE = "1014";

    /**
     * 数据类型-时间
     */
    String DATA_TYPE_TIME = "1015";

    /**
     * 数据类型时间
     */
    String DATA_TYPE_STRING = "1012";

}
