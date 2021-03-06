package com.kevin.common.designMode.pcModel;

import com.kevin.common.domain.People;

/**
 * Created by kevin on 2018/12/8.
 */
public class Test {

    public static void main(String[] args) {
        DelayQueue<People> delayQueue = new DelayQueue<>();
        delayQueue.setQueueSize(10000);
        delayQueue.setDealService(new DealServiceImpl());
        delayQueue.start();




        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 600; i++) {
                    People people = new People();
                    people.setName("tom" + i);
                    if (i % 2 == 0) {
                        people.setSex("" + 0);
                    } else {
                        people.setSex("" + 1);
                    }
                    people.setAge(4 - i);
                    people.setMoney(i * 2);
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    delayQueue.add(people);
                }
            }
        }).start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 600; i++) {
                    People people = new People();
                    people.setName("jom" + i);
                    if (i % 2 == 0) {
                        people.setSex("" + 0);
                    } else {
                        people.setSex("" + 1);
                    }
                    people.setAge(4 - i);
                    people.setMoney(i * 2);
                    delayQueue.add(people);
                }
            }
        }).start();



    }
}
