package com.kevin.common.proxy;

import com.kevin.common.proxy.impl.PeopleServiceImpl;
import com.kevin.common.proxy.service.PeopleService;
import org.junit.jupiter.api.Test;

public class ProxyTest {
    @Test
    public  void testJdkProxy(){
        JdkProxyFactory proxyFactory = new JdkProxyFactory();
        PeopleService peopleService = (PeopleService) proxyFactory.createProxyIntance(new PeopleServiceImpl());
        peopleService.say("jdk proxy is ok");
    }

    @Test
    public  void testCglibProxy(){
        CglibProxyFactory proxyFactory = new CglibProxyFactory();
        PeopleServiceImpl peopleService = (PeopleServiceImpl) proxyFactory.createProxyIntance(new PeopleServiceImpl());
        peopleService.say("cglib proxy is ok");
    }
}
