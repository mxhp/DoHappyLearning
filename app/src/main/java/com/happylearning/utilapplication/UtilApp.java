package com.happylearning.utilapplication;

import android.app.Application;

import com.bumptech.glide.Glide;
import com.happylearning.utilapplication.config.AppConfig;
import com.happylearning.utilapplication.config.SettingConfig;
import com.vondear.rxtool.RxTool;

public class UtilApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        RxTool.init(this);
//        AppConfig appConfig = new AppConfig.Builder().showMeizi(false).build();
        SettingConfig.getInstance().init(this);
    }
}
