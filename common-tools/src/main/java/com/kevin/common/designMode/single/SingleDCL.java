package com.kevin.common.designMode.single;

public class SingleDCL {
    private static volatile SingleDCL instance = null;// volatile是为了保证禁止指令重排序

    private SingleDCL() {
        System.out.println("begin to init class");
    }

    public static SingleDCL getInstance() {
        if (instance == null) {
            synchronized (SingleDCL.class) {
                if (instance == null) {
                    instance = new SingleDCL();//这里可能会发生指令重排 加volatile
                }
            }
        }
        return instance;
    }

}
