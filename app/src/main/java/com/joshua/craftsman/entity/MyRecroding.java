package com.joshua.craftsman.entity;

/**
 * Created by nzz on 2017/5/1.
 * 工匠用户--我的录制
 */
public class MyRecroding {
    private String recrodingImage; //录制视频或音频的图片
    private String name; //录制人名字
    private String time; //录制时间(xx年xx月xx日 xx时xx分)
    private String duration; //视频/音频时长
    private String storageUrl; //存储地址

    public String getRecrodingImage() {
        return recrodingImage;
    }

    public void setRecrodingImage(String recrodingImage) {
        this.recrodingImage = recrodingImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getStorageUrl() {
        return storageUrl;
    }

    public void setStorageUrl(String storageUrl) {
        this.storageUrl = storageUrl;
    }

    public MyRecroding(String recrodingImage, String name, String time, String duration, String storageUrl) {
        this.recrodingImage = recrodingImage;
        this.name = name;
        this.time = time;
        this.duration = duration;
        this.storageUrl = storageUrl;
    }

    @Override
    public String toString() {
        return "MyRecroding{" +
                "recrodingImage='" + recrodingImage + '\'' +
                ", name='" + name + '\'' +
                ", time='" + time + '\'' +
                ", duration='" + duration + '\'' +
                ", storageUrl='" + storageUrl + '\'' +
                '}';
    }
}
