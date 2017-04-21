package com.official.news.helpers;

/**
 * Created by nabin on 10/18/16.
 */

import android.app.Activity;
import android.app.ProgressDialog;

public class CustomProgressDialog {
    ProgressDialog mProgress;

    public CustomProgressDialog(Activity activity) {
        mProgress = new ProgressDialog(activity);
        mProgress.setIndeterminate(false);
    }

    public void showPd(String message) {
        mProgress.setMessage(message);
        mProgress.setCancelable(false);
        mProgress.show();
    }

    public void hidePd() {
        mProgress.dismiss();
    }
}
