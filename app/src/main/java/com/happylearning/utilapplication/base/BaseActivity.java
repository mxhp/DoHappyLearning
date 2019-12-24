package com.happylearning.utilapplication.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.gyf.immersionbar.ImmersionBar;
import com.happylearning.utilapplication.contract.BaseContract;
import com.happylearning.utilapplication.presenter.BasePresenter;
import com.vondear.rxtool.view.RxToast;
import com.vondear.rxui.animation.RxAbstractPathAnimator;
import com.vondear.rxui.view.dialog.RxDialogLoading;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements BaseContract.View {

    public P mPresenter;
    RxDialogLoading rxDialogLoading;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        ImmersionBar.with(this).init();
        setContentView(getLayoutId());
        mPresenter = createPresenter();
        mPresenter.attachView(this);
        initView();
        rxDialogLoading = new RxDialogLoading(this);
        rxDialogLoading.setLoadingText("Loading");

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
        mPresenter.detachView();
        mPresenter = null;
    }

    protected abstract P createPresenter();

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initData();

    @Override
    public void loading() {
        if (rxDialogLoading != null) {
            rxDialogLoading.show();
        }
    }

    @Override
    public void loaded() {
        if (rxDialogLoading != null) {
            rxDialogLoading.hide();
        }
    }

    @Override
    public void showMessage(String msg) {
        RxToast.error(msg);
    }
}
