package com.happylearning.utilapplication;

import android.app.Application;

import com.vondear.rxtool.RxTool;

public class UtilApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        RxTool.init(this);
    }
}
