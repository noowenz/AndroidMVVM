/*
 *  Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      https://mindorks.com/license/apache-v2
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License
 */

package com.official.android_mvvm.ui.home;

import android.arch.lifecycle.ViewModelProvider;
import android.content.res.Resources;
import android.support.v4.widget.CircularProgressDrawable;

import com.official.android_mvvm.data.local.prefs.SharedPreference;
import com.official.android_mvvm.data.remote.ApiServices;
import com.official.android_mvvm.ui.home.viewModel.HomeViewModel;
import com.official.android_mvvm.ViewModelProviderFactory;
import com.official.android_mvvm.util.rx.SchedulerProvider;

import javax.inject.Inject;

import dagger.Module;
import dagger.Provides;


@Module
public class HomeActivityModule {

    @Provides
    HomeViewModel provideHomeViewModel(ApiServices apiServices, SharedPreference prefs, Resources resources, SchedulerProvider schedulerProvider) {
        return new HomeViewModel(apiServices, prefs, resources, schedulerProvider);
    }

    @Provides
    ViewModelProvider.Factory homeViewModelProvider(HomeViewModel homeViewModel) {
        return new ViewModelProviderFactory<>(homeViewModel);
    }

}
