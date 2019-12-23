package com.happylearning.utilapplication.start;

import android.annotation.SuppressLint;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.happylearning.utilapplication.R;
import com.happylearning.utilapplication.base.BaseActivity;
import com.happylearning.utilapplication.contract.StartContract;
import com.happylearning.utilapplication.presenter.StartPresenter;
import com.vondear.rxtool.view.RxToast;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class StartActivity extends BaseActivity<StartPresenter> implements StartContract.View {

    private AppCompatImageView mImgLauncherWelcome;
    private TextView mGo;

    @Override
    protected StartPresenter createPresenter() {
        return new StartPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mImgLauncherWelcome = findViewById(R.id.img_launcher_welcome);
        mGo = findViewById(R.id.go);
        mGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RxToast.info("next clicked!");
            }
        });
    }

    @SuppressLint("CheckResult")
    @Override
    protected void initData() {
        mPresenter.getBeauty();
        Observable.timer(2, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        mGo.setVisibility(View.VISIBLE);
                    }
                });

    }

    @Override
    public void showImage(String url) {
        Glide.with(this).load(url).into(mImgLauncherWelcome);
    }

}
