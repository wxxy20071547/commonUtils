package com.kevin.common.juc;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 三个线程  A B C
 * A打印完5次 B打印玩10次 C再打印15次
 * 循环十次
 */
public class MultThreadPrint {
    private int number = 1;// 1,2,3
    private Lock lock = new ReentrantLock();
    private Condition cA = lock.newCondition();
    private Condition cB = lock.newCondition();
    private Condition cC = lock.newCondition();


    public void print5() {
        lock.lock();
        try {
            while (number != 1) cA.await();
            for (int i = 0; i < 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            number = 2;
            cB.signal();
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
    }

    public void print10() {
        lock.lock();
        try {
            while (number != 2) cB.await();
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            number = 3;
            cC.signal();
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
    }

    public void print15() {
        lock.lock();
        try {
            while (number != 3) cC.await();//多线程判读不能用if 必须用while
            for (int i = 0; i < 15; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            number = 1;
            cA.signal();
        } catch (Exception e) {

        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        MultThreadPrint multThreadPrint = new MultThreadPrint();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) multThreadPrint.print5();
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) multThreadPrint.print10();
        }, "B").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) multThreadPrint.print15();
        }, "C").start();
    }
}
