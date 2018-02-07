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

package com.official.android_mvvm.base;

import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.jakewharton.rxbinding2.internal.GenericTypeNullable;
import com.official.android_mvvm.data.common.LiveDataResponse;
import com.official.android_mvvm.data.SharedPreference;
import com.official.android_mvvm.rx.SchedulersFacade;
import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseViewModel<R, M, N> extends ViewModel implements LifecycleObserver {

    private R repository;
    private M baseModel;
    private N mNavigator;
    private SharedPreference prefs;
    private Resources resources;
    private CompositeDisposable mCompositeDisposable;
    private final MutableLiveData<LiveDataResponse> response;
    private final SchedulersFacade schedulers;

    public BaseViewModel(R repository, SharedPreference prefs, Resources resources) {
        this.repository = repository;
        this.prefs = prefs;
        this.resources = resources;
        this.mCompositeDisposable = new CompositeDisposable();
        this.response= new MutableLiveData<>();
        this.schedulers = new SchedulersFacade();
    }

    public void setNavigator(N navigator) {
        this.mNavigator = navigator;
    }

    public N getNavigator() {
        return mNavigator;
    }

    public R getRepository() {
        return repository;
    }

    public Resources getResources() {
        return resources;
    }

    public void setBaseModel(M baseModel) {
        this.baseModel = baseModel;
    }

    public M getBaseModel() {
        return baseModel;
    }

    public SharedPreference getSharedPreference() {
        return prefs;
    }

    public CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }

    public MutableLiveData<LiveDataResponse> getResponse() {

        return response;
    }

    public SchedulersFacade getSchedulers() {

        return schedulers;
    }


    @Override
    protected void onCleared() {
        mCompositeDisposable.dispose();
        super.onCleared();
    }
}
