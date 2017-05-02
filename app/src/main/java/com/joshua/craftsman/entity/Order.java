package com.joshua.craftsman.entity;

/**
 * Created by nzz on 2017/5/1.
 * 我的订单
 */
public class Order {
    /* 专辑订单 */
    private String albumImage; //专辑图片
    private String albumTitle; //专辑标题
    private String craftsmanName; //工匠名
    private int programNumber; //分集数量
    private String albumPrice; //专辑价格
    /* 节目订单 */
    private String programImage; //节目图片
    private String programTitle; //节目标题
    private int playTimes; //播放量
    private long releaseTime; //发布时间
    private String programPrice; //节目价格

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

    public String getProgramImage() {
        return programImage;
    }

    public void setProgramImage(String programImage) {
        this.programImage = programImage;
    }

    public String getProgramTitle() {
        return programTitle;
    }

    public void setProgramTitle(String programTitle) {
        this.programTitle = programTitle;
    }

    public int getPlayTimes() {
        return playTimes;
    }

    public void setPlayTimes(int playTimes) {
        this.playTimes = playTimes;
    }

    public long getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(long releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getProgramPrice() {
        return programPrice;
    }

    public void setProgramPrice(String programPrice) {
        this.programPrice = programPrice;
    }

    public Order(String albumImage, String albumTitle, String craftsmanName, int programNumber, String albumPrice, String programImage, String programTitle, int playTimes, long releaseTime, String programPrice) {
        this.albumImage = albumImage;
        this.albumTitle = albumTitle;
        this.craftsmanName = craftsmanName;
        this.programNumber = programNumber;
        this.albumPrice = albumPrice;
        this.programImage = programImage;
        this.programTitle = programTitle;
        this.playTimes = playTimes;
        this.releaseTime = releaseTime;
        this.programPrice = programPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "albumImage='" + albumImage + '\'' +
                ", albumTitle='" + albumTitle + '\'' +
                ", craftsmanName='" + craftsmanName + '\'' +
                ", programNumber=" + programNumber +
                ", albumPrice='" + albumPrice + '\'' +
                ", programImage='" + programImage + '\'' +
                ", programTitle='" + programTitle + '\'' +
                ", playTimes=" + playTimes +
                ", releaseTime=" + releaseTime +
                ", programPrice='" + programPrice + '\'' +
                '}';
    }
}
