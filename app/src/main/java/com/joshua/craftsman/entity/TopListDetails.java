package com.joshua.craftsman.entity;

/**
 * Created by nzz on 2017/5/1.
 * 榜单详情
 */

public class TopListDetails {
    private String topRank; //排序序号
    private String programImage; //节目图片
    private String title; //节目标题
    private String name; //作者名
    private String playUrl; //下载地址
    private String downloadUrl; //下载地址

    public String getTopRank() {
        return topRank;
    }

    public void setTopRank(String topRank) {
        this.topRank = topRank;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlayUrl() {
        return playUrl;
    }

    public void setPlayUrl(String playUrl) {
        this.playUrl = playUrl;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }

    public TopListDetails(String topRank, String programImage, String title, String name, String playUrl, String downloadUrl) {
        this.topRank = topRank;
        this.programImage = programImage;
        this.title = title;
        this.name = name;
        this.playUrl = playUrl;
        this.downloadUrl = downloadUrl;
    }

    @Override
    public String toString() {
        return "TopListDetails{" +
                "topRank='" + topRank + '\'' +
                ", programImage='" + programImage + '\'' +
                ", title='" + title + '\'' +
                ", name='" + name + '\'' +
                ", playUrl='" + playUrl + '\'' +
                ", downloadUrl='" + downloadUrl + '\'' +
                '}';
    }
}
