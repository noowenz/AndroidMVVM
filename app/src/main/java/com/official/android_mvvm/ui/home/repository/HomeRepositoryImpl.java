package com.official.android_mvvm.ui.home.repository;

import com.official.android_mvvm.ui.home.model.User;
import com.official.android_mvvm.data.ApiServices;
import com.official.android_mvvm.ui.home.model.UserDetails;

import io.reactivex.Observable;
import io.reactivex.Observer;


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
//        return apiServices.getUsers();
        return Observable.just(new User("Nabin Shrestha", "nabin.shrestha@ebpearls.com", "noowenz.com.np"));
    }

    @Override
    public Observable<UserDetails> getUserDetails() {
//        return apiServices.getUsers();
        return Observable.just(new UserDetails("KTM, Nepal", "9801111111"));
    }
}
