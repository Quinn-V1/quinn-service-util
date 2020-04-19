package com.quinn.util.base.handler;

import com.quinn.util.base.api.LoggerFactoryInitializer;
import com.quinn.util.base.api.LoggerGenerator;
import com.quinn.util.base.constant.ConfigConstant;
import com.quinn.util.base.factory.LoggerExtendFactory;
import com.quinn.util.constant.StringConstant;

import java.util.Iterator;
import java.util.Properties;
import java.util.ServiceLoader;

/**
 * 默认工厂初始化方法
 *
 * @author Qunhua.Liao
 * @since 2020-03-31
 */
public class DefaultLoggerFactoryInitializer implements LoggerFactoryInitializer {

    /**
     * 初始化日志工厂
     *
     * @param properties 参数
     */
    public static void staticInitLoggerFactory(Properties properties) {
        ServiceLoader<LoggerFactoryInitializer> initializers = ServiceLoader.load(LoggerFactoryInitializer.class);
        String loggerInitializerName = properties.getProperty(ConfigConstant.CONFIG_KEY_LOGGER_INITIALIZER_NAME,
                ConfigConstant.SYSTEM_LOGGER_INITIALIZER_DEFAULT);

        Iterator<LoggerFactoryInitializer> initializerIterator = initializers.iterator();
        LoggerFactoryInitializer initializer = null;
        while (initializerIterator.hasNext()) {
            LoggerFactoryInitializer next = initializerIterator.next();
            if (initializer == null || loggerInitializerName.equals(next.getName())) {
                initializer = next;
            }
        }

        if (initializer != null) {
            initializer.initLoggerFactory(properties);
        }
    }

    @Override
    public String getName() {
        return DefaultLoggerFactoryInitializer.class.getSimpleName();
    }

    @Override
    public void initLoggerFactory(Properties properties) {
        ServiceLoader<LoggerGenerator> loggerGenerators = ServiceLoader.load(LoggerGenerator.class);
        Iterator<LoggerGenerator> loggerGeneratorIterator = loggerGenerators.iterator();
        String loggerGeneratorName = properties.getProperty(ConfigConstant.CONFIG_KEY_LOGGER_GENERATOR_NAME,
                ConfigConstant.SYSTEM_LOGGER_GENERATOR_DEFAULT);

        LoggerGenerator loggerGenerator = null;
        while (loggerGeneratorIterator.hasNext()) {
            loggerGenerator = loggerGeneratorIterator.next();
            if (loggerGeneratorName.equals(loggerGenerator.getName())) {
                LoggerExtendFactory.setLoggerGenerator(loggerGenerator);
                break;
            }
        }

        placeholderHandlerOf(loggerGenerator);

        LoggerExtendFactory.setLoggerGenerator(loggerGenerator);
    }

    /**
     * 设置占位符解析器 (最上课能被覆盖的方法)
     *
     * @param loggerGenerator 占位符解析器
     */
    public void placeholderHandlerOf(LoggerGenerator loggerGenerator) {
        loggerGenerator.ofPlaceholderHandler(
                new DefaultPlaceholderHandler(StringConstant.CHAR_OPEN_BRACE, StringConstant.CHAR_CLOSE_BRACE));
    }

}
