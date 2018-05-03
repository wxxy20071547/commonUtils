package com.kevin.common;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by kevin on 2018/5/2.
 */
public class LockTest {

    public static void main(String[] args) {
        //获取公平锁
        ReentrantLock lock = new ReentrantLock(true);

        try{
            lock.lock();
            lock.tryLock();
            lock.tryLock(500, TimeUnit.SECONDS);
            lock.lockInterruptibly();
            lock.unlock();
            Condition a = lock.newCondition();
            a.await();

        }catch (Exception e){

        }

    }


}
