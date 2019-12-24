package com.happylearning.utilapplication.utils;

import android.arch.lifecycle.LifecycleOwner;

import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.AutoDisposeConverter;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

public class RxJavaTools {
    public static <T> AutoDisposeConverter<T> bindToLifecycle( LifecycleOwner lifecycleOwner){
       return AutoDispose.<T>autoDisposable(AndroidLifecycleScopeProvider.from(lifecycleOwner));
    }
}
