package com.kevin.common.gc;

import java.nio.ByteBuffer;

/**
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
 */
public class DirectMemoryTest {

    public static void main(String[] args) {
        System.out.println("本地配置的最大本地直接内存=" + (sun.misc.VM.maxDirectMemory() / (double) 1024 / 1024 / 1024) + "G");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ByteBuffer bb = ByteBuffer.allocateDirect(6 * 1024 * 1024);
    }
}
