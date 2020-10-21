package com.example.asmandroidnangcao.Model;

import java.util.ArrayList;
import java.util.List;

public class Item {
    private String title;
    private String pubDate;
    private String link;
    private String guid;
    private String author;
    private String thumbnail;
    private String description;
    private String content;
    private Object enclosure;
    private ArrayList<String> categories;

    public String getTitle() {
        return title;
    }

    public String getPubDate() {
        return pubDate;
    }

    public String getLink() {
        return link;
    }

    public String getGuid() {
        return guid;
    }

    public String getAuthor() {
        return author;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getDescription() {
        return description;
    }

    public String getContent() {
        return content;
    }

    public Object getEnclosure() {
        return enclosure;
    }

    public ArrayList<String> getCategories() {
        return categories;
    }
}
