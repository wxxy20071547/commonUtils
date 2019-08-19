package com.kevin.common.javassist;

import lombok.extern.slf4j.Slf4j;

/**
 * @author kevin
 * @date 2019-08-19 14:55
 */
@Slf4j
public class Point {


    public void move(int x, int y) {
        log.info("begin move x:{},y:{}", x, y);
    }
}
