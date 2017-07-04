package com.joshua.craftsman.entity;

/**
 * Created by nzz on 2017/5/1.
 * 我的订单--节目
 */

public class MyOrderProgram {
    private String programOrderID; //节目订单ID
    private String programID; //节目ID
    private String idAlbum; //所属专辑编号----根据该编号可得所属专辑信息
    private String programImage; //节目图片
    private String programTitle; //节目标题
    private int playTimes; //播放量
    private long releaseTime; //发布时间
    private String programPrice; //节目价格

    public String getProgramOrderID() {
        return programOrderID;
    }

    public void setProgramOrderID(String programOrderID) {
        this.programOrderID = programOrderID;
    }

    public String getProgramID() {
        return programID;
    }

    public void setProgramID(String programID) {
        this.programID = programID;
    }

    public String getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(String idAlbum) {
        this.idAlbum = idAlbum;
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

    public MyOrderProgram(String programOrderID, String programID, String idAlbum, String programImage, String programTitle, int playTimes, long releaseTime, String programPrice) {
        this.programOrderID = programOrderID;
        this.programID = programID;
        this.idAlbum = idAlbum;
        this.programImage = programImage;
        this.programTitle = programTitle;
        this.playTimes = playTimes;
        this.releaseTime = releaseTime;
        this.programPrice = programPrice;
    }

    @Override
    public String toString() {
        return "MyOrderProgram{" +
                "programOrderID='" + programOrderID + '\'' +
                ", programID='" + programID + '\'' +
                ", idAlbum='" + idAlbum + '\'' +
                ", programImage='" + programImage + '\'' +
                ", programTitle='" + programTitle + '\'' +
                ", playTimes=" + playTimes +
                ", releaseTime=" + releaseTime +
                ", programPrice='" + programPrice + '\'' +
                '}';
    }
}
