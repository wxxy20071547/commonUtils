package com.kevin.common.domain;

import lombok.Data;
import lombok.ToString;

import java.text.MessageFormat;

/**
 * Created by kevin on 2017/11/29.
 */
@Data
@ToString
public class People {
    private String name = "joy";
    private int age;
    private String address;
    private String sex;

    public String doSomething(String name,String sex, int age){
        return MessageFormat.format("a people name :{0},sex :{1},age is {2}", name, sex, age);
    }
}
