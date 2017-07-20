package com.course.innopolis.lentaviewer;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    final String LOG_TAG = "myLogs";

    private static final String URL = "https://lenta.ru/rss";

    // listView для отображения списка новостей
    private ListView listView;

    // адаптер для кастомизированного списка объектов News
    private NewsListAdapter newsListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // создание listView для отображения списка
        listView = (ListView) findViewById(R.id.listView);
        // создание адаптера
        newsListAdapter = new NewsListAdapter(this);
        // используется AsyncTask, чтобы загрузить XML и вывести список новостей
        new DownloadXmlTask().execute(URL);
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
        protected void onPostExecute(List<News> currencyList) {
            // вывод загруженного из xml-файла списка
            newsListAdapter = new NewsListAdapter(MainActivity.this, currencyList);
            listView.setAdapter(newsListAdapter);
        }
    }
}
