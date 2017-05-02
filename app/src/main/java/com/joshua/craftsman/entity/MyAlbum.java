package com.joshua.craftsman.entity;

/**
 * Created by nzz on 2017/5/1.
 * 工匠用户--我的专辑
 */

public class MyAlbum {
    private String albumImage; //专辑图片
    private String albumTitle; //专辑标题
    private String introduction; //简介
    private int programNumber; //分集数量
    private String storage; //存储容量

    public String getAlbumImage() {
        return albumImage;
    }

    public void setAlbumImage(String albumImage) {
        this.albumImage = albumImage;
    }

    public String getAlbumTitle() {
        return albumTitle;
    }

    public void setAlbumTitle(String albumTitle) {
        this.albumTitle = albumTitle;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
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

    public MyAlbum(String albumImage, String albumTitle, String introduction, int programNumber, String storage) {
        this.albumImage = albumImage;
        this.albumTitle = albumTitle;
        this.introduction = introduction;
        this.programNumber = programNumber;
        this.storage = storage;
    }

    @Override
    public String toString() {
        return "MyAlbum{" +
                "albumImage='" + albumImage + '\'' +
                ", albumTitle='" + albumTitle + '\'' +
                ", introduction='" + introduction + '\'' +
                ", programNumber=" + programNumber +
                ", storage='" + storage + '\'' +
                '}';
    }
}
