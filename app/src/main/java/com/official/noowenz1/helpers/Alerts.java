package com.official.noowenz1.helpers;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Rect;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.official.noowenz1.R;


/**
 * Created by nabin on 9/12/16.
 */
public class Alerts {
    public static final String TAG = "Alerts";
    private Activity activity;
    private LayoutInflater li;
    private View promptsView;
    private AlertDialog alert;
    private ImageView ivIcon;
    private TextView tvStatus;
    private TextView tvMsg;
    private Button btnOk;
    private Button btnYes;
    private Button btnNo;
    private AlertDialog alertDialog;
    private AlertDialog.Builder alertDialogBuilder;
    private Rect displayRectangle;
    private SharedPreference prefs;
    private NwzProgressDialog progressDialog;

    public Alerts(Activity activity) {
        this.activity = activity;
        this.li = LayoutInflater.from(activity);

        prefs = new SharedPreference(activity);
        progressDialog = new NwzProgressDialog(activity);
        displayRectangle = new Rect();
        Window window = activity.getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);
    }

    private AlertDialog.Builder initAlertDialog(String title, String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        if (title.isEmpty()) {
            title = activity.getResources().getString(R.string.app_name);
        }
        builder.setTitle(title);
        builder.setMessage(msg);
        return builder;
    }

    public void alertToLogOut() {
        AlertDialog.Builder builder = initAlertDialog("", activity.getString(R.string.logOut_msg));
        builder.setCancelable(false);
        builder.setPositiveButton(activity.getResources().getString(R.string.yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

                clearUserInfo();

            }
        }).setNegativeButton(activity.getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        alertDialog = builder.create();
        alertDialog.show();
    }

    public void clearUserInfo() {
        prefs.setKeyValues(CommonDef.SharedPreferences.isLoggedIn, false);
        prefs.setKeyValues(CommonDef.SharedPreferences.USER_ID, "");
        prefs.setKeyValues(CommonDef.SharedPreferences.AUTH_KEY, "");
        prefs.setKeyValues(CommonDef.SharedPreferences.HASH_CODE, "");
        prefs.setKeyValues(CommonDef.SharedPreferences.EMAIL, "");
        prefs.setKeyValues(CommonDef.SharedPreferences.IMAGE_URL, "");
    }
}
