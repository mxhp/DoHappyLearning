package com.happylearning.utilapplication.config;

import android.content.Context;

import com.happylearning.utilapplication.utils.SharedPreferenceKeys;
import com.vondear.rxtool.RxSPTool;

/**
 * 主要保存一些设置
 */
public class SettingConfig {

    private String laucnherUrl;

    private SettingConfig() {
    }

    private static class SettingConfigHolder {
        private static final SettingConfig config = new SettingConfig();
    }

    public static SettingConfig getInstance() {
        return SettingConfigHolder.config;
    }

    public void init(Context context) {
        if (context==null)
            throw new IllegalArgumentException("context cant not be empty!");
        laucnherUrl = RxSPTool.getString(context, SharedPreferenceKeys.LAUNCHER_URL_KEY);
    }

    public String getLauncherUrl() {
        return laucnherUrl;
    }
}
