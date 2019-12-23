package com.happylearning.utilapplication.contract;

public interface BaseContract {

    interface Model {

    }

    interface View {

        void loading();

        void loaded();

        void showMessage(String msg);

    }

    interface Presenter<V> {

        void attachView(V view);

        void detachView();
    }
}
