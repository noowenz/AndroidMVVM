package com.official.android_mvvm.ui.home.viewModel;


import com.official.android_mvvm.base.BaseViewModel;
import com.official.android_mvvm.base.IBaseViewModel;
import com.official.android_mvvm.data.DataManager;
import com.official.android_mvvm.data.common.LiveDataResponse;
import com.official.android_mvvm.data.local.db.AppDbHelper;
import com.official.android_mvvm.helper.AppConstants;
import com.official.android_mvvm.ui.home.model.HomeModel;
import com.official.android_mvvm.ui.home.model.User;
import com.official.android_mvvm.ui.home.model.UserDetails;
import com.official.android_mvvm.ui.home.view.HomeNavigator;
import com.official.android_mvvm.util.rx.SchedulerProvider;

import java.util.concurrent.TimeUnit;

/**
 * Created by nabin on 12/9/16.
 */

public class HomeViewModel extends BaseViewModel<HomeModel, HomeNavigator> implements IBaseViewModel, IHomeViewModel, AppDbHelper.OnRealmChangedListener {

    public HomeViewModel(DataManager dataManager, SchedulerProvider schedulerProvider) {
        super(dataManager, schedulerProvider);
    }

    @Override
    public void setBaseModel() {
        if (getBaseModel() == null)
            setBaseModel(new HomeModel());
        getDataManager().addUserToDb(false);
    }

    @Override
    public void changeModel() {
        getDataManager().addUserToDb(true);
    }

    @Override
    public void getUser(int request_code) {
        if (getBaseModel().getUser() == null) {
            getCompositeDisposable().add(getDataManager().getUsersFromDb(this, request_code)
                    .delay(1000, TimeUnit.MILLISECONDS)
                    .subscribeOn(getSchedulerProvider().ui())
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
            getCompositeDisposable().add(getDataManager().getUserDetailsFromDb(this, requestCode)
                    .delay(1000, TimeUnit.MILLISECONDS)
                    .subscribeOn(getSchedulerProvider().ui())
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

    @Override
    public void OnChanged(Object changedDb, int request_code) {
        if (request_code == AppConstants.REQUEST_USER){
            getBaseModel().setUser((User) changedDb);
            getResponse().setValue(LiveDataResponse.success(getBaseModel(), request_code));
        } else {
            getBaseModel().setUserDetails((UserDetails) changedDb);
            getResponse().setValue(LiveDataResponse.success(getBaseModel(), request_code));
        }
    }
}
