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

import com.official.android_mvvm.data.model.UserDb;
import com.official.android_mvvm.data.model.UserDetailsDb;
import com.official.android_mvvm.ui.home.model.User;
import com.official.android_mvvm.ui.home.model.UserDetails;

import java.util.UUID;

import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;
import io.realm.OrderedCollectionChangeSet;
import io.realm.OrderedRealmCollectionChangeListener;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

@Singleton
public class AppDbHelper implements DbHelper {

    private final Realm mRealm;

    @Inject
    public AppDbHelper(Realm realm) {
        this.mRealm = realm;
    }

    @Override
    public Observable<User> getUsersFromDb(OnRealmChangedListener onRealmChangedListener, int request_code) {
        final User[] user = {null};
        RealmResults<UserDb> realmResults = mRealm.where(UserDb.class).findAll();
        realmResults.addChangeListener((userDbs, changeSet) -> {
            user[0] = new User(userDbs.get(0).getName(), userDbs.get(0).getEmail(), userDbs.get(0).getUrl());
            onRealmChangedListener.OnChanged(user[0], request_code);
        });
        user[0] = new User(realmResults.get(0).getName(), realmResults.get(0).getEmail(), realmResults.get(0).getUrl());
        return Observable.just(user[0]);
    }

    @Override
    public Observable<UserDetails> getUserDetailsFromDb(OnRealmChangedListener onRealmChangedListener, int request_code) {
        final UserDetails[] userDetails = {null};
        RealmResults<UserDetailsDb> realmResults = mRealm.where(UserDetailsDb.class).findAll();
        realmResults.addChangeListener((userDetailsDbs, changeSet) -> {
            userDetails[0] = new UserDetails(userDetailsDbs.get(0).getAddress(), userDetailsDbs.get(0).getPhone());
            onRealmChangedListener.OnChanged(userDetails[0], request_code);
        });
        userDetails[0] = new UserDetails(realmResults.get(0).getAddress(), realmResults.get(0).getPhone());
        return Observable.just(userDetails[0]);
    }

    @Override
    public Observable<Boolean> addUserToDb(boolean isChanged) {
        mRealm.executeTransaction(realm -> {
            try {
                UserDb userDb = realm.createObject(UserDb.class, UUID.randomUUID().toString());

                if (isChanged) {
                    userDb.setName("Nabin Shrestha");
                } else {
                    userDb.setName("Nabiafsadasdfdssdfadfasdn Shrestha");
                }

                userDb.setEmail("nabin.shrestha@ebpearls.com");
                userDb.setUrl("noowenz.com.np");

                UserDetailsDb userDetailsDb = realm.createObject(UserDetailsDb.class);
                userDetailsDb.setAddress("KTM, Nepal");
                userDetailsDb.setPhone("21313131");

                userDb.setUserDetails(userDetailsDb);

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return null;
    }

    @Override
    public void closeRealm() {
        mRealm.close();
    }

}
