package com.joshua.craftsman.entity;

import android.graphics.Bitmap;

import java.util.Arrays;
import java.util.List;

/**
 * Created by nzz on 2017/4/28.
 */
//专辑：图片 标题 工匠名 播放量 分类名 节目数组 节目数量
public class AlbumInfo {

    private String albumImage;
    private String title;
    private String craftsmanName;
    private int playTimes;
    private String classifyName;
    private List<String> arrayProgram;
    private int programNumber;

    @Override
    public String toString() {
        return "AlbumInfo{" +
                "albumImage='" + albumImage + '\'' +
                ", title='" + title + '\'' +
                ", craftsmanName='" + craftsmanName + '\'' +
                ", playTimes=" + playTimes +
                ", classifyName='" + classifyName + '\'' +
                ", arrayProgram=" + arrayProgram +
                ", programNumber=" + programNumber +
                '}';
    }


    public AlbumInfo(String albumImage, String title, String craftsmanName, int playTimes, String classifyName, List<String> arrayProgram, int programNumber) {
        this.albumImage = albumImage;
        this.title = title;
        this.craftsmanName = craftsmanName;
        this.playTimes = playTimes;
        this.classifyName = classifyName;
        this.arrayProgram = arrayProgram;
        this.programNumber = programNumber;
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



}
