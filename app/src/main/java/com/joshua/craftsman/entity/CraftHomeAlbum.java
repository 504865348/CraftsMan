package com.joshua.craftsman.entity;

/**
 * Created by nzz on 2017/7/22.
 * 工匠主页--专辑
 */

public class CraftHomeAlbum {
    private String albumId; //专辑ID
    private String albumImage; //图片
    private String title; //标题
    private String classifyName; //分类名
    private String intro; //简介

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
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

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public CraftHomeAlbum(String albumId, String albumImage, String title, String classifyName, String intro) {
        this.albumId = albumId;
        this.albumImage = albumImage;
        this.title = title;
        this.classifyName = classifyName;
        this.intro = intro;
    }

    @Override
    public String toString() {
        return "CraftHomeAlbum{" +
                "albumId='" + albumId + '\'' +
                ", albumImage='" + albumImage + '\'' +
                ", title='" + title + '\'' +
                ", classifyName='" + classifyName + '\'' +
                ", intro='" + intro + '\'' +
                '}';
    }
}
