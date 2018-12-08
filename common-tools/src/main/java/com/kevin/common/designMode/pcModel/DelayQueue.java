package com.kevin.common.designMode.pcModel;

import com.kevin.common.domain.People;
import lombok.Data;

import java.util.Objects;
import java.util.concurrent.*;

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
                T t;
                try {
                    while (true) {
                        if (Objects.nonNull(t = queue.take())) {
                            System.out.println("Consumed " + t);
                            dealService.deal(t);
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
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
