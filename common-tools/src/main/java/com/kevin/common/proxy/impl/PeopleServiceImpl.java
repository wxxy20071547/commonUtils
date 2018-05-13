package com.kevin.common.proxy.impl;

import com.kevin.common.proxy.service.PeopleService;

public class PeopleServiceImpl implements PeopleService{
    @Override
    public void say(String what) {
        System.out.println("hello , say:" + what);
    }
}
