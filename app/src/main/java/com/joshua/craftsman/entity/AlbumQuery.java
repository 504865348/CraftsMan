package com.joshua.craftsman.entity;

/**
 * Created by nzz on 2017/7/18.
 */

public class AlbumQuery {
    private String albumID;
    private String albumImage; //专辑图片
    private String title; //标题
    private String classifyName; //专辑所属分类
    private String craftsmanName; //工匠名
    private String introduction; //专辑简介

    public String getAlbumID() {
        return albumID;
    }

    public void setAlbumID(String albumID) {
        this.albumID = albumID;
    }

    public String getAlbumImage() {
        return albumImage;
    }

    public void setAlbumImage(String albumImage) {
        this.albumImage = albumImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
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

    public AlbumQuery(String albumID, String albumImage, String title, String classifyName, String craftsmanName, String introduction) {
        this.albumID = albumID;
        this.albumImage = albumImage;
        this.title = title;
        this.classifyName = classifyName;
        this.craftsmanName = craftsmanName;
        this.introduction = introduction;
    }

    @Override
    public String toString() {
        return "AlbumQuery{" +
                "albumID='" + albumID + '\'' +
                ", albumImage='" + albumImage + '\'' +
                ", title='" + title + '\'' +
                ", classifyName='" + classifyName + '\'' +
                ", craftsmanName='" + craftsmanName + '\'' +
                ", introduction='" + introduction + '\'' +
                '}';
    }
}
