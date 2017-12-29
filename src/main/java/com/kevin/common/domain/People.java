package com.kevin.common.domain;

import lombok.Data;
import lombok.ToString;

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
}
