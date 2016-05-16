package com.mar2.feedter.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by jmart on 08/05/2016.
 */
public class EntryObject {

    @SerializedName("id")
    private String id;
    @SerializedName("link")
    private String link;
    @SerializedName("title")
    private String title;
    @SerializedName("shortEntry")
    private String shortEntry;
    @SerializedName("photoLink")
    private String photoLink;
    @SerializedName("dateTime")
    private String dateTime;

    public EntryObject(String id, String link, String title, String shortEntry, String photoLink, String dateTime) {
        this.id = id;
        this.link = link;
        this.title = title;
        this.shortEntry = shortEntry;
        this.photoLink = photoLink;
        this.dateTime = dateTime;
    }
    public EntryObject(){

    }

    public String getId() {
        return id;
    }

    public String getLink() {
        return link;
    }

    public String getTitle() {
        return title;
    }

    public String getShortEntry() {
        return shortEntry;
    }

    public String getPhotoLink() {
        return photoLink;
    }

    public String getDateTime() {
        return dateTime;
    }
}
