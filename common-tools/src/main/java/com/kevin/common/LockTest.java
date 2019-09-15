package com.kevin.common;

import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by kevin on 2018/5/2.
 */
public class LockTest {

    public static void main(String[] args) {
        int cpuNum = Runtime.getRuntime().availableProcessors();// 获取机器CPU个数
        ExecutorService executorService = Executors.newFixedThreadPool(cpuNum + 1);
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("thread=" + Thread.currentThread().getName());
            }
        });

        //获取公平锁
        ReentrantLock lock = new ReentrantLock(true);//ReentrantLock默认是不公平

        try {
            lock.lock();
            lock.tryLock();//没有超时间，底层还是不公平是实现
            lock.tryLock(500, TimeUnit.SECONDS);
            lock.lockInterruptibly();
            lock.unlock();
            Condition a = lock.newCondition();
            a.await();

        } catch (Exception e) {

        }

    }


}
