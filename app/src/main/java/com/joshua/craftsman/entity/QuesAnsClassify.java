package com.joshua.craftsman.entity;

/**
 * Created by nzz on 2017/5/1.
 * 问答分类--问答，工匠
 */

public class QuesAnsClassify {
    /* 问答 */
    private String craftsImage; //工匠头像
    private String craftsName; //工匠名
    private String introduction; //工匠简介
    private String content; //问题内容
    private String time; //音频时长
    private String listenrNumber; //收听人数
    /* 工匠--前3个属性同“问答” */
    private String details; //工匠详细介绍

    public String getCraftsImage() {
        return craftsImage;
    }

    public void setCraftsImage(String craftsImage) {
        this.craftsImage = craftsImage;
    }

    public String getCraftsName() {
        return craftsName;
    }

    public void setCraftsName(String craftsName) {
        this.craftsName = craftsName;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getListenrNumber() {
        return listenrNumber;
    }

    public void setListenrNumber(String listenrNumber) {
        this.listenrNumber = listenrNumber;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public QuesAnsClassify(String craftsImage, String craftsName, String introduction, String content, String time, String listenrNumber, String details) {
        this.craftsImage = craftsImage;
        this.craftsName = craftsName;
        this.introduction = introduction;
        this.content = content;
        this.time = time;
        this.listenrNumber = listenrNumber;
        this.details = details;
    }

    @Override
    public String toString() {
        return "QuesAnsClassify{" +
                "craftsImage='" + craftsImage + '\'' +
                ", craftsName='" + craftsName + '\'' +
                ", introduction='" + introduction + '\'' +
                ", content='" + content + '\'' +
                ", time='" + time + '\'' +
                ", listenrNumber='" + listenrNumber + '\'' +
                ", details='" + details + '\'' +
                '}';
    }
}
