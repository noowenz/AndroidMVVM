package com.official.noowenz.home.injection;


import android.content.res.Resources;

import com.official.noowenz.helpers.SharedPreference;
import com.official.noowenz.home.model.HomeModelImpl;
import com.official.noowenz.home.view.IHomeView;
import com.official.noowenz.injection.ActivityScope;
import com.official.noowenz.retrofit.ApiServices;

import dagger.Module;
import dagger.Provides;

@Module
public class HomeModule {
    private final IHomeView iHomeView;

    public HomeModule(IHomeView iHomeView) {
        this.iHomeView = iHomeView;
    }

    @Provides
    @ActivityScope
    IHomeView providesIHomeView() {
        return iHomeView;
    }

    @Provides
    @ActivityScope
    HomeModelImpl providesHomeModelImpl(ApiServices apiServices, SharedPreference prefs, Resources resources) {
        return new HomeModelImpl(apiServices, prefs, resources);
    }
}
