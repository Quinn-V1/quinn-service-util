package com.quinn.util.base.api;

import com.quinn.util.base.model.BaseResult;

import java.util.Map;

/**
 * 国际化消息解析器
 *
 * @author Qunhua.Liao
 * @since 2020-04-20
 */
public interface I18nMsgResolver {

    /**
     * 优先级
     *
     * @return 优先级
     */
    int priority();

    /**
     * 解析消息
     *
     * @param messageCode 消息编码
     * @param params      直接参数
     * @param i18nParams  国际化参数
     * @return 国际化消息
     */
    BaseResult<String> resolveMap(String messageCode, Map<String, Object> params, Map<String, Object> i18nParams);

    /**
     * 解析消息（数组形式的参数）
     *
     * @param messageCode 消息编码
     * @param params      参数
     * @return
     */
    BaseResult<String> resolveArray(String messageCode, Object... params);

}
