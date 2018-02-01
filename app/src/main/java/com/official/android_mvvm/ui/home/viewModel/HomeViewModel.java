package com.official.android_mvvm.ui.home.viewModel;

import android.content.res.Resources;

import com.official.android_mvvm.base.BaseViewModel;
import com.official.android_mvvm.base.IBaseViewModel;
import com.official.android_mvvm.data.common.LiveDataResponse;
import com.official.android_mvvm.data.SharedPreference;
import com.official.android_mvvm.ui.home.model.HomeModel;
import com.official.android_mvvm.ui.home.model.User;
import com.official.android_mvvm.ui.home.repository.HomeRepositoryImpl;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

/**
 * Created by nabin on 12/9/16.
 */

public class HomeViewModel extends BaseViewModel<HomeRepositoryImpl, HomeModel> implements IBaseViewModel, IHomeViewModel {

    @Inject
    public HomeViewModel(HomeRepositoryImpl homeRepository, SharedPreference prefs, Resources resources) {
        super(homeRepository, prefs, resources);
    }

    @Override
    public void setBaseModel() {
        setBaseModel(new HomeModel());
    }

    @Override
    public void getUser(int request_code) {
        getCompositeDisposable().add(getRepository().getUser()
                .delay(5000, TimeUnit.MILLISECONDS)
                .subscribeOn(getSchedulers().io())
                .observeOn(getSchedulers().ui())
                .doOnSubscribe(__ -> getResponse().setValue(LiveDataResponse.loading(request_code)))
                .subscribe(
                        response -> {
                            getBaseModel().setUser(response);
                            getResponse().setValue(LiveDataResponse.success(getBaseModel(), request_code));
                        },
                        throwable -> {
                            throwable.printStackTrace();
                            getResponse().setValue(LiveDataResponse.error(throwable, request_code));
                        }
                )
        );
    }

    @Override
    public void getUserDetails(int requestCode) {
        getCompositeDisposable().add(getRepository().getUserDetails()
                .delay(5000, TimeUnit.MILLISECONDS)
                .subscribeOn(getSchedulers().io())
                .observeOn(getSchedulers().ui())
                .doOnSubscribe(__ -> getResponse().setValue(LiveDataResponse.loading(requestCode)))
                .subscribe(
                        response -> {
                            getBaseModel().setUserDetails(response);
                            getResponse().setValue(LiveDataResponse.success(getBaseModel(), requestCode));
                        },
                        throwable -> getResponse().setValue(LiveDataResponse.error(throwable, requestCode))
                )
        );
    }

    @Override
    public String getEmail() {
        return ((User) getResponse().getValue().data).getEmail();
    }
}
