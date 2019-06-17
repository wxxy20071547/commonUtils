package com.kevin.common.designMode.observer;

/**
 * @author kevin
 * @date 2019-06-17 10:31
 */
public class Test {
    public static void main(String[] args) {
        BossObserver teacher = new BossObserver();
        WorkerObserver zhangSan = new WorkerObserver("张三", teacher);
        WorkerObserver LiSi = new WorkerObserver("李四", teacher);
        WorkerObserver WangWu = new WorkerObserver("王五", teacher);

        teacher.setWork("画图");
        teacher.setWork("准备材料");
        teacher.setWork("制作");

    }
}
