package com.mar2.feedter.model;

/**
 * Created by jmart on 11/05/2016.
 */
public class FilterObject {

    private String id;
    private String typeOfContent;
    private String media;
    private String category;
    private String subcategory;

    public FilterObject(String id, String typeOfContent, String media, String category, String subcategory) {
        this.id = id;
        this.typeOfContent = typeOfContent;
        this.media = media;
        this.category = category;
        this.subcategory = subcategory;
    }

    public String getId() {
        return id;
    }

    public String getTypeOfContent() {
        return typeOfContent;
    }

    public String getMedia() {
        return media;
    }

    public String getCategory() {
        return category;
    }

    public String getSubcategory() {
        return subcategory;
    }
}
