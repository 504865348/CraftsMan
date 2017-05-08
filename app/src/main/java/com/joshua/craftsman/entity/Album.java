package com.joshua.craftsman.entity;

import java.util.List;

/**
 * Created by nzz on 2017/4/28.
 * 专辑
 */

public class Album {
    private String albumID; //专辑ID
    private String albumImage; //图片
    private String title; //标题
    private String craftsmanName; //工匠名
    private int playTimes; //播放量
    private String classifyName; //分类名
    private String introduction; //专辑简介
    private List<String> arrayProgram; //节目列表(参见Program类)
    private int programNumber; //节目总数

    public String getAlbumID() {
        return albumID;
    }

    public void setAlbumID(String albumID) {
        this.albumID = albumID;
    }

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

    public String getClassifyName() {
        return classifyName;
    }

    public void setClassifyName(String classifyName) {
        this.classifyName = classifyName;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public List<String> getArrayProgram() {
        return arrayProgram;
    }

    public void setArrayProgram(List<String> arrayProgram) {
        this.arrayProgram = arrayProgram;
    }

    public int getProgramNumber() {
        return programNumber;
    }

    public void setProgramNumber(int programNumber) {
        this.programNumber = programNumber;
    }

    public Album(String albumID, String albumImage, String title, String craftsmanName, int playTimes, String classifyName, String introduction, List<String> arrayProgram, int programNumber) {
        this.albumID = albumID;
        this.albumImage = albumImage;
        this.title = title;
        this.craftsmanName = craftsmanName;
        this.playTimes = playTimes;
        this.classifyName = classifyName;
        this.introduction = introduction;
        this.arrayProgram = arrayProgram;
        this.programNumber = programNumber;
    }

    @Override
    public String toString() {
        return "Album{" +
                "albumID='" + albumID + '\'' +
                ", albumImage='" + albumImage + '\'' +
                ", title='" + title + '\'' +
                ", craftsmanName='" + craftsmanName + '\'' +
                ", playTimes=" + playTimes +
                ", classifyName='" + classifyName + '\'' +
                ", introduction='" + introduction + '\'' +
                ", arrayProgram=" + arrayProgram +
                ", programNumber=" + programNumber +
                '}';
    }
}
