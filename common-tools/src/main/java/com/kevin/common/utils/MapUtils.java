package com.kevin.common.utils;

import com.google.common.collect.Maps;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Created by kevin on 2018/6/11.
 */
public class MapUtils {

    public static Map<String, Object> beanToMap(Object obj) {
        Map<String, Object> reMap = Maps.newHashMap();
        if (obj == null)
            return null;
        Field[] fields = obj.getClass().getDeclaredFields();
        try {
            for (int i = 0; i < fields.length; i++) {
                try {
                    String fileName = fields[i].getName();
                    Field f = obj.getClass().getDeclaredField(fileName);
                    f.setAccessible(true);
                    Object o = f.get(obj);
                    reMap.put(fileName, o);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return reMap;
    }

}
