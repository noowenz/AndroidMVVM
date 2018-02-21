package com.official.android_mvvm.ui.home.viewModel;

import android.content.res.Resources;

import com.official.android_mvvm.base.BaseViewModel;
import com.official.android_mvvm.base.IBaseViewModel;
import com.official.android_mvvm.data.common.LiveDataResponse;
import com.official.android_mvvm.data.local.prefs.SharedPreference;
import com.official.android_mvvm.data.remote.ApiServices;
import com.official.android_mvvm.ui.home.model.HomeModel;
import com.official.android_mvvm.ui.home.model.User;
import com.official.android_mvvm.ui.home.model.UserDetails;
import com.official.android_mvvm.ui.home.view.HomeNavigator;
import com.official.android_mvvm.util.rx.SchedulerProvider;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by nabin on 12/9/16.
 */

public class HomeViewModel extends BaseViewModel<HomeModel, HomeNavigator> implements IBaseViewModel, IHomeViewModel {

    public HomeViewModel(ApiServices apiServices, SharedPreference prefs, Resources resources, SchedulerProvider schedulerProvider) {
        super(apiServices, prefs, resources, schedulerProvider);
    }

    @Override
    public void setBaseModel() {
        if (getBaseModel() == null)
            setBaseModel(new HomeModel());
    }

    @Override
    public void getUser(int request_code) {
        if (getBaseModel().getUser() == null) {
            getCompositeDisposable().add(Observable.just(new User("Nabin Shrestha", "nabin.shrestha@ebpearls.com", "noowenz.com.np"))
                    .delay(5000, TimeUnit.MILLISECONDS)
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().ui())
                    .doOnSubscribe(__ -> getResponse().setValue(LiveDataResponse.loading(request_code)))
                    .subscribe(
                            response -> {
                                getBaseModel().setUser(response);
                                getResponse().setValue(LiveDataResponse.success(getBaseModel(), request_code));
                            },
                            throwable -> {
                                throwable.printStackTrace();
                                getResponse().setValue(LiveDataResponse.error(throwable.getMessage(), request_code));
                            }
                    )
            );
        }
    }

    @Override
    public void getUserDetails(int requestCode) {
        if (getBaseModel().getUserDetails() == null) {
            getCompositeDisposable().add(Observable.just(new UserDetails("KTM, Nepal", "9801111111"))
                    .delay(5000, TimeUnit.MILLISECONDS)
                    .subscribeOn(getSchedulerProvider().io())
                    .observeOn(getSchedulerProvider().ui())
                    .doOnSubscribe(__ -> getResponse().setValue(LiveDataResponse.loading(requestCode)))
                    .subscribe(
                            response -> {
                                getBaseModel().setUserDetails(response);
                                getResponse().setValue(LiveDataResponse.success(getBaseModel(), requestCode));
                            },
                            throwable -> getResponse().setValue(LiveDataResponse.error(throwable.getMessage(), requestCode))
                    )
            );
        }
    }

    @Override
    public String getEmail() {
        return ((User) getResponse().getValue().data).getEmail();
    }

    public void openAboutFragment(){
        getNavigator().openAboutFragment();
    }
}
