package com.official.news.utils;

import android.app.Application;
import android.content.Context;

import com.official.news.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by nabin on 9/9/16.
 */
public class MyApplication extends Application {

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/SourceSansPro-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
}
