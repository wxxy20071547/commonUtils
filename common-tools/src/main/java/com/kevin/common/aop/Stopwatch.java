package com.kevin.common.aop;


public final class Stopwatch {

    private long startTime = System.nanoTime();

    public static Stopwatch start(){
        return new Stopwatch();
    }

    public long stopInMS(){
        return (System.nanoTime() - startTime)/1000000;
    }

}
