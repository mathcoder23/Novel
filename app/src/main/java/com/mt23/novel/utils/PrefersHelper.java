package com.mt23.novel.utils;

import android.content.Context;
import android.content.SharedPreferences;
import com.alibaba.fastjson.JSON;
import com.mt23.novel.MyApplication;

import java.util.Map;

public class PrefersHelper {

    public static String getStrValue(String name, String key) {
    	SharedPreferences cache = MyApplication.context.getSharedPreferences(name, Context.MODE_PRIVATE);
        return cache.getString(key, null);
    }

    public static void setValue(String name, String key, String value) {
    	SharedPreferences cache = MyApplication.context.getSharedPreferences(name, Context.MODE_PRIVATE);
    	cache.edit().putString(key, value).commit();
    }

    public static void removeSpf(String name, String key) {
    	SharedPreferences cache = MyApplication.context.getSharedPreferences(name, Context.MODE_PRIVATE);
    	cache.edit().remove(key).commit();
    }
    public static void clearSpf(String name)
    {
    	SharedPreferences cache = MyApplication.context.getSharedPreferences(name, Context.MODE_PRIVATE);
    	cache.edit().clear().commit();
    }

    public static int getIntValue(String name, String key, int defaultInt) {
    	SharedPreferences cache = MyApplication.context.getSharedPreferences(name, Context.MODE_PRIVATE);
        return cache.getInt(key, defaultInt);
    }

    public static void setValue(String name, String key, int value) {
    	SharedPreferences cache = MyApplication.context.getSharedPreferences(name, Context.MODE_PRIVATE);
    	cache.edit().putInt(key, value).commit();
    }

    public static Boolean getBooleanValue(String name, String key, Boolean defaultBoolean) {
    	SharedPreferences cache = MyApplication.context.getSharedPreferences(name, Context.MODE_PRIVATE);
        return cache.getBoolean(key, defaultBoolean);
    }

    public static void setValue(String name, String key, Boolean value) {
    	SharedPreferences cache = MyApplication.context.getSharedPreferences(name, Context.MODE_PRIVATE);
    	cache.edit().putBoolean(key, value).commit();
    }

    public static void setValue(String name, String key, long value) {
    	SharedPreferences cache = MyApplication.context.getSharedPreferences(name, Context.MODE_PRIVATE);
    	cache.edit().putLong(key, value).commit();
    }

    public static long getLongValue(String name, String key, long defaultLong) {
    	SharedPreferences cache = MyApplication.context.getSharedPreferences(name, Context.MODE_PRIVATE);
        return cache.getLong(key, defaultLong);
    }
    public static Map<String, ?> getAll(String name) {
    	SharedPreferences cache = MyApplication.context.getSharedPreferences(name, Context.MODE_PRIVATE);
    	return cache.getAll();
    }

}
