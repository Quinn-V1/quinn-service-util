package com.quinn.util.base.api;

import com.quinn.util.base.model.BaseResult;

/**
 * 大文件读取，分部处理器
 *
 * @author Qunhua.Liao
 * @since 2020-02-12
 */
public interface LargeStringFilePartHandler {

    /**
     * 处理方法
     *
     * @param string    读取到的部分字符串
     * @return  处理是否成功
     */
    BaseResult handle(String string);

}
