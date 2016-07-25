package com.example.administrator.rxjavademo.util;

import com.orhanobut.logger.Logger;

/**
 * created by litao
 **/
public class LogUtil {
    public static void  i(String TAG,String content){
        Logger.t(TAG).i(content);
    }

    public static void  d(String TAG,String content){
        Logger.t(TAG).d(content);
    }

    public static void  e(String TAG,String content){
        Logger.t(TAG).e(content);
    }

    public static void  json(String TAG,String json){
        Logger.t(TAG).json(json);
    }
}
