package com.kevin.common.domain;

import lombok.Data;
import lombok.ToString;
import org.apache.commons.lang.StringUtils;

import java.text.MessageFormat;

/**
 * Created by kevin on 2017/11/29.
 */
@Data
@ToString
public class People {
    private String name = "joy";
    private int age;
    private int money;
    private String address;
    private String sex;

    public String doSomething(String name,String sex, int age){
        return MessageFormat.format("a people name :{0},sex :{1},age is {2}", name, sex, age);
    }

    public People sum(People p){
        if (StringUtils.isBlank(sex)){
            this.sex = p.getSex();
        }

        this.age += p.getAge();
        this.money += p.getMoney();
        return this;
    }
}
