package com.joshua.craftsman.entity;

/**
 * Created by nzz on 2017/4/29.
 *首页--工匠
 */

public class Craftsman {
    private String craftsmanID; //工匠ID
    private String imageUrl; //图片
    private String craftsmanName; //工匠名
    private String introduction; //工匠简介

    public String getCraftsmanID() {
        return craftsmanID;
    }

    public void setCraftsmanID(String craftsmanID) {
        this.craftsmanID = craftsmanID;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCraftsmanName() {
        return craftsmanName;
    }

    public void setCraftsmanName(String craftsmanName) {
        this.craftsmanName = craftsmanName;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Craftsman(String craftsmanID, String imageUrl, String craftsmanName, String introduction) {
        this.craftsmanID = craftsmanID;
        this.imageUrl = imageUrl;
        this.craftsmanName = craftsmanName;
        this.introduction = introduction;
    }

    @Override
    public String toString() {
        return "Craftsman{" +
                "craftsmanID='" + craftsmanID + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", craftsmanName='" + craftsmanName + '\'' +
                ", introduction='" + introduction + '\'' +
                '}';
    }
}
