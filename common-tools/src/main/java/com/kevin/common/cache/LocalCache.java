package com.kevin.common.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.kevin.common.domain.People;
import org.springframework.util.StringUtils;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author kevin
 * @date 2019-05-25 09:36
 */
public class LocalCache {


    private static Cache<String, People> LOCAL_CACHE = CacheBuilder.newBuilder().expireAfterWrite(60 * 24, TimeUnit.MINUTES).build();

    private static final String CACHE_KEY = "CACHE_KEY_";


    public static People findByName(String name) {
        if (StringUtils.isEmpty(name)) return null;
        People entity = null;
        try {
            entity = LOCAL_CACHE.get(CACHE_KEY + name,
                    () -> getPeopleByName(name));
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return entity;
    }

    private static People getPeopleByName(String name) {
        People people = new People();
        people.setSex("man");
        people.setName(name);
        people.setAge(18);
        people.setAddress("杭州西湖区支付宝大厦");
        return people;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            System.out.println(findByName("jom"));
        }

    }
}
