package com.kevin.common.proxy;

import org.junit.Test;

import com.kevin.common.proxy.impl.PeopleServiceImpl;
import com.kevin.common.proxy.service.PeopleService;

public class ProxyTest {
    @Test
    public void testJdkProxy() {
        JdkProxyFactory proxyFactory = new JdkProxyFactory();
        PeopleService peopleService = (PeopleService)proxyFactory.createProxyIntance(new PeopleServiceImpl());
        peopleService.say("jdk proxy is ok");
    }

    @Test
    public void testCglibProxy() {
        CglibProxyFactory proxyFactory = new CglibProxyFactory();
        PeopleServiceImpl peopleService = (PeopleServiceImpl)proxyFactory.createProxyIntance(new PeopleServiceImpl());
        peopleService.say("cglib proxy is ok");
    }
}
