package com.kevin.common;


import com.google.common.collect.Lists;

import com.google.common.collect.Maps;
import com.kevin.common.domain.People;

import org.nutz.lang.Mirror;

import java.util.*;
import java.util.concurrent.PriorityBlockingQueue;


/**
 * Created by kevin on 2017/11/29.
 */
public class Test {

    public static void main(String[] args) {
        People people = new People();
        Mirror peopleMirror = Mirror.me(people);
        peopleMirror.invoke(people, "setName", "tom");
        System.out.println(peopleMirror.invoke(people, "getName"));


        //按访问顺序
        Map linkMap = new LinkedHashMap(16, 0.75f, true);
        for (int i = 0; i < 10; i++) {
            linkMap.put(String.valueOf(i), i);
        }
        linkMap.get("4");
        linkMap.get("1");
        System.out.println(linkMap);
        try {
            chufa();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public static void chufa() {
        try {
            int i = 1/0;
        } catch (Exception e){

        }finally {
            System.out.println("finally");
        }
    }


}
