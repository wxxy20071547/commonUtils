package com.kevin.common.clone;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.*;

/**
 * 实现深拷贝
 * 1.序列化实现
 * 2.通过层层继承Cloneable接口，重写clone，例如本例PeopleClone和Address都需要
 */
@Data
@AllArgsConstructor
public class PeopleClone implements Serializable{
    private String name;
    private int age;
    private Address address;

    public Object deepClone() {
        try {
            //写入对象
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ObjectOutputStream os = new ObjectOutputStream(bo);
            os.writeObject(this);
            //读取对象
            ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
            ObjectInputStream oi = new ObjectInputStream(bi);
            return (oi.readObject());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        PeopleClone p = new PeopleClone("小明",11,new Address("浙江","杭州"));
        PeopleClone p3 = (PeopleClone)p.deepClone();
        System.out.println(p);
        System.out.println(p3);
        System.out.println("--------windcoder.com----------");
        p3.setName("小明克隆人");
        p3.setAge(12);
        p3.getAddress().setProvince("浙江clone");
        System.out.println(p);
        System.out.println(p3);
        System.out.println("--------windcoder.com----------");
    }

}
