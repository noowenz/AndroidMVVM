package com.official.android_mvvm.home.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.Handler;

import com.official.android_mvvm.home.model.User;
import com.official.android_mvvm.retrofit.ApiServices;

/**
 * Created by ebpearls on 1/19/2018.
 */

public class HomeRepositoryImpl implements IHomeRepository {
    private ApiServices apiServices;

    public HomeRepositoryImpl(ApiServices apiServices) {
        this.apiServices = apiServices;
    }

    @Override
    public LiveData<User> getUser(String userName) {
        final MutableLiveData<User> liveData = new MutableLiveData<>();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                liveData.setValue(new User("Nabin", "nks@ebpearls.com", "www.noowenz.com.np"));
            }
        }, 1000);

        return liveData;
    }
}
