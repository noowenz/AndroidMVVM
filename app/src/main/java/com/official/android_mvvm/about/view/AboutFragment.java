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

package com.official.android_mvvm.about.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.official.android_mvvm.R;
import com.official.android_mvvm.about.viewModel.AboutViewModel;
import com.official.android_mvvm.base.BaseFragment;

import javax.inject.Inject;

import butterknife.BindView;


public class AboutFragment extends BaseFragment<AboutViewModel> {

    public static final String TAG = AboutFragment.class.getSimpleName();

    @BindView(R.id.tv_about)
    TextView tvAbout;

    @Inject
    AboutViewModel mAboutViewModel;

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

    private void init(){
        tvAbout.setText("I am Nabin Shrestha");
    }

    @Override
    public AboutViewModel getViewModel() {
        return mAboutViewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_about;
    }

    public void goBack() {
        getBaseActivity().onFragmentDetached(TAG);
    }
}
