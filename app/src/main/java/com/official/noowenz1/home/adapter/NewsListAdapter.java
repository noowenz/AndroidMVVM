package com.official.noowenz1.home.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.official.noowenz1.R;
import com.official.noowenz1.home.model.GetNewsResponse;

import java.util.ArrayList;

import retrofit2.Response;

/**
 * Created by eb-nabin on 2/5/17.
 */

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.ItemViewHolder> {
    private Activity activity;
    private Response<ArrayList<GetNewsResponse>> newsList;

    public NewsListAdapter(Activity activity, Response<ArrayList<GetNewsResponse>> newsList) {
        this.activity = activity;
        this.newsList = newsList;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return newsList.body().size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public NewsListAdapter.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NewsListAdapter.ItemViewHolder holder, int position) {
        holder.tvContent.setText(Html.fromHtml(newsList.body().get(position).content.rendered));
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        public TextView tvContent;

        public ItemViewHolder(View view) {
            super(view);
            tvContent = (TextView) view.findViewById(R.id.tv_content);
        }
    }
}