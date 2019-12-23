package com.happylearning.utilapplication.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.happylearning.utilapplication.contract.BaseContract;
import com.happylearning.utilapplication.presenter.BasePresenter;
import com.vondear.rxtool.view.RxToast;
import com.vondear.rxui.view.dialog.shapeloadingview.RxShapeLoadingView;

import org.greenrobot.eventbus.EventBus;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements BaseContract.View {

    public P mPresenter;
    RxShapeLoadingView rxBaseRoundProgressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        mPresenter = createPresenter();
        mPresenter.attachView(this);
        initView();
        rxBaseRoundProgressBar = new RxShapeLoadingView(getBaseContext());
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        initRequest();
    }

    protected void initRequest() {

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        mPresenter.detachView();
        mPresenter = null;
    }

    protected abstract P createPresenter();

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initData();

    @Override
    public void loading() {
        if (rxBaseRoundProgressBar != null) {
            rxBaseRoundProgressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void loaded() {
        if (rxBaseRoundProgressBar != null) {
            rxBaseRoundProgressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void showMessage(String msg) {
        RxToast.error(msg);
    }
}
