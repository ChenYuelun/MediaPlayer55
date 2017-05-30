package com.example.mediaplayer55.app;

import android.app.Application;

import org.xutils.x;

/**
 * Created by chenyuelun on 2017/5/30.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
        x.Ext.setDebug(true);
    }
}
