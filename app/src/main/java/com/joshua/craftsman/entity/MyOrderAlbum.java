package com.joshua.craftsman.entity;

/**
 * Created by nzz on 2017/5/1.
 * 我的订单--专辑
 */

public class MyOrderAlbum {
    private String albumOrderID; //专辑订单ID
    private String albumID; //专辑ID
    private String albumImage; //专辑图片
    private String albumTitle; //专辑标题
    private String craftsmanName; //工匠名
    private int programNumber; //分集数量
    private String albumPrice; //专辑价格

    public String getAlbumOrderID() {
        return albumOrderID;
    }

    public void setAlbumOrderID(String albumOrderID) {
        this.albumOrderID = albumOrderID;
    }

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

    public String getAlbumTitle() {
        return albumTitle;
    }

    public void setAlbumTitle(String albumTitle) {
        this.albumTitle = albumTitle;
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

    public String getAlbumPrice() {
        return albumPrice;
    }

    public void setAlbumPrice(String albumPrice) {
        this.albumPrice = albumPrice;
    }

    public MyOrderAlbum(String albumOrderID, String albumID, String albumImage, String albumTitle, String craftsmanName, int programNumber, String albumPrice) {
        this.albumOrderID = albumOrderID;
        this.albumID = albumID;
        this.albumImage = albumImage;
        this.albumTitle = albumTitle;
        this.craftsmanName = craftsmanName;
        this.programNumber = programNumber;
        this.albumPrice = albumPrice;
    }

    @Override
    public String toString() {
        return "MyOrderAlbum{" +
                "albumOrderID='" + albumOrderID + '\'' +
                ", albumID='" + albumID + '\'' +
                ", albumImage='" + albumImage + '\'' +
                ", albumTitle='" + albumTitle + '\'' +
                ", craftsmanName='" + craftsmanName + '\'' +
                ", programNumber=" + programNumber +
                ", albumPrice='" + albumPrice + '\'' +
                '}';
    }
}
