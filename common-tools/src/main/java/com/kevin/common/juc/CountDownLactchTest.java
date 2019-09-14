package com.kevin.common.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 减法
 */
public class CountDownLactchTest implements Runnable {
    static final CountDownLatch latch = new CountDownLatch(5);//count 必须大于线程数
    static final CountDownLactchTest test = new CountDownLactchTest();

    @Override
    public void run() {
        latch.countDown();
        System.out.println("check complete");
    }


    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 100; i++) {
            executorService.submit(test);
        }
        latch.await();

        System.out.println("is over");
        executorService.shutdown();
    }
}
