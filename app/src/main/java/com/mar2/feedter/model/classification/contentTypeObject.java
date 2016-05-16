package com.mar2.feedter.model.classification;

/**
 * Created by jmart on 16/05/2016.
 */
public class contentTypeObject extends GenericClassificationObject {

    private String typeOfContent;


    public contentTypeObject(String id, String typeOfContent) {
        setId(id);
        this.typeOfContent = typeOfContent;

    }

    public String getTypeOfContent() {
        return typeOfContent;
    }



}
