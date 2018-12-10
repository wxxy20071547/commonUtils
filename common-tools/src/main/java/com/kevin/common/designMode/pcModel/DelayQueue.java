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
    private final static ExecutorService e = Executors.newSingleThreadExecutor();


    public void start() {
        queue = new LinkedBlockingDeque<T>();
        e.execute(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (queue.size() > 0) {
                        List<T> list = Lists.newArrayList();
                        queue.drainTo(list);
                        System.out.println(">>>>>>>>>>>>>list's size is "+ list.size());
                        list.forEach(t -> {
                            dealService.deal(t);
                        });

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
