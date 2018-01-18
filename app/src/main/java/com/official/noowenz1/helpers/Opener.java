package com.official.noowenz1.helpers;

import android.app.Activity;
import android.content.Intent;
import com.official.noowenz1.home.view.HomeActivity;
import com.official.noowenz1.R;


/**
 * Created by nabin on 9/7/16.
 */
public class Opener {

    public static void Back(Activity activity) {
        activity.finish();
        activity.overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

    public static void HomeActivity(Activity activity) {
        Intent intent = new Intent(activity, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }
}
