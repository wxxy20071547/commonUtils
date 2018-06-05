package com.kevin.common.serviceLoader;

/**
 * Created by kevin on 2018/6/5.
 */
public class Mondeo implements  Car{
    @Override
    public void loadPeople() {
        System.out.println("I am Ford Mondeo，I can load 5 peoples");
    }
}
