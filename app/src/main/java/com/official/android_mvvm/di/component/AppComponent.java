package com.official.android_mvvm.di.component;


import android.app.Application;

import com.official.android_mvvm.di.module.AppModule;
import com.official.android_mvvm.di.module.NetModule;
import com.official.android_mvvm.di.builder.ActivityBuilder;
import com.official.android_mvvm.MyApplication;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        AppModule.class,
        NetModule.class,
        ActivityBuilder.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();

    }

    void inject(MyApplication application);
}
