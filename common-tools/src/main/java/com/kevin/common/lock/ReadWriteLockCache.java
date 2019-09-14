package com.kevin.common.lock;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockCache {

    private static ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    private volatile Map<String, String> map = Maps.newHashMap();

    public void get(String key) {


        try {
            rwLock.readLock().lock();
            System.out.println(Thread.currentThread().getName() + "正在读取");
            Thread.sleep(3000);
            String value = map.get(key);
            System.out.println(Thread.currentThread().getName() + "读取完成" + value);
        } catch (Exception e) {

        } finally {
            rwLock.readLock().unlock();
        }

    }


    public void put(String key, String value) {


        try {
            rwLock.writeLock().lock();
            System.out.println(Thread.currentThread().getName() + "正在写入" + key);
            Thread.sleep(3000);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "写入完成");
        } catch (Exception e) {

        } finally {
            rwLock.writeLock().unlock();
        }

    }


    public static void main(String[] args) {
        ReadWriteLockCache lockCache = new ReadWriteLockCache();
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            new Thread(() -> {
                try {
                    lockCache.put(String.valueOf(finalI), String.valueOf(finalI));
                } catch (Exception e) {

                }

            }, String.valueOf(i)).start();
        }


        for (int i = 0; i < 5; i++) {
            int finalI = i;
            new Thread(() -> {
                try {
                    lockCache.get(String.valueOf(finalI));
                } catch (Exception e) {

                }

            }, String.valueOf(i)).start();
        }
    }


}
