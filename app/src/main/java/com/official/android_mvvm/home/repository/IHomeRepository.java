package com.official.android_mvvm.home.repository;

import android.arch.lifecycle.LiveData;

import com.official.android_mvvm.home.model.User;

public interface IHomeRepository {
    LiveData<User> getUser(String userName);
}