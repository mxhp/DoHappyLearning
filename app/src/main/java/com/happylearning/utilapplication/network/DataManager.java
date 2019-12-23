package com.happylearning.utilapplication.network;

import com.happylearning.utilapplication.network.api.UgankApi;

import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataManager {
    private static OkHttpClient okHttpClient = new OkHttpClient();
    private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
    private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJava2CallAdapterFactory.create();
    private static UgankApi gankApi;
//    private static KaiYanApi kaiYanApi;

    public static UgankApi getGankApi() {
        if (gankApi == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .baseUrl("http://gank.io/api/")
                    .client(okHttpClient)
                    .build();
            gankApi = retrofit.create(UgankApi.class);
        }
        return gankApi;
    }

//    public static KaiYanApi getKaiYanApi(){
//        if (kaiYanApi == null) {
//            Retrofit retrofit=new Retrofit.Builder()
//                    .baseUrl("http://baobab.kaiyanapp.com/api/")
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .client(new OkHttpClient())
//                    .build();
//            kaiYanApi =retrofit.create(KaiYanApi.class);
//        }
//        return kaiYanApi;
//    }
}
