package com.quinn.util.base.factory;

import com.quinn.util.base.api.LoggerExtend;
import com.quinn.util.base.api.LoggerGenerator;
import com.quinn.util.base.api.PlaceholderHandler;
import com.quinn.util.base.constant.ConfigConstant;
import com.quinn.util.base.handler.DefaultLoggerFactoryInitializer;
import com.quinn.util.base.handler.DefaultPlaceholderHandler;
import com.quinn.util.constant.StringConstant;

import java.util.Iterator;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 扩展日志接口对象
 *
 * @author Qunhua.Liao
 * @since 2020-03-30
 */
public class LoggerExtendFactory {

    /**
     * 日志生成器
     */
    private static LoggerGenerator loggerGenerator;

    /**
     * 日志容器
     */
    private static final Map<String, LoggerExtend> LOGGER_MAP;

    static {
        LOGGER_MAP = new ConcurrentHashMap<>();
        // 初始日志工厂
        DefaultLoggerFactoryInitializer.staticInitLoggerFactory(System.getProperties());
    }

    /**
     * 设置日志生成器
     *
     * @param loggerGenerator
     */
    public static void setLoggerGenerator(LoggerGenerator loggerGenerator) {
        LoggerExtendFactory.loggerGenerator = loggerGenerator;
        PlaceholderHandler placeholderHandler = loggerGenerator.ofPlaceholderHandler(null);
        for (Map.Entry<String, LoggerExtend> entry : LOGGER_MAP.entrySet()) {
            entry.getValue().setPlaceholderHandler(placeholderHandler);
        }
    }

    /**
     * 通过类名获取日志
     *
     * @param className 类名
     * @return 日志对象
     */
    public static LoggerExtend getLogger(String className) {
        LoggerExtend loggerExtend = LOGGER_MAP.get(className);
        if (loggerExtend == null) {
            loggerExtend = loggerGenerator.generator(className);
            LOGGER_MAP.put(className, loggerExtend);
        }
        return loggerExtend;
    }

    /**
     * 通过类对象获取日志名称
     *
     * @param clazz 类对象
     * @return 日志对象
     */
    public static LoggerExtend getLogger(Class clazz) {
        return getLogger(clazz.getName());
    }

}
