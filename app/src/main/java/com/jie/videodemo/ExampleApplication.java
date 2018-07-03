package com.jie.videodemo;

import android.app.Application;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by jie on 2018/6/22.
 */
public class ExampleApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }
}
