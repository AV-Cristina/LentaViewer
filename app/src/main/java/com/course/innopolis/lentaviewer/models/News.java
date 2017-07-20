package com.course.innopolis.lentaviewer.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Cristina on 20.07.2017.
 */

public class News implements Parcelable{

    private String title;
    private String description;
    private String pubDate;
    private String enclosurejpg;
    private String category;

    public News() {
    }

    public News(String title, String description, String pubDate, String enclosurejpg, String category) {
        this.title = title;
        this.description = description;
        this.pubDate = pubDate;
        this.enclosurejpg = enclosurejpg;
        this.category = category;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    // Упаковка объекта в Parcel
    public void writeToParcel(Parcel parcel, int flag){
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeString(pubDate);
        parcel.writeString(enclosurejpg);
        parcel.writeString(category);
    }

    // Creator необходим, чтобы создавать новые объекты или массивы объектов
    public static final Parcelable.Creator<News> CREATOR = new Parcelable.Creator<News>() {
        public News createFromParcel(Parcel in) {
            return new News(in);
        }
        public News[] newArray(int size) {
            return new News[size];
        }
    };

    // Конструктор, считывающий данные из Parcel
    private News(Parcel parcel){
        title = parcel.readString();
        description = parcel.readString();
        pubDate = parcel.readString();
        enclosurejpg = parcel.readString();
        category = parcel.readString();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getEnclosurejpg() {
        return enclosurejpg;
    }

    public void setEnclosurejpg(String enclosurejpg) {
        this.enclosurejpg = enclosurejpg;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
