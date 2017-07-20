package com.course.innopolis.lentaviewer.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.course.innopolis.lentaviewer.R;
import com.course.innopolis.lentaviewer.models.News;

public class NewsDetailsActivity extends AppCompatActivity {

    private TextView pubDate;
    private TextView title;
    private TextView description;


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

        Toast.makeText(this, news.getEnclosurejpg(), Toast.LENGTH_LONG).show();
    }
}
