package com.course.innopolis.lentaviewer;

/**
 * Created by Cristina on 20.07.2017.
 */

public class News {
    private String guid;
    private String title;
    private String link;
    private String description;
    private String pubDate;
    private String enclosurejpg;
    private String category;

    public News() {
    }

    public News(String guid, String title, String link, String description, String pubDate, String enclosurejpg, String category) {
        this.guid = guid;
        this.title = title;
        this.link = link;
        this.description = description;
        this.pubDate = pubDate;
        this.enclosurejpg = enclosurejpg;
        this.category = category;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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
