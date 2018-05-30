package com.kevin.common;


import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.util.concurrent.RateLimiter;
import com.kevin.common.domain.People;
import com.kevin.common.rpc.service.HelloService;
import com.kevin.common.rpc.service.impl.AbstractHelloService;
import lombok.Synchronized;
import org.nutz.lang.Mirror;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by kevin on 2017/11/29.
 */
public class Test {

    public static void main(String[] args) {
        HelloService hs = new AbstractHelloService() {
            @Override
            public String hello(String name) {
                return "this is a new " +name;
            }
        };
        System.out.println(hs.hello("very good"));
        People people = new People();
        Mirror peopleMirror = Mirror.me(people);
        peopleMirror.invoke(people,"setName","tom");
        System.out.println(peopleMirror.invoke(people,"getName"));


        List<String> list = Lists.newArrayList();




        Map<String,Object> map = new HashMap<>();

        map.put("1",222);
    }
}
