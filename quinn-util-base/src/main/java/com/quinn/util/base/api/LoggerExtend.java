package com.quinn.util.base.api;

/**
 * 扩展日志对象
 *
 * @author Qunhua.Liao
 * @since 2020-03-30
 */
public interface LoggerExtend {

    /**
     * 获取日志名称
     *
     * @return  日志名称
     */
    String getName();

    /**
     * 追踪级别日志
     *
     * @param format  消息模板
     * @param args  消息参数
     * @return 消息构建器
     */
    void trace(String format, Object... args);

    /**
     * 追踪级别日志-带错误
     *
     * @param format  消息模板
     * @param error  消息异常
     * @param args  消息参数
     * @return 消息构建器
     */
    void traceError(String format, Throwable error, Object... args);

    /**
     * 调试级别日志
     *
     * @param format  消息模板
     * @param args  消息参数
     * @return 消息构建器
     */
    void debug(String format, Object... args);

    /**
     * 调试级别日志-带错误
     *
     * @param format  消息模板
     * @param error  消息异常
     * @param args  消息参数
     * @return 消息构建器
     */
    void debugError(String format, Throwable error, Object... args);

    /**
     * 通知级别日志
     *
     * @param format  消息模板
     * @param args  消息参数
     * @return 消息构建器
     */
    void info(String format, Object... args);

    /**
     * 通知级别日志-带错误
     *
     * @param format  消息模板
     * @param error  消息异常
     * @param args  消息参数
     * @return 消息构建器
     */
    void infoError(String format, Throwable error, Object... args);

    /**
     * 警告级别日志
     *
     * @param format  消息模板
     * @param args  消息参数
     * @return 消息构建器
     */
    void warn(String format, Object... args);

    /**
     * 警告级别日志-带错误
     *
     * @param format  消息模板
     * @param error  消息异常
     * @param args  消息参数
     * @return 消息构建器
     */
    void warnError(String format, Throwable error, Object... args);

    /**
     * 错误级别日志
     *
     * @param format  消息模板
     * @param args  消息参数
     * @return 消息构建器
     */
    void error(String format, Object... args);

    /**
     * 错误级别日志-带错误
     *
     * @param format  消息模板
     * @param error  消息异常
     * @param args  消息参数
     * @return 消息构建器
     */
    void errorError(String format, Throwable error, Object... args);

}
