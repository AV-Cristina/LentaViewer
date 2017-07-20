package com.course.innopolis.lentaviewer.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.course.innopolis.lentaviewer.R;
import com.course.innopolis.lentaviewer.app.OnListItemCallback;
import com.course.innopolis.lentaviewer.models.News;

import java.util.List;

/**
 * Created by Cristina on 20.07.2017.
 * Класс-адаптер для вывода списка новостей в RecyclerView
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder>{

    private String TAG = "RecyclerView ";
    private List<News> mNewsList;
    private LayoutInflater mInflater;
    private OnListItemCallback callback;

    public NewsAdapter(Context context, List<News> mNewsList, OnListItemCallback callback) {
        this.mNewsList = mNewsList;
        this.mInflater = LayoutInflater.from(context);
        this.callback = callback;
    }


    @Override
    public NewsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder");
        View view = mInflater.inflate(R.layout.news_item, parent, false);
        NewsHolder holder = new NewsHolder(view, callback);
        return holder;
    }


    @Override
    public void onBindViewHolder(NewsHolder holder, int position) {
        News currentNews = mNewsList.get(position);
        holder.setData(currentNews, position);
    }


    @Override
    public int getItemCount() {
        return mNewsList.size();
    }


    class NewsHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView pubDate, category, title;
        int position;
        News current;
        private OnListItemCallback callback;


        public NewsHolder(View itemView, OnListItemCallback callback) {
            super(itemView);
            pubDate = (TextView)itemView.findViewById(R.id.news_pubDate);
            category = (TextView)itemView.findViewById(R.id.news_category);
            title = (TextView)itemView.findViewById(R.id.news_title);
            this.callback= callback;
            itemView.setOnClickListener(this);
        }


        public void setData(News current, int position) {
            this.pubDate.setText(current.getPubDate());
            this.category.setText(current.getCategory());
            this.title.setText(current.getTitle());
            this.position = position;
            this.current = current;
        }


        @Override
        public void onClick(View v) {
            callback.onClick(current);
        }
    }
}
