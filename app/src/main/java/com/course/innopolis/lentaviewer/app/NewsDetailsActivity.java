package com.course.innopolis.lentaviewer.app;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.course.innopolis.lentaviewer.R;
import com.course.innopolis.lentaviewer.models.News;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class NewsDetailsActivity extends AppCompatActivity {

    private TextView pubDate;
    private TextView title;
    private TextView description;

    ImageView imageView;
    DownloadManager downloadManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);

        News news = (News)getIntent().getParcelableExtra(News.class.getCanonicalName());

        pubDate = (TextView)findViewById(R.id.news_pubDate);
        title = (TextView)findViewById(R.id.news_title);
        description = (TextView)findViewById(R.id.news_description);

        pubDate.setText(news.getPubDate());
        title.setText(news.getTitle());
        description.setText(news.getDescription());

        imageView = (ImageView)findViewById(R.id.news_photo);

        downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);

        // Создание запроса
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(news.getEnclosurejpg()));
        request.setTitle("Title"); //заголовок будущей нотификации
        request.setDescription("My description"); //описание будущей нотификации
        request.setMimeType("application/my-mime"); //mine type загружаемого файла

        // флаг,что-бы уведомление осталось по окончании загрузки
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        // Добавление запроса в очередь
        downloadManager.enqueue(request);
    }


    @Override
    protected void onResume() {
        super.onResume();
        // Подписываемся на сообщения от сервиса
        registerReceiver(receiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
        registerReceiver(receiver, new IntentFilter(DownloadManager.ACTION_NOTIFICATION_CLICKED));
    }


    @Override
    protected void onPause() {
        super.onPause();
        // Отписываемся от сообщений сервиса
        unregisterReceiver(receiver);
    }


    BroadcastReceiver receiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            // Сообщение о том, что загрузка закончена
            if (DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(action)){
                long downloadId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, 0);
                DownloadManager dm = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
                DownloadManager.Query query = new DownloadManager.Query();
                query.setFilterById(downloadId);
                Cursor cursor = dm.query(query);
                if (cursor.moveToFirst()){
                    int columnIndex = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS);
                    if (DownloadManager.STATUS_SUCCESSFUL == cursor.getInt(columnIndex)) {
                        String uriString = cursor.getString(cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI));
                        imageView.setImageURI(Uri.parse(uriString));
                    }
                }

                // Сообщение о клике по нотификации
            } else if (DownloadManager.ACTION_NOTIFICATION_CLICKED.equals(action)){
                DownloadManager dm = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
                // несколько параллельных загрузок могут быть объеденены в одну нотификацию,
                // поэтому пытаемся получить список всех загрузок, связанных с выбранной нотификацией
                long[] ids = intent.getLongArrayExtra(DownloadManager.EXTRA_NOTIFICATION_CLICK_DOWNLOAD_IDS);
                DownloadManager.Query query = new DownloadManager.Query();
                query.setFilterById(ids);
                Cursor cursor = dm.query(query);
                int idIndex = cursor.getColumnIndex(DownloadManager.COLUMN_ID);
                if (cursor.moveToFirst()){
                    do {
                        long downloadId = cursor.getLong(idIndex);

                    } while (cursor.moveToNext());
                }
            }
        }
    };
}

