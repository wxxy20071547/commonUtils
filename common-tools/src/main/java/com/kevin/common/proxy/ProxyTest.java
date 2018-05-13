package com.kevin.common.proxy;

import com.kevin.common.proxy.impl.PeopleServiceImpl;
import com.kevin.common.proxy.service.PeopleService;

public class ProxyTest {
    public static void main(String[] args) {
        testJdkProxy();
        testCglibProxy();
    }

    public static void testJdkProxy(){
        JdkProxyFactory proxyFactory = new JdkProxyFactory();
        PeopleService peopleService = (PeopleService) proxyFactory.createProxyIntance(new PeopleServiceImpl());
        peopleService.say("jdk proxy is ok");
    }


    public static void testCglibProxy(){
        CglibProxyFactory proxyFactory = new CglibProxyFactory();
        PeopleServiceImpl peopleService = (PeopleServiceImpl) proxyFactory.createProxyIntance(new PeopleServiceImpl());
        peopleService.say("cglib proxy is ok");
    }
}
