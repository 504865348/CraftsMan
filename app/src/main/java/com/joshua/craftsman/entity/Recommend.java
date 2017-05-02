package com.joshua.craftsman.entity;


/**
 * Created by nzz on 2017/4/28.
 * 首页--推荐
 */
public class Recommend {
    private String programImage; //节目图片
    private String title; //标题
    private String introduction; //简介
    private String downloadUrl; //下载地址

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

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public Recommend(String programIamge, String title, String introduction, String downloadUrl) {
        this.programImage = programIamge;
        this.title = title;
        this.introduction = introduction;
        this.downloadUrl = downloadUrl;
    }

    @Override
    public String toString() {
        return "Recommend{" +
                "programImage='" + programImage + '\'' +
                ", title='" + title + '\'' +
                ", introduction='" + introduction + '\'' +
                ", downloadUrl='" + downloadUrl + '\'' +
                '}';
    }
}
