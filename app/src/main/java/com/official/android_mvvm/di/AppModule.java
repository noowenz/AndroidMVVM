package com.official.android_mvvm.di;

import android.app.Application;
import android.content.res.Resources;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by nabin on 21-Apr-16.
 */
@Module
public class AppModule {
    @Provides
    @Singleton
    Resources provideResources(Application application) {
        return application.getResources();
    }
}
