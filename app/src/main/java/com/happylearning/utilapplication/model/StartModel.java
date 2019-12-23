package com.happylearning.utilapplication.model;

import com.happylearning.utilapplication.entity.CategoryResult;
import com.happylearning.utilapplication.network.DataManager;

import io.reactivex.Single;

public class StartModel implements BaseModel {

    public Single<CategoryResult> getBeautyData() {
        return DataManager.getGankApi().getRandomBeauties(1);
    }
}
