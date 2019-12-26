package com.happylearning.utilapplication.model;

import android.text.TextUtils;

import com.happylearning.utilapplication.config.SettingConfig;
import com.happylearning.utilapplication.entity.CategoryResult;
import com.happylearning.utilapplication.network.DataManager;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Single;
import io.reactivex.functions.Function;

public class StartModel implements BaseModel {

    public Single<String> getBeautyData() {
        Single<String> single;
        if (TextUtils.isEmpty(SettingConfig.getInstance().getLauncherUrl())) {
            single = DataManager.getGankApi().getRandomBeauties(1).flatMapObservable(new Function<CategoryResult, ObservableSource<CategoryResult.ResultsBean>>() {
                @Override
                public ObservableSource<CategoryResult.ResultsBean> apply(CategoryResult categoryResult) throws Exception {
                    return Observable.fromIterable(categoryResult.results);
                }
            }).firstOrError().map(new Function<CategoryResult.ResultsBean, String>() {
                @Override
                public String apply(CategoryResult.ResultsBean resultsBean) throws Exception {
                    return resultsBean == null ? "" : resultsBean.url;
                }
            });
        } else {
            single = Single.just(SettingConfig.getInstance().getLauncherUrl());
        }

        return single;
    }
}
