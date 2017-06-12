package com.official.noowenz.menu;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.NavUtils;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.official.noowenz.R;
import com.official.noowenz.helpers.Alerts;
import com.official.noowenz.helpers.SharedPreference;

import java.util.ArrayList;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * This is a burger Menu bar...
 */
public abstract class BaseDrawer extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ArrayList<NavDrawerItems> navDrawerItems;
    private String[] navMenuTitles;
    private TypedArray navMenuIcons;
    private NavDrawerListAdapter adapter;
    private DrawerLayout mDrawerLayout;
    private LinearLayout mainView;
    private LayoutInflater inflater;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    public static boolean BACK_BUTTON_ENABLED = false;
    public static boolean DRAWER_BUTTON_ENABLED = false;
    public String DRAWER_ID = "001";
    public String BACK_BUTTON_ID = "002";
    public static boolean ACTION_BAR_ENABLED = true;
    private ActionBar actionBar;
    public ImageButton ibtnActionBtn;
    public TextView tvActionTitle;
    private SharedPreference prefs;
    private Alerts alerts;
    public RelativeLayout rlCustomActionBar;
    protected ImageButton ibtnAction;
    protected ImageButton ibtnBack;
    private LinearLayout llNavHeader;

    /**
     * @return the view to load in this activity.
     */
    protected abstract int getLayoutId();

    protected abstract String actionBarTitle();

    protected abstract String currentActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        actionBar = getSupportActionBar();

        prefs = new SharedPreference(this);
        alerts = new Alerts(this);

        inflater = (LayoutInflater) getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
        intiNavDrawer();

        View view = inflater.inflate(getLayoutId(), null, false);
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.container);
        frameLayout.addView(view);
        initCustomBar();
    }

    @SuppressWarnings("ResourceType")
    private void intiNavDrawer() {
        ibtnAction = (ImageButton) findViewById(R.id.ibtn_action);
        ibtnBack = (ImageButton) findViewById(R.id.ibtn_back);
        llNavHeader = (LinearLayout) findViewById(R.id.ll_nav_header);
//        llNavHeader.setOnClickListener(actionBarClickListener);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mainView = (LinearLayout) findViewById(R.id.holder);
        mDrawerList = (ListView) findViewById(R.id.lv_menu);

        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);
        navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);
        navDrawerItems = new ArrayList<NavDrawerItems>();

        navDrawerItems.add(new NavDrawerItems(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
        navDrawerItems.add(new NavDrawerItems(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));

        // Recycle the typed array
        navMenuIcons.recycle();
        // setting the nav drawer list adapter
        adapter = new NavDrawerListAdapter(getApplicationContext(), navDrawerItems);
        mDrawerList.setAdapter(adapter);

        //shifting the mainview along with the sliding menu
        mDrawerToggle = new ActionBarDrawerToggle(BaseDrawer.this, mDrawerLayout, R.drawable.ic_menu, R.string.app_name, R.string.app_name) {
            public void onDrawerClosed(View view) {
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                invalidateOptionsMenu();
//                supportInvalidateOptionsMenu();
                hideSoftKeyboard();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
//                mainView.setTranslationX(-slideOffset * drawerView.getWidth());
                mDrawerLayout.bringChildToFront(drawerView);
                mDrawerLayout.requestLayout();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerList.setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position == 0) {
            if (!currentActivity().equalsIgnoreCase("Home")) {
                // do here
            } else {
                mDrawerLayout.closeDrawer(Gravity.START);
            }
        }
    }

    void initCustomBar() {
        ibtnActionBtn = (ImageButton) findViewById(R.id.ibtn_action);
        tvActionTitle = (TextView) findViewById(R.id.tv_action);
        tvActionTitle.setText(actionBarTitle());
        rlCustomActionBar = (RelativeLayout) findViewById(R.id.ll_custom_action_bar);
        enableDrawerButton();
    }

    public void enableDrawerButton() {
        BACK_BUTTON_ENABLED = false;
        DRAWER_BUTTON_ENABLED = true;
        ibtnActionBtn.setImageResource(R.drawable.ic_menu);
        ibtnActionBtn.setTag(DRAWER_ID);
        ibtnActionBtn.setOnClickListener(actionBarClickListener);
    }

    public View.OnClickListener actionBarClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.ll_nav_header) {
                if (!currentActivity().equalsIgnoreCase("Header")) {
                    //do here
                } else {
                    mDrawerLayout.closeDrawer(Gravity.START);
                }
            } else {
                if (v.getTag().equals(BACK_BUTTON_ID)) {
                    NavUtils.navigateUpFromSameTask(BaseDrawer.this);
                } else if (v.getTag().equals(DRAWER_ID)) {
                    if (mDrawerLayout.isDrawerOpen(Gravity.START)) {
                        mDrawerLayout.closeDrawer(Gravity.START);
                    } else {
                        mDrawerLayout.openDrawer(Gravity.START);
                    }
                }
            }
        }
    };

    public void hideSoftKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (inputMethodManager.isAcceptingText()) {
            inputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
        }
    }

    public void hideNavigationDrawer() {
        ibtnActionBtn.setVisibility(View.GONE);
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
