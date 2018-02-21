package com.official.android_mvvm.data.remote;

import com.official.android_mvvm.ui.home.model.User;
import com.official.android_mvvm.ui.home.model.UserDetails;

import io.reactivex.Observable;
import retrofit2.http.GET;


public interface ApiServices {
    @GET("users")
    Observable<User> getUsers();

    @GET("users_details")
    Observable<UserDetails> getUsersDetails();
}
