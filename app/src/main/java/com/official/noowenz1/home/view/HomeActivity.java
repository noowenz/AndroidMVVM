package com.official.noowenz1.home.view;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;
import com.official.noowenz1.R;
import com.official.noowenz1.helpers.Alerts;
import com.official.noowenz1.helpers.NwzProgressDialog;
import com.official.noowenz1.helpers.SharedPreference;
import com.official.noowenz1.home.injection.DaggerHomeComponent;
import com.official.noowenz1.home.injection.HomeModule;
import com.official.noowenz1.home.model.GetNewsResponse;
import com.official.noowenz1.home.adapter.NewsListAdapter;
import com.official.noowenz1.home.presenter.HomePresenterImpl;
import com.official.noowenz1.menu.BaseDrawer;
import com.official.noowenz1.utils.MyApplication;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;

public class HomeActivity extends BaseDrawer implements IHomeView, SwipeRefreshLayout.OnRefreshListener{
    private NwzProgressDialog progressDialog;
    private Alerts alerts;
    private NewsListAdapter newsListAdapter;
    private boolean isNewsRefreshing = false;

    @BindView(R.id.rv_news)
    RecyclerView rvNewsList;

    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout setRefreshing;

    @Inject
    HomePresenterImpl presenter;

    @Inject
    SharedPreference prefs;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected String actionBarTitle() {
        return "Home";
    }

    @Override
    protected String currentActivity() {
        return "Home";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        DaggerHomeComponent.builder()
                .netComponent(((MyApplication) getApplicationContext()).getNetComponent())
                .homeModule(new HomeModule(this))
                .build()
                .inject(this);

        progressDialog = new NwzProgressDialog(this);
        alerts = new Alerts(this);

        init();
    }

    private void init(){
        rvNewsList.setLayoutManager(new LinearLayoutManager(this));
        setRefreshing.setOnRefreshListener(this);

        presenter.getList();
        presenter.setProgressBarVisibility(View.VISIBLE);
    }

    private void manageProgressDialog(){
        if (isNewsRefreshing){
            setRefreshing.setRefreshing(false);
            isNewsRefreshing = false;
        } else {
            progressDialog.hidePd();
        }
    }

    private void loadNews(Response<ArrayList<GetNewsResponse>> newsList){
        manageProgressDialog();
        newsListAdapter = new NewsListAdapter(this, newsList);
        rvNewsList.setAdapter(newsListAdapter);
    }

    @Override
    public void onRefresh() {
        if (!isNewsRefreshing) {
            isNewsRefreshing = true;
            presenter.getList();
        }
    }

    @Override
    public void getListSuccess(Response<ArrayList<GetNewsResponse>> response) {
        loadNews(response);
    }

    @Override
    public void onSetProgressBarVisibility(int visibility) {
        if (visibility == View.VISIBLE) {
            progressDialog.showPd("Please wait...");
        } else {
            progressDialog.hidePd();
        }
    }

    @Override
    public void onShowError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }
}
