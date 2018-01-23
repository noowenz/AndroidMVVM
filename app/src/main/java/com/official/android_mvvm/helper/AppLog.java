package com.official.android_mvvm.helper;

import android.util.Log;

import com.official.android_mvvm.BuildConfig;


public class AppLog {

    private static final String defaultLog = "APP_LOG";

    public static void d(String tag, String message) {
        if (BuildConfig.DEBUG && message != null) {
            Log.d(tag, message);
        }
    }

    public static void d(String message) {
        if (BuildConfig.DEBUG && message != null) {
            Log.d(defaultLog, message);
        }
    }

    public static void e(String tag, String message) {
        if (BuildConfig.DEBUG && message != null) {
            Log.e(tag, message);
        }
    }

    public static void e(String message) {
        if (BuildConfig.DEBUG && message != null) {
            Log.e(defaultLog, message);
        }
    }

    public static void w(String tag, String message) {
        if (BuildConfig.DEBUG && message != null) {
            Log.w(tag, message);
        }
    }

    public static void w(String message) {
        if (BuildConfig.DEBUG && message != null) {
            Log.w(defaultLog, message);
        }
    }

    public static void v(String tag, String message) {
        if (BuildConfig.DEBUG && message != null) {
            Log.v(tag, message);
        }
    }

    public static void v(String message) {
        if (BuildConfig.DEBUG && message != null) {
            Log.v(defaultLog, message);
        }
    }

    public static void i(String tag, String message) {
        if (BuildConfig.DEBUG && message != null) {
            Log.i(tag, message);
        }
    }

    public static void i(String message) {
        if (BuildConfig.DEBUG && message != null) {
            Log.i(defaultLog, message);
        }
    }

    public static void custom(String tag, String message) {
        if (BuildConfig.DEBUG && message != null) {
            int maxLogSize = 1000;
            for (int i = 0; i <= message.length() / maxLogSize; i++) {

                int start = i * maxLogSize;
                int end = (i + 1) * maxLogSize;

                end = end > message.length() ? message.length() : end;

                Log.i(tag, message.substring(start, end));
            }
        }
    }
}