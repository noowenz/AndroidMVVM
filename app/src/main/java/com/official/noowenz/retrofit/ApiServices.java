package com.official.noowenz.retrofit;

import com.official.noowenz.home.model.GetNewsResponse;
import com.official.noowenz.helpers.UrlHelpers;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;


public interface ApiServices {
    @GET(UrlHelpers.posts)
    Call<ArrayList<GetNewsResponse>> getNews(
    );
}
