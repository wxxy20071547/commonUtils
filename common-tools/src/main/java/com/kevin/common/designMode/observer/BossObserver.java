package com.kevin.common.designMode.observer;

import lombok.Getter;

import java.util.Observable;

/**
 * @author kevin
 * @date 2019-06-17 10:27
 */
public class BossObserver extends Observable {
    @Getter
    private String info;

    public void setWork(String info) {
        this.info=info;
        System.out.println("布置的工作是"+info);
        setChanged();
        notifyObservers();
    }

}
