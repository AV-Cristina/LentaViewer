package com.course.innopolis.lentaviewer.utils;

import android.util.Log;
import android.util.Xml;

import com.course.innopolis.lentaviewer.models.News;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cristina on 20.07.2017.
 */

public class NewsXmlParser {

    final static String LOG_TAG = "NewsParserLogs ";

    public List<News> parse(InputStream in) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            return readNewsList(parser);
        } finally {
            in.close();
        }
    }

    // Парсим xml файл в список объектов News
    private static List<News> readNewsList(XmlPullParser parser) throws XmlPullParserException, IOException {
        List<News> newsList = new ArrayList();
        News news = null;

        while (parser.getEventType() != XmlPullParser.END_DOCUMENT) {

            if (parser.getEventType() == XmlPullParser.START_TAG) {

                if (parser.getName().equals("title")) {
                    news = new News();
                    news.setTitle(parser.nextText());
                    Log.d(LOG_TAG, "title = " + news.getTitle());
                }

                else if (news != null){
                    switch (parser.getName()) {
                        case "description":
                            news.setDescription(parser.nextText());
                            Log.d(LOG_TAG, "description = " + news.getDescription());
                            break;
                        case "pubDate":
                            String pubDate = parser.nextText();
                            news.setPubDate(pubDate.substring(0, pubDate.length() - 6));
                            Log.d(LOG_TAG, "pubDate = " + news.getPubDate());
                            break;
                        case "enclosure":
                            news.setEnclosurejpg(parser.getAttributeValue(null, "url"));
                            Log.d(LOG_TAG, "enclosure = " + news.getEnclosurejpg());
                            break;
                        case "category":
                            news.setCategory(parser.nextText());
                            newsList.add(news);
                            Log.d(LOG_TAG, "category = " + news.getCategory());
                            break;
                    }
                }
            }
            parser.next();
        }
        return newsList;
    }
}
