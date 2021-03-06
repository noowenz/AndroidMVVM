package com.official.android_mvvm.di.builder;

import com.official.android_mvvm.ui.about.AboutFragmentProvider;
import com.official.android_mvvm.ui.home.HomeActivityModule;
import com.official.android_mvvm.ui.home.view.HomeActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = {HomeActivityModule.class, AboutFragmentProvider.class})
    abstract HomeActivity bindHomeActivity();

}