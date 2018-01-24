package com.official.android_mvvm.ui.home.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.widget.TextView;
import android.widget.Toast;

import com.official.android_mvvm.R;
import com.official.android_mvvm.ui.about.view.AboutFragment;
import com.official.android_mvvm.base.BaseActivity;
import com.official.android_mvvm.data.common.LiveDataResponse;
import com.official.android_mvvm.ui.home.model.User;
import com.official.android_mvvm.ui.home.viewModel.HomeViewModel;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class HomeActivity extends BaseActivity<HomeViewModel> implements HasSupportFragmentInjector {

    @BindView(R.id.tv_msg)
    TextView tvMsg;

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

    @Inject
    HomeViewModel homeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        homeViewModel.getResponse().observe(this, liveDataResponse -> processResponse(liveDataResponse));
        init();
    }

    private void init() {
        if (isNetworkConnected())
            homeViewModel.getUser();
        else
            Toast.makeText(this, "No internet connection available.", Toast.LENGTH_SHORT).show();
    }

    private void processResponse(LiveDataResponse liveDataResponse) {
        switch (liveDataResponse.status) {
            case LOADING:
                renderLoadingState();
                break;

            case SUCCESS:
                renderDataState((User) liveDataResponse.data);
                break;

            case ERROR:
                renderErrorState(liveDataResponse.error);
                break;
        }
    }

    private void renderErrorState(Throwable error) {
        hideLoading();
    }

    private void renderDataState(User data) {
        hideLoading();
        tvMsg.setText(data.getName());
        tvMsg.setText(homeViewModel.getEmail());
    }

    private void renderLoadingState() {
        showLoading();
    }

    @Override
    public HomeViewModel getViewModel() {
        return homeViewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @OnClick(R.id.tv_msg)
    void showAboutFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .disallowAddToBackStack()
                .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                .add(R.id.rl_home, AboutFragment.newInstance(), AboutFragment.TAG)
                .commit();
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
}
