package com.official.android_mvvm.ui.home.model;

public class User {
    String url;
    String name;
    String email;

    public User(String name, String email, String avatar_url) {
        this.url = avatar_url;
        this.name = name;
        this.email = email;
    }

    public String getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}