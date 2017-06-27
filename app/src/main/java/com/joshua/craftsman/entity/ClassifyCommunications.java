package com.joshua.craftsman.entity;

/**
 * Created by nzz on 2017/6/5.
 * 首页-分类-通信广电工程
 */

public class ClassifyCommunications {
    private String communicationsID; //通信广电工程ID
    private String programImage; //节目图片
    private String title; //标题
    private String introduction; //简介
    //private String downloadUrl; //下载地址

    public String getCommunicationsID() {
        return communicationsID;
    }

    public void setCommunicationsID(String communicationsID) {
        this.communicationsID = communicationsID;
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

    public ClassifyCommunications(String communicationsID, String programImage, String title, String introduction) {
        this.communicationsID = communicationsID;
        this.programImage = programImage;
        this.title = title;
        this.introduction = introduction;
    }

    @Override
    public String toString() {
        return "ClassifyCommunications{" +
                "communicationsID='" + communicationsID + '\'' +
                ", programImage='" + programImage + '\'' +
                ", title='" + title + '\'' +
                ", introduction='" + introduction + '\'' +
                '}';
    }
}
