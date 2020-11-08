package com.quinn.util.licence.model;

import com.quinn.util.base.exception.MethodNotFoundException;
import com.quinn.util.base.model.BaseResult;
import com.quinn.util.base.model.LicenceInfo;

import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

/**
 * 应用程序服务
 *
 * @author Qunhua.Liao
 * @since 2020-03-27
 */
public class ApplicationInfo {

    private ApplicationInfo() {
    }

    public static ApplicationInfo getInstance() {
        return null;
    }

    public static BaseResult init(LicenceInfo data) {
        throw new MethodNotFoundException();
    }

    public void generateKey() {
        throw new MethodNotFoundException();
    }

    public static String getAppKey() {
        throw new MethodNotFoundException();
    }

    public void visitIncrease() {
        throw new MethodNotFoundException();
    }

    public void requestIncrease() {
        throw new MethodNotFoundException();
    }

    public void requestDecrease() {
        throw new MethodNotFoundException();
    }

    public void sessionIncrease() {
        throw new MethodNotFoundException();
    }

    public void sessionDecrease() {
        throw new MethodNotFoundException();
    }

    public void starting() {
        throw new MethodNotFoundException();
    }

    public void stated() {
        throw new MethodNotFoundException();
    }

    public void waiting() {
        throw new MethodNotFoundException();
    }

    public void stopping() {
        throw new MethodNotFoundException();
    }

    public boolean isSupportLang(String langCode) {
        throw new MethodNotFoundException();
    }

    public String appName(String langCode) {
        throw new MethodNotFoundException();
    }

    public void setApplicationProperties(Properties properties) {
        throw new MethodNotFoundException();
    }

    public void addMetadata(ByteArrayOutputStream os) {
        throw new MethodNotFoundException();
    }

    public Properties getApplicationProperties() {
        throw new MethodNotFoundException();
    }

    public Object getConfigMetadataMap() {
        throw new MethodNotFoundException();
    }

    public LongAdder getVisitTimes() {
        throw new MethodNotFoundException();
    }

    public AtomicInteger getRemainRequestCount() {
        throw new MethodNotFoundException();
    }

    public AtomicInteger getSessionCount() {
        throw new MethodNotFoundException();
    }

    public void setHost(String host) {
        throw new MethodNotFoundException();
    }

    public void setPort(int port) {
        throw new MethodNotFoundException();
    }

    public void setApplicationName(String applicationName) {
        throw new MethodNotFoundException();
    }

    public LocalDateTime getStartDate() {
        throw new MethodNotFoundException();
    }
}
