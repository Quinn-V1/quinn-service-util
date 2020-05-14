package com.quinn.util.base;

/**
 * 线程操作工具类
 *
 * @author Qunhua.Liao
 * @since 2020-02-16
 */
public class ThreadUtil {

    public static Thread commonThread(Thread t) {
        if (t.isDaemon()) {
            t.setDaemon(false);
        }

        if (t.getPriority() != Thread.NORM_PRIORITY) {
            t.setPriority(Thread.NORM_PRIORITY);
        }
        return t;
    }

}
