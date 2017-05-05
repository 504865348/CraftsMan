package com.joshua.craftsman.entity;

/**
 * Created by nzz on 2017/4/28.
 * 节目
 */

public class Program {
    private String programImage; //图片
    private String title; //标题
    private int playTimes; //播放量
    private String time; //时长
    private int commentTimes; //评论次数
    private long releaseTime; //发布时间
    private String playUrl; //播放地址
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

    public int getPlayTimes() {
        return playTimes;
    }

    public void setPlayTimes(int playTimes) {
        this.playTimes = playTimes;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getCommentTimes() {
        return commentTimes;
    }

    public void setCommentTimes(int commentTimes) {
        this.commentTimes = commentTimes;
    }

    public long getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(long releaseTime) {
        this.releaseTime = releaseTime;
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

    public Program(String programImage, String title, int playTimes, String time, int commentTimes, long releaseTime, String playUrl, String downloadUrl) {
        this.programImage = programImage;
        this.title = title;
        this.playTimes = playTimes;
        this.time = time;
        this.commentTimes = commentTimes;
        this.releaseTime = releaseTime;
        this.playUrl = playUrl;
        this.downloadUrl = downloadUrl;
    }

    @Override
    public String toString() {
        return "Program{" +
                "programImage='" + programImage + '\'' +
                ", title='" + title + '\'' +
                ", playTimes=" + playTimes +
                ", time='" + time + '\'' +
                ", commentTimes=" + commentTimes +
                ", releaseTime=" + releaseTime +
                ", playUrl='" + playUrl + '\'' +
                ", downloadUrl='" + downloadUrl + '\'' +
                '}';
    }
}
