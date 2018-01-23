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
import android.content.res.Resources;

import com.official.android_mvvm.about.viewModel.AboutViewModel;
import com.official.android_mvvm.helpers.SharedPreference;

import dagger.Module;
import dagger.Provides;

@Module
public class AboutFragmentModule {

    @Provides
    AboutViewModel provideAboutViewModel(SharedPreference preference, Resources resources) {
        return new AboutViewModel(preference, resources);
    }
}
