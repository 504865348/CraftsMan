package com.joshua.craftsman.entity;

/**
 * Created by nzz on 2017/6/5.
 * 首页-分类-铁路工程
 */

public class ClassifyRailWay {
    private String railWayID; //铁路工程ID
    private String programImage; //节目图片
    private String title; //标题
    private String introduction; //简介
    //private String downloadUrl; //下载地址

    public String getRailWayID() {
        return railWayID;
    }

    public void setRailWayID(String railWayID) {
        this.railWayID = railWayID;
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

    public ClassifyRailWay(String railWayID, String programImage, String title, String introduction) {
        this.railWayID = railWayID;
        this.programImage = programImage;
        this.title = title;
        this.introduction = introduction;
    }

    @Override
    public String toString() {
        return "ClassifyRailWay{" +
                "railWayID='" + railWayID + '\'' +
                ", programImage='" + programImage + '\'' +
                ", title='" + title + '\'' +
                ", introduction='" + introduction + '\'' +
                '}';
    }
}
