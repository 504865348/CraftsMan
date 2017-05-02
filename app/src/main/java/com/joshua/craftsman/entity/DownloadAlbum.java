package com.joshua.craftsman.entity;

/**
 * Created by nzz on 2017/5/1.
 * 下载专辑
 */

public class DownloadAlbum {
    private String image; //图片
    private String title; //标题
    private String craftsmanName; //工匠名
    private int programNumber; //分集数量
    private String storage; //存储容量

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCraftsmanName() {
        return craftsmanName;
    }

    public void setCraftsmanName(String craftsmanName) {
        this.craftsmanName = craftsmanName;
    }

    public int getProgramNumber() {
        return programNumber;
    }

    public void setProgramNumber(int programNumber) {
        this.programNumber = programNumber;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public DownloadAlbum(String image, String title, String craftsmanName, int programNumber, String storage) {
        this.image = image;
        this.title = title;
        this.craftsmanName = craftsmanName;
        this.programNumber = programNumber;
        this.storage = storage;
    }

    @Override
    public String toString() {
        return "DownloadAlbum{" +
                "image='" + image + '\'' +
                ", title='" + title + '\'' +
                ", craftsmanName='" + craftsmanName + '\'' +
                ", programNumber=" + programNumber +
                ", storage='" + storage + '\'' +
                '}';
    }
}
