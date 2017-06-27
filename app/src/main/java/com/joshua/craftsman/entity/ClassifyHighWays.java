package com.joshua.craftsman.entity;

/**
 * Created by nzz on 2017/6/5.
 * 首页-分类-公路
 */

public class ClassifyHighWays {
    private String highWaysID; //公路ID
    private String programImage; //节目图片
    private String title; //标题
    private String introduction; //简介
    //private String downloadUrl; //下载地址

    public String getHighWaysID() {
        return highWaysID;
    }

    public void setHighWaysID(String highWaysID) {
        this.highWaysID = highWaysID;
    }

    public String getProgramImage() {
        return programImage;
    }

    public void setProgramImage(String programImage) {
        this.programImage = programImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public ClassifyHighWays(String highWaysID, String programImage, String title, String introduction) {
        this.highWaysID = highWaysID;
        this.programImage = programImage;
        this.title = title;
        this.introduction = introduction;
    }

    @Override
    public String toString() {
        return "ClassifyHighWays{" +
                "highWaysID='" + highWaysID + '\'' +
                ", programImage='" + programImage + '\'' +
                ", title='" + title + '\'' +
                ", introduction='" + introduction + '\'' +
                '}';
    }
}
