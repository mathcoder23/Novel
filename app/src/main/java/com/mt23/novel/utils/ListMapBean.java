package com.mt23.novel.utils;

import android.util.Log;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mathcoder23 on 11/7/16.
 */
public class ListMapBean {
    public static void ListToListmap(List list, Class cla) {
        List<Map<String, String>> result = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {
            Object obj = list.get(i);
            Class dataCla = obj.getClass();

        }

    }
    public static Map<String,String> BeanToMap(Object obj)
    {
        Map<String,String> map = new HashMap<>();
        Class dataCla = obj.getClass();
        Field[] fields = dataCla.getDeclaredFields();
        try {
            for (Field field : fields)
            {
                String key = field.getName();
                field.setAccessible(true);
                if (field.get(obj)!=null)
                {
                    String value = field.get(obj)+"";

                    map.put(key,value);
                }

            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return map;
    }
    public static Object MapToBean(Map<String, String> map, Class clas)
    {
        Object obj = null;
        try {
            obj = clas.newInstance();

            Field[] fields = clas.getDeclaredFields();
            for (Field field : fields)
            {
                if (field.isSynthetic())
                {
                    continue;
                }
                String name = field.getName();
                field.setAccessible(true);
                if(map.get(name)!=null)
                field.set(obj,map.get(name));
            }

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
