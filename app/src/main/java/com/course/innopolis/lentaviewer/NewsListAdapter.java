package com.course.innopolis.lentaviewer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Cristina on 20.07.2017.
 * Адаптер для отображения кастомизированного списка новостей
 */

public class NewsListAdapter extends BaseAdapter {
        Context ctx;
        LayoutInflater lInflater;
        List<News> objects;

    NewsListAdapter(Context context) {
            ctx = context;
            lInflater = (LayoutInflater) ctx
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        NewsListAdapter(Context context, List<News> currencyList) {
            ctx = context;
            objects = currencyList;
            lInflater = (LayoutInflater) ctx
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        // количество элементов
        @Override
        public int getCount() {
            return objects.size();
        }

        // элемент по позиции
        @Override
        public Object getItem(int position) {
            return objects.get(position);
        }

        // id по позиции
        @Override
        public long getItemId(int position) {
            return position;
        }

        // пункт списка
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            if (view == null) {
                view = lInflater.inflate(R.layout.news_row, parent, false);
            }

            News news = getNews(position);

            // заполнение элемента View :
            ((TextView) view.findViewById(R.id.news_pubDate)).setText(news.getPubDate());
            ((TextView) view.findViewById(R.id.news_category)).setText(news.getCategory());
            ((TextView) view.findViewById(R.id.news_title)).setText(news.getTitle());

            return view;
        }

        // возвращает News по позиции
        News getNews(int position) {
            return ((News) getItem(position));
        }

    }