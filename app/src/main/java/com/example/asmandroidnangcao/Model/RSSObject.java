package com.example.asmandroidnangcao.Model;

import android.content.ClipData;

import java.util.ArrayList;
import java.util.List;

public class RSSObject {
    private String status;
    private Feed feed;
    private ArrayList<Item> items;

    public String getStatus() {
        return status;
    }

    public Feed getFeed() {
        return feed;
    }

    public ArrayList<Item> getItems() {
        return items;
    }
}
