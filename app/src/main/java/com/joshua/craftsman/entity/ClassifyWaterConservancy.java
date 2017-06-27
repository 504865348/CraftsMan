package com.joshua.craftsman.entity;

/**
 * Created by nzz on 2017/6/5.
 * 首页-分类-水利水电
 */

public class ClassifyWaterConservancy {
    private String conservancyID; //水利水电ID
    private String programImage; //节目图片
    private String title; //标题
    private String introduction; //简介
    //private String downloadUrl; //下载地址

    public String getConservancyID() {
        return conservancyID;
    }

    public void setConservancyID(String conservancyID) {
        this.conservancyID = conservancyID;
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

    public ClassifyWaterConservancy(String conservancyID, String programImage, String title, String introduction) {
        this.conservancyID = conservancyID;
        this.programImage = programImage;
        this.title = title;
        this.introduction = introduction;
    }

    @Override
    public String toString() {
        return "ClassifyWaterConservancy{" +
                "conservancyID='" + conservancyID + '\'' +
                ", programImage='" + programImage + '\'' +
                ", title='" + title + '\'' +
                ", introduction='" + introduction + '\'' +
                '}';
    }
}