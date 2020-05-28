package com.quinn.util.base;

import com.quinn.util.base.exception.DataStyleNotMatchException;
import com.quinn.util.base.enums.CommonMessageEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * 逻辑值操作工具类
 *
 * @author QUnhua.Liao
 * @since 2020-03-29
 */
public final class BooleanUtil {

    private BooleanUtil() {
    }

    private static final Map<Object, Boolean> BOOLEAN_MAP = new HashMap<>();

    static {
        BOOLEAN_MAP.put("N", false);
        BOOLEAN_MAP.put(0, false);
        BOOLEAN_MAP.put("Y", true);
        BOOLEAN_MAP.put(1, true);
    }

    /**
     * 判断一个对象是否可以转为逻辑值
     *
     * @param obj 对象
     * @return 为逻辑值则为true否则为false
     */
    public static boolean isBoolean(Object obj) {
        if (obj instanceof Boolean) {
            return true;
        }

        return BOOLEAN_MAP.containsKey(obj);
    }

    /**
     * 将一个对象转化为逻辑值
     *
     * @param obj 对象
     * @return 逻辑值
     */
    public static Boolean parseBoolean(Object obj) {
        if (obj instanceof Boolean) {
            return (Boolean) obj;
        }

        Boolean res = BOOLEAN_MAP.get(obj);
        if (res == null) {
            throw new DataStyleNotMatchException()
                    .getMessageProp()
                    .addParamI8n(CommonMessageEnum.DATA_STYLE_NOT_MATCHED.paramNames[0], Boolean.class.getName())
                    .addParam(CommonMessageEnum.DATA_STYLE_NOT_MATCHED.paramNames[1], obj)
                    .exception();
        }

        return res;
    }

}
