package com.quinn.util.base.model;

import com.quinn.util.base.exception.ParameterShouldNotEmpty;
import com.quinn.util.base.enums.ExceptionEnum;

/**
 * 临时容器
 *
 * @author Qunhua.Liao
 * @since 2020-04-16
 */
public class TempContainer {

    private Object t;

    /**
     * 带参构造器
     *
     * @param t 文件呢信息
     */
    public TempContainer(Object t) {
        if (t == null) {
            throw new ParameterShouldNotEmpty()
                    .addParam(ExceptionEnum.PARAM_SHOULD_NOT_NULL.paramNames[0], "fileInfoVO")
                    .exception()
                    ;
        }
        this.t = t;
    }

    /**
     * 真实数据
     *
     * @return  真实数据
     */
    public <T> T realData() {
        return (T) t;
    }

}
