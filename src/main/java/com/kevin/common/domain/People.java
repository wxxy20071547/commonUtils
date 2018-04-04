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
    private String name;
    private int age;
    private int money;
    private String address;
    private String sex;
    private int total;

    public String doSomething(String name,String sex, int age){
        return MessageFormat.format("a people name :{0},sex :{1},age is {2}", name, sex, age);
    }

    public People sum(People p){
        if (StringUtils.isBlank(sex)){
            this.sex = p.getSex();
        }

        if (StringUtils.isBlank(name)){
            this.name = p.name;
        }

        this.age += p.getAge();
        this.money += p.getMoney();
        this.total += p.getAge() + p.getMoney();
        return this;
    }
}
