package com.official.noowenz1.retrofit;

import com.official.noowenz1.home.model.GetNewsResponse;
import com.official.noowenz1.helpers.UrlHelpers;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;


public interface ApiServices {
    @GET(UrlHelpers.posts)
    Call<ArrayList<GetNewsResponse>> getNews(
    );
}
