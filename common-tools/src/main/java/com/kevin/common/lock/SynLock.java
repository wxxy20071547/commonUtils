package com.kevin.common.lock;

/**
 * @author kevin
 * @date 2019-05-05 14:33
 *
 *  javap -c SynLock  javap –verbose SynLock 可以查看字节码
 */
public class SynLock {

    public  void test() {
        synchronized (this){
            System.out.println();
        }


    }


    public static void main(String[] args) {
        new SynLock().test();
    }
}
