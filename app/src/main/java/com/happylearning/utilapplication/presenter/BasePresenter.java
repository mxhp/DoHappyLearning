package com.happylearning.utilapplication.presenter;


import com.happylearning.utilapplication.contract.BaseContract;
import com.happylearning.utilapplication.model.BaseModel;

import java.lang.ref.WeakReference;

public abstract class BasePresenter<V extends BaseContract.View, M extends BaseModel> implements BaseContract.Presenter<V> {

    protected WeakReference<V> mViewRef;
    private WeakReference<M> mModelRef;

    public BasePresenter() {
        mModelRef = new WeakReference<>(createModel());
    }

    protected abstract M createModel();

    @Override
    public void attachView(V view) {
        mViewRef = new WeakReference<>(view);
    }

    public synchronized WeakReference<M> getModel() {
        if (null == mModelRef || null == mModelRef.get()) {
            mModelRef = new WeakReference<>(createModel());
        }
        return mModelRef;
    }

    @Override
    public void detachView() {
        if (null != mViewRef && null != mViewRef.get()) {
            mViewRef.clear();
        }
        mViewRef = null;
        if (null != mModelRef && null != mModelRef.get()) {
            mModelRef.clear();
        }
        mModelRef = null;
    }
}
