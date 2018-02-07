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

package com.official.android_mvvm.ui.about.view;


import android.arch.lifecycle.ViewModelProvider;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.official.android_mvvm.R;
import com.official.android_mvvm.base.BaseFragment;
import com.official.android_mvvm.data.common.LiveDataResponse;
import com.official.android_mvvm.ui.about.viewModel.AboutViewModel;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class AboutFragment extends BaseFragment<AboutViewModel> implements AboutNavigator {

    public static final String TAG = AboutFragment.class.getSimpleName();

    @BindView(R.id.tv_about)
    TextView tvAbout;
    @BindView(R.id.pb_loading)
    ProgressBar pbLoading;
    @BindView(R.id.iv_error)
    ImageView ivError;
    @BindView(R.id.tv_refresh)
    TextView tvRefresh;
    @BindView(R.id.ll_progress)
    LinearLayout llProgress;
    Unbinder unbinder;

    @Inject
    AboutViewModel mAboutViewModel;

    @Inject
    ViewModelProvider.Factory mViewModelFactory;

    public static AboutFragment newInstance() {
        Bundle args = new Bundle();
        AboutFragment fragment = new AboutFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    @Override
    public void init() {
        if (isNetworkConnected()) {
            tvAbout.setVisibility(View.VISIBLE);
            llProgress.setVisibility(View.GONE);

//            showLoadingDialog(false);
            tvAbout.setText("I am Nabin Shrestha");
        } else {
            tvAbout.setVisibility(View.GONE);
            llProgress.setVisibility(View.VISIBLE);

            Toast.makeText(getActivity(), "No internet connection available.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showProgressBar() {
        pbLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        pbLoading.setVisibility(View.GONE);
    }

    @Override
    public AboutViewModel getViewModel() {
        return mAboutViewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_about;
    }

    @Override
    public void goBack() {
        getBaseActivity().onFragmentDetached(TAG);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
