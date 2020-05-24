package com.quinn.util.base.api;

import com.quinn.util.base.exception.BaseBusinessException;
import com.quinn.util.base.model.BaseResult;
import com.quinn.util.base.model.MessageProp;

import java.util.Locale;
import java.util.Map;

/**
 * 消息解析器
 *
 * @author Qunhua.Liao
 * @since 2020-03-28
 */
public interface MessageResolver {

    /**
     * 消息解析
     *
     * @param locale         语言
     * @param messageCode    消息编码
     * @param params         普通参数
     * @param i18nParams     国际化参数
     * @param defaultMessage 默认消息
     * @return 解析后的消息
     */
    BaseResult<String> resolveMessage(Locale locale, String messageCode, Map params, Map i18nParams, String defaultMessage);

    /**
     * 解析消息属性持有器
     *
     * @param locale         语言
     * @param messageProp    消息属性持有器
     * @param defaultMessage 消息属性持有器
     * @return 解析后的消息
     */
    BaseResult<String> resolveMessageProp(Locale locale, MessageProp messageProp, String defaultMessage);

    /**
     * 解析结果
     *
     * @param locale 语言
     * @param result 结果
     * @return 解析后的消息
     */
    BaseResult<String> resolveResult(Locale locale, BaseResult result);

    /**
     * 解析结果
     *
     * @param locale    语言
     * @param exception 异常
     * @return 解析后的消息
     */
    BaseResult<String> resolveException(Locale locale, BaseBusinessException exception);

    /**
     * 解析消息
     *
     * @param locale         语言
     * @param messageCode    消息模板
     * @param args           消息参数
     * @param defaultMessage 默认消息
     * @return 解析后的消息
     */
    BaseResult<String> resolveMessageWithArrayParams(
            Locale locale, String messageCode, String defaultMessage, Object... args);

    /**
     * 优先级
     *
     * @return 优先级
     */
    int priority();

}
