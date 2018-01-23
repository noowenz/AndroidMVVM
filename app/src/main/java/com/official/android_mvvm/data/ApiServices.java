package com.official.android_mvvm.data;

import com.official.android_mvvm.ui.home.model.User;

import io.reactivex.Observable;
import retrofit2.http.GET;


public interface ApiServices {
    @GET("users")
    Observable<User> getUsers(
    );
}
