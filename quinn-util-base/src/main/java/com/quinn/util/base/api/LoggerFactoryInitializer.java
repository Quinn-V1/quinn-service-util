package com.quinn.util.base.api;

import java.util.Properties;

/**
 * 日志工厂初始化方法
 *
 * @author Qunhua.Liao
 * @since 2020-03-31
 */
public interface LoggerFactoryInitializer {

    /**
     * 获取初始化容器名称
     *
     * @return  名称
     */
    String getName();

    /**
     * 初始化容器
     *
     * @param properties    参数
     */
    void initLoggerFactory(Properties properties);

}
