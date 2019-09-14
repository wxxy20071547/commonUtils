package com.kevin.common.juc;


import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *加法
 CyclicBarrier初始化时规定一个数目，然后计算调用了CyclicBarrier.await()进入等待的线程数。当线程数达到了这个数目时，所有进入等待状态的线程被唤醒并继续。
 CyclicBarrier就象它名字的意思一样，可看成是个障碍， 所有的线程必须到齐后才能一起通过这个障碍。
 CyclicBarrier初始时还可带一个Runnable的参数， 此Runnable任务在CyclicBarrier的数目达到后，所有其它线程被唤醒前被执行。

 */
public class CyclicBarrierTest {

    static class Write implements Runnable {
        private CyclicBarrier cyclicBarrier;

        public Write(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println("线程"+Thread.currentThread().getName()+"到达...");
            try {
                cyclicBarrier.await();//必须的等待所有线程到期才继续执行
            } catch (Exception e) {
            }
            System.out.println("线程"+Thread.currentThread().getName()+"开动...");

        }
    }

    public static void main(String[] args) {
        ExecutorService ex = Executors.newFixedThreadPool(10);
        CyclicBarrier c = new CyclicBarrier(5,new Runnable(){

            @Override
            public void run() {
                System.out.println("线程"+Thread.currentThread().getName()+"全部到达");
            }
        });
        for (int i=0;i<10;i++){
            ex.submit(new Write(c));
        }

        ex.shutdown();
    }
}
