package com.official.android_mvvm.retrofit;

import com.official.android_mvvm.helpers.UrlHelpers;
import com.official.android_mvvm.home.model.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;


public interface ApiServices {
    @GET(UrlHelpers.posts)
    Call<ArrayList<User>> getNews(
    );
}
