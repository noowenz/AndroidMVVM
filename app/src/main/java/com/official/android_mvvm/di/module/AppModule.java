package com.official.android_mvvm.di.module;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import com.official.android_mvvm.BuildConfig;
import com.official.android_mvvm.data.AppDataManager;
import com.official.android_mvvm.data.DataManager;
import com.official.android_mvvm.data.local.db.AppDbHelper;
import com.official.android_mvvm.data.local.db.DbHelper;
import com.official.android_mvvm.data.local.prefs.AppPreferencesHelper;
import com.official.android_mvvm.data.local.prefs.PreferencesHelper;
import com.official.android_mvvm.di.DatabaseInfo;
import com.official.android_mvvm.di.PreferenceInfo;
import com.official.android_mvvm.helper.AppConstants;
import com.official.android_mvvm.util.rx.AppSchedulerProvider;
import com.official.android_mvvm.util.rx.SchedulerProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by nabin on 21-Apr-16.
 */
@Module
public class AppModule {

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    Resources provideResources(Application application) {
        return application.getResources();
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return AppConstants.DB_NAME;
    }

    @Provides
    @PreferenceInfo
    String providePreferenceName() {
        return AppConstants.PREF_NAME;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @Singleton
    Realm provideRealm() {
        return Realm.getDefaultInstance();
    }

    @Provides
    @Singleton
    DbHelper provideDbHelper(AppDbHelper appDbHelper) {
        return appDbHelper;
    }

    @Provides
    @Singleton
    PreferencesHelper providePreferencesHelper(AppPreferencesHelper appPreferencesHelper) {
        return appPreferencesHelper;
    }

    @Provides
    @Singleton
    RealmConfiguration realmConfiguration(Application application, @DatabaseInfo String databaseName) {
        Realm.init(application);
        RealmConfiguration.Builder builder = new RealmConfiguration.Builder();
        builder.name(databaseName);
        if (BuildConfig.DEBUG) {
            builder = builder.deleteRealmIfMigrationNeeded();
        }
        return builder.build();
    }
}
