package com.official.android_mvvm.home.viewModel;

import android.arch.lifecycle.LiveData;
import android.content.res.Resources;

import com.official.android_mvvm.base.BaseViewModel;
import com.official.android_mvvm.helpers.SharedPreference;
import com.official.android_mvvm.home.model.User;
import com.official.android_mvvm.home.repository.HomeRepositoryImpl;

import javax.inject.Inject;

/**
 * Created by nabin on 12/9/16.
 */

public class HomeViewModel extends BaseViewModel<HomeRepositoryImpl> {
    private LiveData<User> user;

    @Inject
    public HomeViewModel(HomeRepositoryImpl homeRepository, SharedPreference prefs, Resources resources) {
        super(homeRepository, prefs, resources);
    }

    public LiveData<User> getUser(String userName) {
        user = getRepository().getUser(userName);
        return user;
    }
}
