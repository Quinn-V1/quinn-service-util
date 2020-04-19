package com.quinn.util.base.api;

import java.util.List;
import java.util.Map;

/**
 * 占位参数处理接口
 *
 * @author Qunhua.Liao
 * @since 2020-03-28
 */
public interface PlaceholderHandler {

    /**
     * 解析字符串
     *
     * @param format     需要替换的源字符串
     * @param properties 待替换的属性值
     * @return 替换后的值
     */
    String parseStringWithMap(String format, Map<String, Object> properties);

    /**
     * 指定key-value替换
     *
     * @param format   要替换的原字符串
     * @param args     参数
     * @return 替换后的值
     */
    String parseStringWithArray(String format, Object... args);

    /**
     * 解析SQL
     *
     * @param formatSql     需要替换的源SQL
     * @param params        需要带入SQL的参数-从cond中解析出来
     * @param cond          源参数
     * @return              解析好的SQL文，占位参数使用 ? 替换，然后实际参数在param中
     */
    String parseSqlParamMapToList(String formatSql, List<Object> params, Map<String, Object> cond);

    /**
     * 指定key-value替换
     *
     * @param format   要替换的原字符串
     * @param key      键
     * @param value    替换值
     * @return 替换后如值
     */
    String parseStringByOne(String format, String key, String value);

    /**
     * 判断是否已经完全解析
     *
     * @param value 解析对象
     * @return 是否完全解析
     */
    boolean isComplete(String value);

}
