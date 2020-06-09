package com.quinn.util.constant;

/**
 * 消息枚举
 *
 * @author Qunhua.Liao
 * @since 2020-05-24
 */
public interface MessageEnumFlag {

    /**
     * 数据类型的数据类型
     */
    String DADA_TYPE_OF_DATA_TYPE = "DataTypeEnum";

    /**
     * 默认描述
     *
     * @return 描述
     */
    String name();

    /**
     * 默认描述
     *
     * @return 描述
     */
    default String key() {
        return getClass().getSimpleName() + StringConstant.CHAR_COLON + name()
                + StringConstant.CHAR_POUND_SIGN + StringConstant.NONE_OF_DATA;
    }

    /**
     * 默认描述
     *
     * @return 描述
     */
    String defaultDesc();

    /**
     * 参数名
     *
     * @return 参数名
     */
    String[] paramNames();

}
