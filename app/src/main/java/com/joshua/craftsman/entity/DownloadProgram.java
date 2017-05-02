package com.joshua.craftsman.entity;

/**
 * Created by nzz on 2017/5/1.
 * 下载节目
 */
public class DownloadProgram {
    private String image; //图片
    private String title; //标题
    private String craftsmanName; //工匠名
    private int playTimes; //播放量
    private String time; //时长
    private String storage; //存储容量

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCraftsmanName() {
        return craftsmanName;
    }

    public void setCraftsmanName(String craftsmanName) {
        this.craftsmanName = craftsmanName;
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

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public DownloadProgram(String image, String title, String craftsmanName, int playTimes, String time, String storage) {
        this.image = image;
        this.title = title;
        this.craftsmanName = craftsmanName;
        this.playTimes = playTimes;
        this.time = time;
        this.storage = storage;
    }

    @Override
    public String toString() {
        return "DownloadProgram{" +
                "image='" + image + '\'' +
                ", title='" + title + '\'' +
                ", craftsmanName='" + craftsmanName + '\'' +
                ", playTimes=" + playTimes +
                ", time='" + time + '\'' +
                ", storage='" + storage + '\'' +
                '}';
    }
}
