package com.official.noowenz.home.presenter;

import android.view.View;

import com.official.noowenz.helpers.SharedPreference;
import com.official.noowenz.home.model.GetNewsResponse;
import com.official.noowenz.home.model.HomeModelImpl;
import com.official.noowenz.home.view.IHomeView;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Response;

/**
 * Created by nabin on 12/9/16.
 */

public class HomePresenterImpl implements IHomePresenter {
    IHomeView iHomeView;
    HomeModelImpl homeModel;
    SharedPreference prefs;

    @Inject
    public HomePresenterImpl(IHomeView iHomeView, HomeModelImpl homeModel, SharedPreference prefs) {
        this.iHomeView = iHomeView;
        this.homeModel = homeModel;
        this.prefs = prefs;
    }

    @Override
    public void getList() {
        homeModel.doGetList(this);
    }

    @Override
    public void getListResponse(Response<ArrayList<GetNewsResponse>> response) {
       iHomeView.getListSuccess(response);
    }

    @Override
    public void setProgressBarVisibility(int show) {
        iHomeView.onSetProgressBarVisibility(show);
    }

    @Override
    public void onShowError(String error) {
        iHomeView.onShowError(error);
    }
}
