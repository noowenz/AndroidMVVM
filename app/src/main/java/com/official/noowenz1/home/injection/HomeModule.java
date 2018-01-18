package com.official.noowenz1.home.injection;


import android.content.res.Resources;

import com.official.noowenz1.helpers.SharedPreference;
import com.official.noowenz1.home.model.HomeModelImpl;
import com.official.noowenz1.home.view.IHomeView;
import com.official.noowenz1.injection.ActivityScope;
import com.official.noowenz1.retrofit.ApiServices;

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
