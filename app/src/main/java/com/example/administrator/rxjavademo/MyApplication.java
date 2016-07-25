package com.example.administrator.rxjavademo;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;

public class MyApplication extends Application {
    private static MyApplication instance;
    @Override
    public void onCreate() {
        super.onCreate();
        init();
        instance = this;
//        ButterKnife.setDebug(BuildConfig.DEBUG);
    }

    private void init() {
        initLogger();
        Stetho.initializeWithDefaults(this);
    }

    private void initLogger() {
        Logger.init("AIS")                 // default PRETTYLOGGER or use just init()
                .methodCount(3)                 // default 2
                .hideThreadInfo()               // default shown
                .logLevel(LogLevel.NONE)        // default LogLevel.FULL
                .methodOffset(0);                // default 0
//                .logAdapter(new AndroidLogAdapter()); //default AndroidLogAdapter
    }

    public static MyApplication getInstance(){
        return instance;
    }
}
