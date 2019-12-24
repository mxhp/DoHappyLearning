package com.happylearning.utilapplication.presenter;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LifecycleOwner;
import android.util.Log;

import com.happylearning.utilapplication.contract.StartContract;
import com.happylearning.utilapplication.entity.CategoryResult;
import com.happylearning.utilapplication.model.StartModel;
import com.happylearning.utilapplication.start.StartActivity;
import com.happylearning.utilapplication.utils.RxJavaTools;
import com.vondear.rxtool.RxSPTool;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class StartPresenter extends BasePresenter<StartContract.View, StartModel> implements StartContract.Presenter<StartContract.View> {

    public StartPresenter(StartActivity startActivity) {
        super(startActivity);
    }

    @SuppressLint("CheckResult")
    @Override
    public void getBeauty() {
        mViewRef.get().loading();
        getModel()
                .get()
                .getBeautyData()
                .timeout(5, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .flatMapObservable(new Function<CategoryResult, ObservableSource<CategoryResult.ResultsBean>>() {
                    @Override
                    public ObservableSource<CategoryResult.ResultsBean> apply(CategoryResult categoryResult) throws Exception {
                        return Observable.fromIterable(categoryResult.results);
                    }
                })
                .firstOrError()
                .map(new Function<CategoryResult.ResultsBean, String>() {
                    @Override
                    public String apply(CategoryResult.ResultsBean resultsBean) throws Exception {
                        return resultsBean == null ? "" : resultsBean.url;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .as(RxJavaTools.<String>bindToLifecycle((LifecycleOwner) mActivity.get()))
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) {
                        mViewRef.get().loaded();
                        mViewRef.get().showImage(s);
                        RxSPTool.putString(mActivity.get(),"url",s);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) {
                        Log.i("ttt", throwable.toString());
                        mViewRef.get().loaded();
                        mViewRef.get().showMessage("request failed!");
                    }
                });
    }

    @Override
    protected StartModel createModel() {
        return new StartModel();
    }
}
