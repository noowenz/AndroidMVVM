package com.official.noowenz1.menu;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.official.noowenz1.R;
import com.official.noowenz1.helpers.SharedPreference;

import java.util.ArrayList;

/**
 * Created by nabin on 7/23/2015.
 */
public class NavDrawerListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<NavDrawerItems> navDrawerItems;
    private SharedPreference prefs;

    public NavDrawerListAdapter(Context context, ArrayList<NavDrawerItems> navDrawerItems){
        this.context = context;
        this.navDrawerItems = navDrawerItems;
        prefs = new SharedPreference(context);
    }

    @Override
    public int getCount() {
        return navDrawerItems.size();
    }

    @Override
    public Object getItem(int position) {
        return navDrawerItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.drawer_list_item, null);
        }

        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "fonts/SourceSansPro-SemiBold.ttf");

        RelativeLayout rlDrawerItem = (RelativeLayout) convertView.findViewById(R.id.rl_drawer_item);
        ImageView imgIcon = (ImageView) convertView.findViewById(R.id.drawer_list_icon);
        TextView txtTitle = (TextView) convertView.findViewById(R.id.drawer_list_item);
        txtTitle.setTypeface(typeface);

        if(imgIcon != null) {
            imgIcon.setImageResource(navDrawerItems.get(position).getIcon());
        }

        if(txtTitle != null) {
            txtTitle.setText(navDrawerItems.get(position).getTitle());
        }

        return convertView;
    }
}
