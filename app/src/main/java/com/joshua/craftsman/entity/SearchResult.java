package com.joshua.craftsman.entity;

/**
 * Created by nzz on 2017/4/29.
 * 搜索结果
 */
public class SearchResult {
    /* 专辑 */
    private String albumImage; //专辑图片
    private String title; //标题
    private String albumIntroduction; //专辑简介
    private int playTimes; //播放量
    private int albumNumber; //搜索专辑所得数量
    /* 问答 */
    private String content; //问题内容
    private String time; //时长
    private int listenrNumber; //收听人数
    private int quesAnsNumber; //搜索问答所得数量
    /* 工匠 */
    private String craftsImage; //工匠图片
    private String craftsName; //账号名
    private String craftsIntroduction; //工匠简介
    private String craftsContent; //工匠详细介绍
    private int craftsNumber; //搜索工匠所得数量

    public String getAlbumImage() {
        return albumImage;
    }

    public void setAlbumImage(String albumImage) {
        this.albumImage = albumImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlbumIntroduction() {
        return albumIntroduction;
    }

    public void setAlbumIntroduction(String albumIntroduction) {
        this.albumIntroduction = albumIntroduction;
    }

    public int getPlayTimes() {
        return playTimes;
    }

    public void setPlayTimes(int playTimes) {
        this.playTimes = playTimes;
    }

    public int getAlbumNumber() {
        return albumNumber;
    }

    public void setAlbumNumber(int albumNumber) {
        this.albumNumber = albumNumber;
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

    public int getListenrNumber() {
        return listenrNumber;
    }

    public void setListenrNumber(int listenrNumber) {
        this.listenrNumber = listenrNumber;
    }

    public int getQuesAnsNumber() {
        return quesAnsNumber;
    }

    public void setQuesAnsNumber(int quesAnsNumber) {
        this.quesAnsNumber = quesAnsNumber;
    }

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

    public String getCraftsIntroduction() {
        return craftsIntroduction;
    }

    public void setCraftsIntroduction(String craftsIntroduction) {
        this.craftsIntroduction = craftsIntroduction;
    }

    public String getCraftsContent() {
        return craftsContent;
    }

    public void setCraftsContent(String craftsContent) {
        this.craftsContent = craftsContent;
    }

    public int getCraftsNumber() {
        return craftsNumber;
    }

    public void setCraftsNumber(int craftsNumber) {
        this.craftsNumber = craftsNumber;
    }

    public SearchResult(String albumImage, String title, String albumIntroduction, int playTimes, int albumNumber, String content, String time, int listenrNumber, int quesAnsNumber, String craftsImage, String craftsName, String craftsIntroduction, String craftsContent, int craftsNumber) {
        this.albumImage = albumImage;
        this.title = title;
        this.albumIntroduction = albumIntroduction;
        this.playTimes = playTimes;
        this.albumNumber = albumNumber;
        this.content = content;
        this.time = time;
        this.listenrNumber = listenrNumber;
        this.quesAnsNumber = quesAnsNumber;
        this.craftsImage = craftsImage;
        this.craftsName = craftsName;
        this.craftsIntroduction = craftsIntroduction;
        this.craftsContent = craftsContent;
        this.craftsNumber = craftsNumber;
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                "albumImage='" + albumImage + '\'' +
                ", title='" + title + '\'' +
                ", albumIntroduction='" + albumIntroduction + '\'' +
                ", playTimes=" + playTimes +
                ", albumNumber=" + albumNumber +
                ", content='" + content + '\'' +
                ", time='" + time + '\'' +
                ", listenrNumber=" + listenrNumber +
                ", quesAnsNumber=" + quesAnsNumber +
                ", craftsImage='" + craftsImage + '\'' +
                ", craftsName='" + craftsName + '\'' +
                ", craftsIntroduction='" + craftsIntroduction + '\'' +
                ", craftsContent='" + craftsContent + '\'' +
                ", craftsNumber=" + craftsNumber +
                '}';
    }
}
