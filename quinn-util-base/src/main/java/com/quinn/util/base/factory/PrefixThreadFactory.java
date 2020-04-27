package com.quinn.util.base.factory;

import com.quinn.util.base.util.ThreadUtil;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 前缀命名任务线程工厂
 *
 * @author Qunhua.Liao
 * @since 2020-04-26
 */
public class PrefixThreadFactory implements ThreadFactory {

    /**
     * 线程组
     */
    private final ThreadGroup group;

    /**
     * 线程数量
     */
    private final AtomicInteger threadNumber = new AtomicInteger(1);

    /**
     * 线程名前缀
     */
    private final String namePrefix;

    public PrefixThreadFactory(String namePrefix) {
        SecurityManager s = System.getSecurityManager();
        group = (s != null) ? s.getThreadGroup() : Thread.currentThread().getThreadGroup();
        this.namePrefix = namePrefix;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(group, r,
                namePrefix + threadNumber.getAndIncrement(),
                0);

        return ThreadUtil.commonThread(t);
    }
}
