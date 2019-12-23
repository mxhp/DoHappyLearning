package com.happylearning.utilapplication.network.api;


import com.happylearning.utilapplication.entity.CategoryResult;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UgankApi {

//    @GET("data/{category}/{number}/{page}")
//    Call<Category> getCategoryDate(@Path("category") String category, @Path("number") int number, @Path("page") int page);

    @GET("random/data/福利/{number}")
    Single<CategoryResult> getRandomBeauties(@Path("number") int number);

//    @GET("search/query/{key}/category/all/count/{count}/page/{page}")
//    Call<SearchResult> getSearchResult(@Path("key") String key, @Path("count") int count, @Path("page") int page);

}
