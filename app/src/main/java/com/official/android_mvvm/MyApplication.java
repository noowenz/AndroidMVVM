package com.official.android_mvvm;

import android.app.Activity;
import android.app.Application;
import com.official.android_mvvm.di.component.DaggerAppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by nabin on 9/9/16.
 */
public class MyApplication extends Application implements HasActivityInjector {

    @Inject
    RealmConfiguration realmConfiguration;

    @Inject
    DispatchingAndroidInjector<Activity> activityDispatchingAndroidInjector;

    @Override
    public void onCreate() {
        super.onCreate();

        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this);

        Realm.setDefaultConfiguration(realmConfiguration);
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return activityDispatchingAndroidInjector;
    }
}
