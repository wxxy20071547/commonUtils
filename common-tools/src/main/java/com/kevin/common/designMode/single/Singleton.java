package com.kevin.common.designMode.single;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 不用synchronized和lock能实现线程安全的单例
 * @author kevin
 * @date 2019-09-12 16:15
 */
public class Singleton {

    private static final AtomicReference<Singleton> INSTANCE = new AtomicReference<Singleton>();

    private Singleton() {
    }

    public static Singleton getInstance() {
        for (; ; ) {
            Singleton singleton = INSTANCE.get();
            if (null != singleton) {
                return singleton;
            }

            singleton = new Singleton();
            if (INSTANCE.compareAndSet(null, singleton)) {
                return singleton;
            }
        }
    }
}
