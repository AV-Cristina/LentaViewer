package com.course.innopolis.lentaviewer.utils;

import com.course.innopolis.lentaviewer.models.News;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by Cristina on 20.07.2017.
 */

public class XmlLoader {
    private String urlString;

    public XmlLoader(String urlString) {
        this.urlString = urlString;
    }

    public List<News> loadXmlFromNetwork() throws XmlPullParserException, IOException {
        InputStream stream = null;
        List<News> newsList  = null;
        NewsXmlParser newsXmlParser = new NewsXmlParser();
        try {
            stream = downloadUrl();
            newsList = newsXmlParser.parse(stream);
        } finally {
            if (stream != null) {
                stream.close();
            }
        }
        return newsList;
    }

    private InputStream downloadUrl() throws IOException {
        URL url = new URL(this.urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setReadTimeout(10000 );
        conn.setConnectTimeout(15000 );
        conn.setRequestMethod("GET");
        conn.setDoInput(true);
        conn.connect();
        return conn.getInputStream();
    }


}
