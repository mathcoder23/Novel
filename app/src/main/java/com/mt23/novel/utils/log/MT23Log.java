package com.mt23.novel.utils.log;

import android.util.Log;

/**
 * Created by mathcoder23 on 1/20/17.
 */
public class MT23Log {
    public static void i(String msg)
    {
        i("mt23log",msg);
    }

    public static void i(String tag,String msg)
    {
        Log.i(tag,msg);
    }
    public static void e(String msg)
    {
        e("mt23log",msg);
    }

    public static void e(String tag,String msg)
    {
        Log.e(tag,msg);
    }

    public static void d(String msg)
    {
        d("mt23log",msg);
    }

    public static void d(String tag,String msg)
    {
        Log.d(tag,msg);
    }
}
