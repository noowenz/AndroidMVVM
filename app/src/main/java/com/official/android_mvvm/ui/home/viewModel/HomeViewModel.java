package com.official.android_mvvm.ui.home.viewModel;

import android.content.res.Resources;

import com.official.android_mvvm.base.BaseViewModel;
import com.official.android_mvvm.data.common.Response;
import com.official.android_mvvm.data.SharedPreference;
import com.official.android_mvvm.ui.home.repository.HomeRepositoryImpl;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Single;

/**
 * Created by nabin on 12/9/16.
 */

public class HomeViewModel extends BaseViewModel<HomeRepositoryImpl> {

    @Inject
    public HomeViewModel(HomeRepositoryImpl homeRepository, SharedPreference prefs, Resources resources) {
        super(homeRepository, prefs, resources);
    }

    public void getUser() {
        getCompositeDisposable().add(Single.just("Hello from HomeRepository")
                .delay(5000, TimeUnit.MILLISECONDS)
                .subscribeOn(getSchedulersFacade().io())
                .observeOn(getSchedulersFacade().ui())
                .doOnSubscribe(__ -> getResponse().setValue(Response.loading()))
                .subscribe(
                        greeting -> getResponse().setValue(Response.success(greeting)),
                        throwable -> getResponse().setValue(Response.error(throwable))
                )
        );
    }

    public String getEmail(){
        return getResponse().getValue().data;
    }

}
