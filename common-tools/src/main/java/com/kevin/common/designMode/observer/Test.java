package com.kevin.common.designMode.observer;

/**
 * @author kevin
 * @date 2019-06-17 10:31
 */
public class Test {
    public static void main(String[] args) {
        BossObserver boss = new BossObserver();
        WorkerObserver zhangSan = new WorkerObserver("张三", boss);
        WorkerObserver LiSi = new WorkerObserver("李四", boss);
        WorkerObserver WangWu = new WorkerObserver("王五", boss);

        boss.setWork("画图");
        boss.setWork("准备材料");
        boss.setWork("制作");

    }
}
