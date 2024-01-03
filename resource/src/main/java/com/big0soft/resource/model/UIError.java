package com.big0soft.resource.model;

public class UIError {
    public boolean error;

    public int image;
    public String title;
    public String description;

    public UIError() {

    }

    public UIError(int image, String title, String description) {
        this.image = image;
        this.title = title;
        this.description = description;
    }

    public UIError(boolean error, int image, String title, String description) {
        this.error = error;
        this.image = image;
        this.title = title;
        this.description = description;
    }
}
