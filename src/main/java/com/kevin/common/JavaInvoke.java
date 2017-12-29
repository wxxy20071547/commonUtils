package com.kevin.common;

import com.kevin.common.domain.People;
import org.nutz.lang.Mirror;

/**
 * Created by kevin on 2017/12/29.
 */
public class JavaInvoke {
    public static void main(String[] args) {
        People people = new People();
        Mirror peopleMirror = Mirror.me(people);
        peopleMirror.invoke(people,"setName","tom");
        System.out.println(peopleMirror.invoke(people,"getName"));
        System.out.println(peopleMirror.invoke(people, "doSomething", "kevin", "man", 28));
    }

}
