package com.course.innopolis.lentaviewer.app;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.course.innopolis.lentaviewer.adapters.NewsAdapter;
import com.course.innopolis.lentaviewer.R;
import com.course.innopolis.lentaviewer.models.News;
import com.course.innopolis.lentaviewer.utils.XmlLoader;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnListItemCallback{
    final String LOG_TAG = "myLogs";
    private static final String URL = "https://lenta.ru/rss";

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.news_recycler_view);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        new DownloadXmlTask().execute(URL);
    }


    // Просмотреть новость подробнее
    @Override
    public void onClick(News news) {
        Toast.makeText(this, "Selected " + news.getTitle(), Toast.LENGTH_LONG).show();
        //Intent intent = new Intent();
        //startActivity(intent);
    }


    public void onClickRefreshData(View view){
        new DownloadXmlTask().execute(URL);
    }


    private class DownloadXmlTask extends AsyncTask<String, Void, List<News>> {
        @Override
        protected List<News> doInBackground(String... urls) {
            List<News> newsList = null;
            XmlLoader xmlLoader = new XmlLoader(URL);
            try {
                newsList = xmlLoader.loadXmlFromNetwork();
            } catch (IOException e) {
                Log.d(LOG_TAG, "Connection error");
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                Log.d(LOG_TAG, "XML error");
                e.printStackTrace();
            }
            return newsList;
        }


        @Override
        protected void onPostExecute(List<News> newsList) {
            // вывод загруженного из xml-файла списка
            mAdapter = new NewsAdapter(MainActivity.this, newsList, MainActivity.this);
            mRecyclerView.setAdapter(mAdapter);
        }
    }
}
