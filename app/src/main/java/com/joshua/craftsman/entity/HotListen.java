package com.joshua.craftsman.entity;

/**
 * Created by nzz on 2017/5/8.
 * 首页--热门--听专题
 */

public class HotListen {
    private String id; //专辑ID
    private String imageUrl; //专辑封面
    private String programName; //专辑名
    private String author; //专辑所属工匠
    private String introduction; //专辑简介
    private String classify; //专辑所属分类
    private String play; //专辑下的节目播放量

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public String getPlay() {
        return play;
    }

    public void setPlay(String play) {
        this.play = play;
    }

    public HotListen(String id, String imageUrl, String programName, String author, String introduction, String classify, String play) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.programName = programName;
        this.author = author;
        this.introduction = introduction;
        this.classify = classify;
        this.play = play;
    }

    @Override
    public String toString() {
        return "HotListen{" +
                "id='" + id + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", programName='" + programName + '\'' +
                ", author='" + author + '\'' +
                ", introduction='" + introduction + '\'' +
                ", classify='" + classify + '\'' +
                ", play='" + play + '\'' +
                '}';
    }
}
