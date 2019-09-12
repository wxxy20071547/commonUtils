package com.kevin.common.juc;

import java.util.concurrent.Semaphore;

/**
 * 对于单个Java应用，我们如何限制其中某个方法methodA()被调用的并发数不能超过100，如果超过100，超出的请求就直接返回null或抛异常
 * Created by kevin on 2018/9/21.
 */
public class SemaphoreTest {

    private static Semaphore semaphore = new Semaphore(0);

    public static Integer methodA() {
        Integer result = null;
        if(!semaphore.tryAcquire()) {
            return null;
        }

        try {
            // TODO 方法中的业务逻辑
            return result;
        } finally {
            semaphore.release();
        }
    }

    public static void main(String[] args) {
        methodA();
    }


}
