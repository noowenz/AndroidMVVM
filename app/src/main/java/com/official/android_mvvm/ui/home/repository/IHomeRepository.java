package com.official.android_mvvm.ui.home.repository;

import com.official.android_mvvm.ui.home.model.User;
import com.official.android_mvvm.ui.home.model.UserDetails;

import io.reactivex.Observable;

public interface IHomeRepository {
    Observable<User> getUser();
    Observable<UserDetails> getUserDetails();
}