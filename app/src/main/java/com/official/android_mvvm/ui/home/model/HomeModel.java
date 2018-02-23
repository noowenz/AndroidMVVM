package com.official.android_mvvm.ui.home.model;


/**
 * Created by ebpearls on 2/1/2018.
 */

public class HomeModel {
    public User user;
    public UserDetails userDetails;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }
}
