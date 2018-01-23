package com.official.android_mvvm.data;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import java.util.Map;

public class SharedPreference extends Application {

    private SharedPreferences prefs;
    private Context _cntx;

    public SharedPreference(Context cntx) {
        _cntx = cntx;
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
    }

    public void setKeyValues(String key, String value) {
        prefs.edit().putString(key, value).commit();
        prefs.edit().commit();
    }

    public void setKeyValues(String key, int value) {
        prefs.edit().putInt(key, value).commit();
        prefs.edit().commit();
    }

    public void setKeyValues(String key, boolean value) {
        prefs.edit().putBoolean(key, value).commit();
        prefs.edit().commit();
    }

    public String getStringValues(String key) {
        String value = prefs.getString(key, "");
        return value;
    }

    public int getIntValues(String key) {
        int nullValue = 0;
        int value = prefs.getInt(key, nullValue);
        return value;
    }

    public boolean getBoolValues(String key) {
        boolean value = false;
        value = prefs.getBoolean(key, value);
        return value;
    }

    public Map<String, ?> getAllVal() {

        Map<String, ?> keys = prefs.getAll();
        return keys;
    }

    public void clearData() {
        prefs.edit().clear().commit();

    }
}
