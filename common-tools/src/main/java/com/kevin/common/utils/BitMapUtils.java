package com.kevin.common.utils;

import java.util.BitSet;

/**
 * Created by kevin on 2018/10/13.
 *
 *   BitMap从字面的意思，很多人认为是位图，其实准确的来说，翻译成基于位的映射，怎么理解呢？

 举一个例子，有一个无序有界int数组{1,2,5,7},初步估计占用内存4*4=16字节，这倒是没什么奇怪的，但是假如有10亿个这样的数呢，10亿*4/(1024*1024*1024)=3.72G左右。如果这样的一个大的数据做查找和排序，那估计内存也崩溃了，有人说，这些数据可以不用一次性加载，那就是要存盘了，存盘必然消耗IO。我们提倡的是高性能，这个方案直接不考虑。

 二、问题分析

 如果用BitMap思想来解决的话，就好很多，那么BitMap是怎么解决的啊，如下：

 一个byte是占8个bit，如果每一个bit的值就是有或者没有，也就是二进制的0或者1，如果用bit的位置代表数组值有还是没有，那么0代表该数值没有出现过，1代表该数组值出现过。不也能描述数据了吗？具体如下图：
 */

public class BitMapUtils {
    //保存数据的
    private byte[] bits;

    //能够存储多少数据
    private int capacity;


    public BitMapUtils(int capacity) {
        this.capacity = capacity;

        //1 bit能存储8个数据，那么capacity数据需要多少个bit呢，capacity/8+1,右移3位相当于除以8
        bits = new byte[(capacity >> 3) + 1];
    }

    public void add(int num) {
        // num/8得到byte[]的index
        int arrayIndex = num >> 3;

        // num%8得到在byte[index]的位置
        int position = num & 0x07;

        //将1左移position后，那个位置自然就是1，然后和以前的数据做|，这样，那个位置就替换成1了。
        bits[arrayIndex] |= 1 << position;
    }

    public boolean contain(int num) {
        // num/8得到byte[]的index
        int arrayIndex = num >> 3;

        // num%8得到在byte[index]的位置
        int position = num & 0x07;

        //将1左移position后，那个位置自然就是1，然后和以前的数据做&，判断是否为0即可
        return (bits[arrayIndex] & (1 << position)) != 0;
    }

    public void clear(int num) {
        // num/8得到byte[]的index
        int arrayIndex = num >> 3;

        // num%8得到在byte[index]的位置
        int position = num & 0x07;

        //将1左移position后，那个位置自然就是1，然后对取反，再与当前值做&，即可清除当前的位置了.
        bits[arrayIndex] &= ~(1 << position);

    }

    public static void main(String[] args) {
        BitMapUtils bitmap = new BitMapUtils(100);
        for (int i = 0; i < 100; i++) {
            bitmap.add(i);
        }

        System.out.println("插入7成功");

        boolean isexsit = bitmap.contain(70);
        System.out.println("7是否存在:" + isexsit);

        bitmap.clear(7);
        isexsit = bitmap.contain(70);
        System.out.println("7是否存在:" + isexsit);


        //或者运用jdk自带工具类
        BitSet bitSet = new BitSet();

        for (int i = 0; i < 2000000000; i++) {
            bitSet.set(i);
        }
        System.out.println(bitSet.get(302));
    }
}