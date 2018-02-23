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

package com.official.android_mvvm.data.local.db;


import com.official.android_mvvm.ui.home.model.User;
import com.official.android_mvvm.ui.home.model.UserDetails;
import io.reactivex.Observable;

public interface DbHelper {
    Observable<User> getUsersFromDb(AppDbHelper.OnRealmChangedListener onRealmChangedListener, int request_code);
    Observable<UserDetails> getUserDetailsFromDb(AppDbHelper.OnRealmChangedListener onRealmChangedListener, int request_code);
    Observable<Boolean> addUserToDb(boolean isChanged);
    void closeRealm();
    interface OnRealmChangedListener<T> {
        void OnChanged(T changedDb, int request_code);
    }
}
