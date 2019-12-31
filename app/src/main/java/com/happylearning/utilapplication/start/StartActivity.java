package com.happylearning.utilapplication.start;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.widget.AppCompatImageView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.happylearning.utilapplication.R;
import com.happylearning.utilapplication.base.BaseActivity;
import com.happylearning.utilapplication.contract.StartContract;
import com.happylearning.utilapplication.presenter.StartPresenter;
import com.happylearning.utilapplication.ui.SportView;
import com.vondear.rxtool.RxLogTool;
import com.vondear.rxtool.view.RxToast;

public class StartActivity extends BaseActivity<StartPresenter> implements StartContract.View {

    private AppCompatImageView mImgLauncherWelcome;
    private TextView mGo;
    private SportView mSportView;

    @Override
    protected StartPresenter createPresenter() {
        return new StartPresenter(this);
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
        mSportView = findViewById(R.id.sportView);
        mSportView.setVisibility(View.VISIBLE);
    }

    @SuppressLint("CheckResult")
    @Override
    protected void initData() {
        RxLogTool.i("showImage", "request star time =" + System.currentTimeMillis());
        mPresenter.getBeauty();
//        Observable.timer(2, TimeUnit.SECONDS)
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<Long>() {
//                    @Override
//                    public void accept(Long aLong) throws Exception {
//                        mGo.setVisibility(View.VISIBLE);
//                        mGo.animate().translationXBy(-800).start();
//                    }
//                });
//        Observable.interval(0, 100, TimeUnit.MILLISECONDS).take(100)
//                .subscribeOn(Schedulers.computation())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<Long>() {
//                    @Override
//                    public void accept(Long aLong) throws Exception {
//                        RxLogTool.i("showImage", "value =" + aLong);
//                        mSportView.setProgress(aLong);
//                    }
//                });
        ObjectAnimator objectAnimator =ObjectAnimator.ofFloat(mSportView, "progress", 0,100);
        objectAnimator.setDuration(2000);
        objectAnimator.setInterpolator(new FastOutSlowInInterpolator());
        objectAnimator.start();
    }

    @Override
    public void showImage(String url) {
        RxLogTool.i("showImage", "star time =" + System.currentTimeMillis());
        Glide.with(this).load(url).into(mImgLauncherWelcome);
        RxLogTool.i("showImage", "end time =" + System.currentTimeMillis());
    }

}
