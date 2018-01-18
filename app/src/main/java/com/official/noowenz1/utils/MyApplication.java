package com.official.noowenz1.utils;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.official.noowenz1.R;
import com.official.noowenz1.helpers.UrlHelpers;
import com.official.noowenz1.injection.component.DaggerNetComponent;
import com.official.noowenz1.injection.component.NetComponent;
import com.official.noowenz1.injection.module.AppModule;
import com.official.noowenz1.injection.module.NetModule;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by nabin on 9/9/16.
 */
public class MyApplication extends MultiDexApplication {
    private NetComponent mNetComponent;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule(UrlHelpers.BASE_URL))
                .build();

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/SourceSansPro-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

    public NetComponent getNetComponent() {
        return mNetComponent;
    }
}
