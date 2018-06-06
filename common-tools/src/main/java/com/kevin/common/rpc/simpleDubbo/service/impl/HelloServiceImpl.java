package com.kevin.common.rpc.simpleDubbo.service.impl;

import com.kevin.common.rpc.simpleDubbo.service.HelloService;

/**
 * Created by kevin on 2018/4/10.
 */
public class HelloServiceImpl implements HelloService {

    public String hello(String name) {
        return "Hello " + name;
    }
}
