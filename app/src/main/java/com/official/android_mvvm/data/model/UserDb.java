package com.official.android_mvvm.data.model;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class UserDb extends RealmObject {
    @PrimaryKey
    private String id;

    private String url;
    private String name;
    private String email;

    private UserDetailsDb userDetails;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserDetailsDb getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetailsDb userDetails) {
        this.userDetails = userDetails;
    }
}