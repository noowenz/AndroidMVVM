package com.official.android_mvvm.ui.home.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.widget.TextView;
import android.widget.Toast;

import com.official.android_mvvm.R;
import com.official.android_mvvm.helper.AppConstants;
import com.official.android_mvvm.ui.about.view.AboutFragment;
import com.official.android_mvvm.base.BaseActivity;
import com.official.android_mvvm.data.common.LiveDataResponse;
import com.official.android_mvvm.ui.home.model.HomeModel;
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

    @BindView(R.id.tv_msg2)
    TextView tvMsg2;

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

    @Inject
    HomeViewModel homeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
    }

    private void init() {
        if (isNetworkConnected()) {
            homeViewModel.setBaseModel();
            homeViewModel.getResponse().observe(this, liveDataResponse -> processResponse(liveDataResponse));
            homeViewModel.getUser(AppConstants.REQUEST_USER);
            homeViewModel.getUserDetails(AppConstants.REQUEST_USER_DETAILS);

        } else {
            Toast.makeText(this, "No internet connection available.", Toast.LENGTH_SHORT).show();
        }
    }

    private void processResponse(LiveDataResponse liveDataResponse) {
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

    private void renderErrorState(Throwable error) {
        hideLoading();
        error.printStackTrace();
    }

    private void renderDataState(HomeModel data, int requestCode) {
        hideLoading();
        if (requestCode == AppConstants.REQUEST_USER)
            tvMsg.setText(data.getUser().getName());
        else
            tvMsg2.setText(data.getUserDetails().getAddress());
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
