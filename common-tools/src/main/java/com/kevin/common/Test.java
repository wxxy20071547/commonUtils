package com.kevin.common;


import com.google.common.collect.Lists;

import com.kevin.common.domain.People;

import org.nutz.lang.Mirror;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by kevin on 2017/11/29.
 */
public class Test {

    public static void main(String[] args) {
        People people = new People();
        Mirror peopleMirror = Mirror.me(people);
        peopleMirror.invoke(people,"setName","tom");
        System.out.println(peopleMirror.invoke(people,"getName"));


        List<String> list = Lists.newArrayList();




        Map<String,Object> map = new HashMap<>();

        map.put("1",222);
    }
}
