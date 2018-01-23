package com.official.android_mvvm.ui.home.repository;

import com.official.android_mvvm.ui.home.model.User;

import io.reactivex.Observable;

public interface IHomeRepository {
    Observable<User> getUser();
}