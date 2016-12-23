package com.yf.myapplicationdemo.xutils;

import android.app.Application;

import org.xutils.x;

/**
 * Created by yangfei on 2016/12/22.
 */

public class MyApp  extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
    }
}
