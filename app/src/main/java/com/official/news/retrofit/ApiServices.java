package com.official.news.retrofit;

import com.official.news.activities.GetNewsResponse;
import com.official.news.helpers.UrlHelpers;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface ApiServices {
    @GET(UrlHelpers.posts)
    Call<ArrayList<GetNewsResponse>> getNews(
    );
}
