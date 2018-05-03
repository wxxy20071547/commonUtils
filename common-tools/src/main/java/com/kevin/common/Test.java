package com.kevin.common;


import com.kevin.common.domain.People;
import com.kevin.common.rpc.service.HelloService;
import com.kevin.common.rpc.service.impl.AbstractHelloService;
import lombok.Synchronized;
import org.nutz.lang.Mirror;

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





    }
}
