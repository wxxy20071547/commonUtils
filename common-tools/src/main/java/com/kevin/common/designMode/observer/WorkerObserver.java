package com.kevin.common.designMode.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * @author kevin
 * @date 2019-06-17 10:30
 */
public class WorkerObserver implements Observer {
    private Observable ob;
    private String name;

    public WorkerObserver(String name, Observable ob) {
        this.ob = ob;
        this.name = name;
        ob.addObserver(this);
    }


    /**
     * 监听
     * @param o
     * @param arg
     */
    @Override
    public void update(Observable o, Object arg) {
        BossObserver t = (BossObserver) o;
        System.out.println(name + "得到工作信息:" + t.getInfo());

    }
}
