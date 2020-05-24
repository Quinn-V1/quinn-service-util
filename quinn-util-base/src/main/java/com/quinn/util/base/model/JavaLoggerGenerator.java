package com.quinn.util.base.model;

import com.quinn.util.base.api.LoggerExtend;
import com.quinn.util.base.api.LoggerGenerator;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Java 原始日志框架
 *
 * @author Qunhua.Liao
 * @since 2020-05-24
 */
public class JavaLoggerGenerator implements LoggerGenerator {

    @Override
    public LoggerExtend generate(String logName) {
        Logger logger = Logger.getLogger(logName);
        return new LoggerExtend() {
            @Override
            public String getName() {
                return null;
            }

            @Override
            public void trace(String format, Object... args) {
                logger.log(Level.FINE, format, args);
            }

            @Override
            public void traceError(String format, Throwable error, Object... args) {
                logger.log(Level.FINE, format, args);
                error.printStackTrace();
            }

            @Override
            public void debug(String format, Object... args) {
                logger.log(Level.CONFIG, format, args);
            }

            @Override
            public void debugError(String format, Throwable error, Object... args) {
                logger.log(Level.CONFIG, format, args);
                error.printStackTrace();
            }

            @Override
            public void info(String format, Object... args) {
                logger.log(Level.INFO, format, args);
            }

            @Override
            public void infoError(String format, Throwable error, Object... args) {
                logger.log(Level.INFO, format, args);
                error.printStackTrace();
            }

            @Override
            public void warn(String format, Object... args) {
                logger.log(Level.WARNING, format, args);
            }

            @Override
            public void warnError(String format, Throwable error, Object... args) {
                logger.log(Level.WARNING, format, args);
                error.printStackTrace();
            }

            @Override
            public void error(String format, Object... args) {
                logger.log(Level.SEVERE, format, args);
            }

            @Override
            public void errorError(String format, Throwable error, Object... args) {
                logger.log(Level.SEVERE, format, args);
                error.printStackTrace();
            }
        };
    }

    @Override
    public String getName() {
        return this.getClass().getSimpleName();
    }
}
