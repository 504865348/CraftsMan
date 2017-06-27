package com.joshua.craftsman.entity;

/**
 * Created by nzz on 2017/6/5.
 * 首页-分类-机电工程
 */

public class ClassifyMechanical {
    private String mechanicalID; //机电工程ID
    private String programImage; //节目图片
    private String title; //标题
    private String introduction; //简介
    //private String downloadUrl; //下载地址

    public String getMechanicalID() {
        return mechanicalID;
    }

    public void setMechanicalID(String mechanicalID) {
        this.mechanicalID = mechanicalID;
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

    public ClassifyMechanical(String mechanicalID, String programImage, String title, String introduction) {
        this.mechanicalID = mechanicalID;
        this.programImage = programImage;
        this.title = title;
        this.introduction = introduction;
    }

    @Override
    public String toString() {
        return "ClassifyMechanical{" +
                "mechanicalID='" + mechanicalID + '\'' +
                ", programImage='" + programImage + '\'' +
                ", title='" + title + '\'' +
                ", introduction='" + introduction + '\'' +
                '}';
    }
}
