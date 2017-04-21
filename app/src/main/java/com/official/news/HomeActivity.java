package com.official.news;

import android.os.Bundle;
import com.official.news.menu.BaseDrawer;

public class HomeActivity extends BaseDrawer {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected String actionBarTitle() {
        return "Home";
    }

    @Override
    protected String currentActivity() {
        return "Home";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
