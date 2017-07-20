package com.course.innopolis.lentaviewer;

import android.util.Log;
import android.util.Xml;

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

    final static String LOG_TAG = "myParseLogs";

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

    // парсим xml файл в список объектов News
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
                    if (parser.getName().equals("link")) {
                        news.setLink(parser.nextText());
                        Log.d(LOG_TAG, "link = " + news.getLink());
                    }

                    else if (parser.getName().equals("pubDate")) {
                        news.setPubDate(parser.nextText());
                        Log.d(LOG_TAG, "pubDate = " + news.getPubDate());
                    }

                    else if (parser.getName().equals("category")) {
                        //String value = parser.nextText().replace(",", ".");
                        news.setCategory(parser.nextText());
                        newsList.add(news);
                        Log.d(LOG_TAG, "category = " + news.getCategory());
                    }
                }
            }
            parser.next();
        }
        return newsList;
    }
}
