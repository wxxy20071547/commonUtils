package com.kevin.common.serviceLoader;

import java.util.ServiceLoader;

/**
 * Created by kevin on 2018/6/5.
 */
public class Test {
    public static void main(String[] args) {
        ServiceLoader<Car> serviceLoader = ServiceLoader.load(Car.class);
        for (Car command : serviceLoader) {
            command.loadPeople();
        }
    }
}
