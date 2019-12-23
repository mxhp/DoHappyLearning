package com.happylearning.utilapplication.contract;

public interface StartContract {

    interface View extends BaseContract.View {
        void showImage(String url);
    }

    interface Presenter<V> extends BaseContract.Presenter<V>{
        void getBeauty();
    }
}
