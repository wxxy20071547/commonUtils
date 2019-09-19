package com.kevin.common;


import com.google.common.collect.Maps;
import com.kevin.common.domain.People;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.nutz.lang.Mirror;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantLock;


/**
 * Created by kevin on 2017/11/29.
 */
public class Test {
    final int a;

    {
        print();
        a = 7;
        System.out.println(a);
    }

    private void print() {
        System.out.println(a);
    }


    public static void main(String[] args) {

        System.out.println(DigestUtils.md5Hex("浙A6D2Q9"));
        System.out.println(DigestUtils.md5Hex("浙A6D2Q2"));
        System.out.println(DigestUtils.md5Hex("浙A6D2Q8"));
        System.out.println(DigestUtils.md5Hex("浙A6D2Q0"));
        System.out.println(DigestUtils.md5Hex("浙A6D2Q9"));



        Map<String, String> map = Maps.newHashMap();
        map.put("1", "1");
        map.put("2", "2");
        map.put("1", "2");
        map.put("2", "2");

        map.forEach((key, value) -> {
            if (StringUtils.equals("1", value)) {
                return;
            }

            System.out.println(value);
        });


        new Test();


        Map cMap = Maps.newHashMap();
        List list = new CopyOnWriteArrayList();
        Map currentMap = new ConcurrentHashMap();
        cMap.put(null, null);
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
        ReentrantLock reentrantLock = new ReentrantLock();


        for (int i, j, k; ; ) {

        }

    }

    public static void chufa() {
        try {
            int i = 1 / 0;
        } catch (Exception e) {

        } finally {
            System.out.println("finally");
        }
    }


}
