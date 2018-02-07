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

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.official.android_mvvm.data.common.LiveDataResponse;
import com.official.android_mvvm.ui.home.model.HomeModel;
import com.official.android_mvvm.util.CommonUtils;
import com.official.android_mvvm.util.NetworkUtils;

import butterknife.ButterKnife;
import dagger.android.AndroidInjection;

public abstract class BaseActivity<V extends BaseViewModel, M> extends AppCompatActivity implements BaseFragment.Callback {

    private ProgressDialog mProgressDialog;
    private V mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        performDependencyInjection();
        super.onCreate(savedInstanceState);
        performBindingViewModel();
        performButterKnifeBind();
    }

    private void performBindingViewModel() {
        this.mViewModel = mViewModel == null ? getViewModel() : mViewModel;
    }

    @TargetApi(Build.VERSION_CODES.M)
    public void requestPermissionsSafely(String[] permissions, int requestCode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode);
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    public boolean hasPermission(String permission) {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED;
    }

    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)
                    getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public boolean isNetworkConnected() {
        return NetworkUtils.isNetworkConnected(getApplicationContext());
    }

    public void showLoadingDialog(boolean isToInterruptUser) {
        if (isToInterruptUser) {
            hideLoadingDialog(isToInterruptUser);
            mProgressDialog = CommonUtils.showLoadingDialog(this);
        } else {
            showProgressBar();
        }

    }

    public void hideLoadingDialog(boolean isToInterruptUser) {
        if (isToInterruptUser) {
            if (mProgressDialog != null && mProgressDialog.isShowing()) {
                mProgressDialog.cancel();
            }
        } else {
            hideProgressBar();
        }
    }

    public abstract void init();

    public abstract void processResponse(LiveDataResponse liveDataResponse);

    public abstract void renderErrorState(String error);

    public abstract void renderDataState(M data, int requestCode);

    public abstract void renderLoadingState();

    public abstract void showProgressBar();

    public abstract void hideProgressBar();

    /**
     * Override for set view model
     *
     * @return view model instance
     */
    public abstract V getViewModel();

    /**
     * @return layout resource id
     */
    public abstract @LayoutRes
    int getLayoutId();

    public void performDependencyInjection() {
        AndroidInjection.inject(this);
    }

    public void performButterKnifeBind() {
        super.setContentView(getLayoutId());
        ButterKnife.bind(this);
    }

    @Override
    public void onFragmentAttached() {

    }

    @Override
    public void onFragmentDetached(String tag) {
    }
}

