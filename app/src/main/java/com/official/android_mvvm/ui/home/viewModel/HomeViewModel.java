package com.official.android_mvvm.ui.home.viewModel;

import android.content.res.Resources;

import com.official.android_mvvm.base.BaseViewModel;
import com.official.android_mvvm.data.common.LiveDataResponse;
import com.official.android_mvvm.data.SharedPreference;
import com.official.android_mvvm.ui.home.model.User;
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
        getCompositeDisposable().add(getRepository().getUser()
                .delay(5000, TimeUnit.MILLISECONDS)
                .subscribeOn(getSchedulers().io())
                .observeOn(getSchedulers().ui())
                .doOnSubscribe(__ -> getResponse().setValue(LiveDataResponse.loading()))
                .subscribe(
                        response -> getResponse().setValue(LiveDataResponse.success(response)),
                        throwable -> getResponse().setValue(LiveDataResponse.error(throwable))
                )
        );
    }

    public String getEmail(){
        return ((User)getResponse().getValue().data).getEmail();
    }

}
