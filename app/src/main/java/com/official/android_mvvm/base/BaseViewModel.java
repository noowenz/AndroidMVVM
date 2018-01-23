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

import android.arch.lifecycle.ViewModel;
import android.content.res.Resources;

import com.official.android_mvvm.helpers.SharedPreference;
import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseViewModel<T> extends ViewModel {

    private SharedPreference prefs;
    private Resources resources;
    private CompositeDisposable mCompositeDisposable;
    private T repository;

    public BaseViewModel(T repository, SharedPreference prefs, Resources resources) {
        this.repository = repository;
        this.prefs = prefs;
        this.resources = resources;
        this.mCompositeDisposable = new CompositeDisposable();
    }

    public T getRepository() {
        return repository;
    }

    public Resources getResources() {
        return resources;
    }

    public SharedPreference getSharedPreference() {
        return prefs;
    }

    public CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }

    @Override
    protected void onCleared() {
        mCompositeDisposable.dispose();
        super.onCleared();
    }
}
