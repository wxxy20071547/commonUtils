package com.kevin.common.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by kevin on 2017/11/29.
 */
public class Student extends People{
    private String className;
    private int grade;

    @Data
    public static class StudentResult implements Serializable {
    }


}
