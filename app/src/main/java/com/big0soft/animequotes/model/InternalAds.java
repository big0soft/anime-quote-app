package com.big0soft.animequotes.model;

import com.google.gson.annotations.SerializedName;

public class InternalAds {
    @SerializedName(value = "ad_id")
    private int id;
    private String image;
    @SerializedName(value = "deep_link")
    private String deepLink;
    @SerializedName(value = "view_type")
    private int viewType;

    public InternalAds() {

    }

    public InternalAds(String image) {
        this.image = image;
    }

    public InternalAds(String image, String deepLink, int viewType) {
        this.image = image;
        this.deepLink = deepLink;
        this.viewType = viewType;
    }

    public String image() {
        return image;
    }

    public InternalAds setImage(String image) {
        this.image = image;
        return this;
    }

    public String deepLink() {
        return deepLink;
    }

    public InternalAds setDeepLink(String deepLink) {
        this.deepLink = deepLink;
        return this;
    }

    public int viewType() {
        return viewType;
    }

    public InternalAds setViewType(int viewType) {
        this.viewType = viewType;
        return this;
    }

    public int id() {
        return id;
    }

    public InternalAds setId(int id) {
        this.id = id;
        return this;
    }

    @Override
    public String toString() {
        return "Ads{" +
                "id=" + id +
                ", image='" + image + '\'' +
                ", deepLink='" + deepLink + '\'' +
                ", viewType=" + viewType +
                '}';
    }
}
