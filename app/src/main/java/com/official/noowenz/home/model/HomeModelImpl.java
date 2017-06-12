package com.official.noowenz.home.model;

import android.content.res.Resources;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.official.noowenz.R;
import com.official.noowenz.helpers.CommonDef;
import com.official.noowenz.helpers.SharedPreference;
import com.official.noowenz.home.presenter.IHomePresenter;
import com.official.noowenz.home.view.HomeActivity;
import com.official.noowenz.retrofit.ApiServices;

import java.net.ConnectException;
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
