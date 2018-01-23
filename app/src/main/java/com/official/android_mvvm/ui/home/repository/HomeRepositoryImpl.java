package com.official.android_mvvm.ui.home.repository;

import com.official.android_mvvm.ui.home.model.User;
import com.official.android_mvvm.data.ApiServices;

import io.reactivex.Observable;


/**
 * Created by ebpearls on 1/19/2018.
 */

public class HomeRepositoryImpl implements IHomeRepository {
    private ApiServices apiServices;

    public HomeRepositoryImpl(ApiServices apiServices) {
        this.apiServices = apiServices;
    }

    @Override
    public Observable<User> getUser() {
       return apiServices.getUsers();
    }
}
