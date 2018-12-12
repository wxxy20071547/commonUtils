package com.kevin.common.designMode.pcModel;

import com.google.common.collect.Lists;
import lombok.Data;

import java.util.List;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by kevin on 2018/12/8.
 */
@Data
public class DelayQueue<T> {
    private BlockingDeque<T> queue;
    private DealService<T> dealService;
    private  ExecutorService e;
    private int nThreads  = 1;


    public void start() {
        e = Executors.newFixedThreadPool(nThreads);
        queue = new LinkedBlockingDeque<T>();
        e.execute(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (queue.size() > 0) {
                        List<T> list = Lists.newArrayList();
                        queue.drainTo(list);
                        System.out.println(Thread.currentThread().getName()+">>>>>>>>>>>>>list's size is " + list.size());
                        list.forEach(t -> {
                            dealService.deal(t);
                        });

                    } else {//如果队列为空，延迟1秒
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                    }
                }

            }
        });


    }

    public void add(T t) {
        try {
            queue.put(t);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
