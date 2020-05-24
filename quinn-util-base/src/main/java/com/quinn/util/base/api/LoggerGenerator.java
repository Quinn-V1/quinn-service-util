package com.quinn.util.base.api;

/**
 * 日志生成器
 *
 * @author Qunhua.Liao
 * @since 2020-03-30
 */
public interface LoggerGenerator {

    /**
     * 生成日志对象
     *
     * @param logName   日志名称
     * @return          日志对象
     */
    LoggerExtend generate(String logName);

    /**
     * 日志生成器的名称
     *
     * @return
     */
    String getName();

}
