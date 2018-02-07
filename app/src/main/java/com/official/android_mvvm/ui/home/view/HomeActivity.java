package com.official.android_mvvm.ui.home.view;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.official.android_mvvm.R;
import com.official.android_mvvm.base.BaseActivity;
import com.official.android_mvvm.data.common.LiveDataResponse;
import com.official.android_mvvm.helper.AppConstants;
import com.official.android_mvvm.ui.about.view.AboutFragment;
import com.official.android_mvvm.ui.home.model.HomeModel;
import com.official.android_mvvm.ui.home.model.User;
import com.official.android_mvvm.ui.home.model.UserDetails;
import com.official.android_mvvm.ui.home.viewModel.HomeViewModel;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import retrofit2.http.PUT;

public class HomeActivity extends BaseActivity<HomeViewModel, HomeModel> implements HasSupportFragmentInjector, HomeNavigator {

    @BindView(R.id.tv_msg)
    TextView tvMsg;
    @BindView(R.id.tv_msg2)
    TextView tvMsg2;
    @BindView(R.id.iv_error)
    ImageView ivError;
    @BindView(R.id.tv_refresh)
    TextView tvRefresh;
    @BindView(R.id.ll_progress)
    LinearLayout llProgress;
    @BindView(R.id.ll_main_view)
    LinearLayout llMainView;
    @BindView(R.id.pb_loading)
    ProgressBar pbLoading;

    @Inject
    HomeViewModel homeViewModel;

    @Inject
    ViewModelProvider.Factory mViewModelFactory;

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        homeViewModel.setNavigator(this);
        init();
    }

    @Override
    public void init() {
        if (isNetworkConnected()) {
            llMainView.setVisibility(View.VISIBLE);
            llProgress.setVisibility(View.GONE);

            homeViewModel.getResponse().observe(this, liveDataResponse -> processResponse(liveDataResponse));
            homeViewModel.setBaseModel();
            homeViewModel.getUser(AppConstants.REQUEST_USER);
            homeViewModel.getUserDetails(AppConstants.REQUEST_USER_DETAILS);
        } else {
            llMainView.setVisibility(View.GONE);
            llProgress.setVisibility(View.VISIBLE);
            Toast.makeText(this, "No internet connection available.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
     public void processResponse(LiveDataResponse liveDataResponse) {
        switch (liveDataResponse.status) {
            case LOADING:
                renderLoadingState();
                break;

            case SUCCESS:
                renderDataState((HomeModel) liveDataResponse.data, liveDataResponse.requestCode);
                break;

            case ERROR:
                renderErrorState(liveDataResponse.error);
                break;
        }
    }

    @Override
    public void renderErrorState(String error) {
        hideLoadingDialog(false);
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void renderDataState(HomeModel data, int requestCode) {
        hideLoadingDialog(false);
        if (data != null && data.getUser() != null && data.getUserDetails() != null) {
            tvMsg.setText(data.getUser().getName());
            tvMsg2.setText(data.getUserDetails().getAddress());
        } else {
            if (requestCode == AppConstants.REQUEST_USER) {
                tvMsg.setText(data.getUser().getName());
            } else if (requestCode == AppConstants.REQUEST_USER_DETAILS) {
                tvMsg2.setText(data.getUserDetails().getAddress());
            }
        }
    }

    @Override
    public void renderLoadingState() {
        showLoadingDialog(false);
    }

    @Override
    public void showProgressBar() {
        pbLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        pbLoading.setVisibility(View.GONE);
    }

    @Override
    public HomeViewModel getViewModel() {
        homeViewModel = ViewModelProviders.of(this, mViewModelFactory).get(HomeViewModel.class);
        return homeViewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @OnClick(R.id.tv_msg)
    void showAboutFragment() {
        homeViewModel.openAboutFragment();
    }

    @OnClick(R.id.tv_msg2)
    void changeLiveDataValue() {
        HomeModel homeModel = new HomeModel();
        homeModel.setUser(new User("Noowenz", "nabin.shrestha@ebpearls.com", "noowenz.com.np"));
        homeModel.setUserDetails(new UserDetails("Kupondole, Nepal", "46546544664646"));
        homeViewModel.getResponse().setValue(LiveDataResponse.success(homeModel, AppConstants.REQUEST_USER));
    }

    @OnClick(R.id.tv_refresh)
    void retry() {
        init();
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(AboutFragment.TAG);
        if (fragment == null) {
            super.onBackPressed();
        } else {
            onFragmentDetached(AboutFragment.TAG);
        }
    }

    @Override
    public void onFragmentDetached(String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        if (fragment != null) {
            fragmentManager
                    .beginTransaction()
                    .disallowAddToBackStack()
                    .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                    .remove(fragment)
                    .commitNow();
        }
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }

    @Override
    public void openAboutFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                .add(R.id.rl_home, AboutFragment.newInstance(), AboutFragment.TAG)
                .commit();
    }

    @Override
    public void handleError(Throwable throwable) {

    }
}
