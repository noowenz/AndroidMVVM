package com.official.noowenz1.home.model;

import android.content.res.Resources;
import android.view.View;

import com.official.noowenz1.helpers.SharedPreference;
import com.official.noowenz1.home.presenter.IHomePresenter;
import com.official.noowenz1.retrofit.ApiServices;

import java.util.ArrayList;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by nabin on 12/9/16.
 */

public class HomeModelImpl implements IHomeModel {
    ApiServices apiService;
    SharedPreference prefs;
    Resources resources;

    @Inject
    public HomeModelImpl(ApiServices apiServices, SharedPreference prefs, Resources resources) {
        this.apiService = apiServices;
        this.prefs = prefs;
        this.resources = resources;
    }

    @Override
    public void doGetList(final IHomePresenter listener) {
        Call<ArrayList<GetNewsResponse>> call = apiService.getNews();
        call.enqueue(new Callback<ArrayList<GetNewsResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<GetNewsResponse>> call, Response<ArrayList<GetNewsResponse>> response) {
                try {
                    if (response.isSuccessful()) {
                        listener.getListResponse(response);
                    } else {
                        listener.setProgressBarVisibility(View.GONE);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    listener.setProgressBarVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<GetNewsResponse>> call, Throwable t) {
                t.printStackTrace();
                listener.setProgressBarVisibility(View.GONE);
            }
        });
    }
}
