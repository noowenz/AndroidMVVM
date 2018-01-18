package com.official.noowenz1.home.view;

import com.official.noowenz1.home.model.GetNewsResponse;

import java.util.ArrayList;

import retrofit2.Response;

/**
 * Created by ebpearls on 6/12/2017.
 */

public interface IHomeView {
    void getListSuccess(Response<ArrayList<GetNewsResponse>> response);
    void onSetProgressBarVisibility(int visibility);
    void onShowError(String error);
}
