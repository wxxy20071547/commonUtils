package com.kevin.common.serviceLoader;

/**
 * Created by kevin on 2018/6/5.
 */
public class Chery implements Car{
    @Override
    public void loadPeople() {
        System.out.println("I am Chery car, I can load 2 peoples");
    }
}
