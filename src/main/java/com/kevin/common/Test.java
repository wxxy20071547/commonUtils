package com.kevin.common;


import com.kevin.common.domain.People;
import org.nutz.lang.Mirror;

/**
 * Created by kevin on 2017/11/29.
 */
public class Test {

    public static void main(String[] args) {
        People people = new People();
        Mirror peopleMirror = Mirror.me(people);
        peopleMirror.invoke(people,"setName","tom");
        System.out.println(peopleMirror.invoke(people,"getName"));
    }
}
