package com.kevin.common.designMode.pcModel;

import com.kevin.common.domain.People;

/**
 * Created by kevin on 2018/12/8.
 */
public class DealServiceImpl<T> implements DealService<T> {


    @Override
    public void deal(T t) {
     People p = (People)t;
        System.out.println("deal name="+p.getName());
    }
}
