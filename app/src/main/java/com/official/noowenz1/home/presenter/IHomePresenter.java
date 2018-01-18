package com.official.noowenz1.home.presenter;

import com.official.noowenz1.home.model.GetNewsResponse;

import java.util.ArrayList;

import retrofit2.Response;

/**
 * Created by ebpearls on 6/12/2017.
 */

public interface IHomePresenter {
    void getList();
    void getListResponse(Response<ArrayList<GetNewsResponse>> response);
    void setProgressBarVisibility(int show);
    void onShowError(String error);
}
