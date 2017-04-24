package com.official.news.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.official.news.R;
import com.official.news.helpers.Alerts;
import com.official.news.helpers.CustomProgressDialog;
import com.official.news.menu.BaseDrawer;
import com.official.news.retrofit.ApiClient;
import com.official.news.retrofit.ApiServices;
import com.official.news.retrofit.ErrorResponse;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends BaseDrawer {
    private CustomProgressDialog progressDialog;
    private Alerts alerts;
    private NewsListAdapter newsListAdapter;

    @BindView(R.id.rv_news)
    RecyclerView rvNewsList;

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

        progressDialog = new CustomProgressDialog(this);
        alerts = new Alerts(this);

        init();
    }

    private void init(){
        rvNewsList.setLayoutManager(new LinearLayoutManager(this));

        getNewsList();
    }

    private void getNewsList(){
        progressDialog.showPd("Please wait...");
        ApiServices apiService = ApiClient.getClient().create(ApiServices.class);
        Call<ArrayList<GetNewsResponse>> call = apiService.getNews();
        call.enqueue(new Callback<ArrayList<GetNewsResponse>>() {
            @Override
            public void onResponse(Call<ArrayList<GetNewsResponse>> call, Response<ArrayList<GetNewsResponse>> response) {
                Log.d("Response Register:", "response" + response);
                progressDialog.hidePd();
                try {
                    if (response.isSuccessful()) {
                        loadNews(response);
                    } else {
                        Toast.makeText(HomeActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<GetNewsResponse>> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(HomeActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadNews(Response<ArrayList<GetNewsResponse>> newsList){
        newsListAdapter = new NewsListAdapter(this, newsList);
        rvNewsList.setAdapter(newsListAdapter);
    }
}
