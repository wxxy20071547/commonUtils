package com.kevin.common.utils;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author kevin
 * @date 2019-04-08 14:54
 */
public class RandomUtils {

    private static int getRandomNumberInRange(int min, int max) {
        Random r = new Random();
        return r.ints(min, (max + 1)).findFirst().getAsInt();
    }

    /**
     * 一次取多个随机数
     * @param min
     * @param max
     * @param randomNum
     * @return
     */
    private static List<Integer> getRandomNumberInRange(int min, int max, int randomNum) {
        Random r = new Random();
        return r.ints(randomNum, min, (max + 1)).boxed().collect(Collectors.toList());
    }

    public static void main(String[] args) {
        System.out.println(getRandomNumberInRange(1,10));
        System.out.println(getRandomNumberInRange(1,10,10));
    }
}
