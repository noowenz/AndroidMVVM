package com.official.android_mvvm.ui.home.viewModel;

/**
 * Created by ebpearls on 2/1/2018.
 */

public interface IHomeViewModel {
    void getUser(int request_code);

    void getUserDetails(int request_code);

    String getEmail();
}
