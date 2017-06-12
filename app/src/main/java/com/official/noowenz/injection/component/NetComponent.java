package com.official.noowenz.injection.component;


import android.content.res.Resources;

import com.official.noowenz.helpers.SharedPreference;
import com.official.noowenz.injection.module.AppModule;
import com.official.noowenz.injection.module.NetModule;
import com.official.noowenz.retrofit.ApiServices;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {
    ApiServices apiInterface();
    SharedPreference sharedPreference();
    Resources resources();
    Retrofit retrofit();
}
