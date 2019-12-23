package com.happylearning.utilapplication.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.happylearning.utilapplication.contract.BaseContract;
import com.happylearning.utilapplication.presenter.BasePresenter;

import org.greenrobot.eventbus.EventBus;

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements BaseContract.View {

    /**
     * rootView是否初始化标志，防止回调函数在rootView为空的时候触发
     */
    private boolean hasCreateView;

    /**
     * 当前Fragment是否处于可见状态标志，防止因ViewPager的缓存机制而导致回调函数的触发
     */
    private boolean isFragmentVisible;

    private boolean fragmentIsFirstVisible = true;

    protected ViewGroup rootView;
    protected P mPresenter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initVariable();
    }

    protected abstract void initVariable();


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPresenter = createPresenter();
        mPresenter.attachView(this);
        if (!hasCreateView && getUserVisibleHint()) {
            onFragmentVisible();
            isFragmentVisible = true;
            if (fragmentIsFirstVisible) {
                onFragmentFirstVisible();
                fragmentIsFirstVisible = false;
            }
        }
    }

    protected abstract P createPresenter();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(getLayoutResId(), container, false);
        initView();
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        return rootView;
    }

    /**
     * 实现Fragment数据的缓加载
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (rootView == null) {
            return;
        }
        hasCreateView = true;
        if (isVisibleToUser) {
            onFragmentVisible();
            isFragmentVisible = true;
            if (fragmentIsFirstVisible) {
                onFragmentFirstVisible();
                fragmentIsFirstVisible = false;
            }
            return;
        }
        if (isFragmentVisible) {
            onFragmentGone();
            isFragmentVisible = false;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPresenter.detachView();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        mPresenter = null;
        fragmentIsFirstVisible = true;
    }

    /**
     * 如果需要用到懒加载，可以使用此方法加载数据
     */
    protected void onFragmentVisible() {
    }

    /**
     * Fragment第一次显示
     */
    protected void onFragmentFirstVisible() {
    }

    /**
     * Fragment不可见时调用此方法
     */
    protected void onFragmentGone() {
    }

    protected abstract int getLayoutResId();

    protected abstract void initView();

}
