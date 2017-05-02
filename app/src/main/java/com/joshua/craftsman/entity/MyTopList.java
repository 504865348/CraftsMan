package com.joshua.craftsman.entity;

/**
 * Created by nzz on 2017/5/1.
 * 工匠用户--我的榜单
 */
public class MyTopList {
    /* 工匠榜单 */
    private int craftsRank; //工匠名次
    private String craftsImage; //工匠头像
    private String craftsName; //工匠名
    private String hotDegree; //热度
    private String fansNumber; //粉丝数
    /* 节目榜单
     * 最火节目飙升榜（按播放次数排序）
     * 最多订阅经典版（按订阅次数排序）
     * 付费精品飙升榜（按购买次数排序）
     * */
    private int programRank; //节目名次
    private String getCraftsName; //工匠名
    private String programName; //节目名

    public int getCraftsRank() {
        return craftsRank;
    }

    public void setCraftsRank(int craftsRank) {
        this.craftsRank = craftsRank;
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

    public String getHotDegree() {
        return hotDegree;
    }

    public void setHotDegree(String hotDegree) {
        this.hotDegree = hotDegree;
    }

    public String getFansNumber() {
        return fansNumber;
    }

    public void setFansNumber(String fansNumber) {
        this.fansNumber = fansNumber;
    }

    public int getProgramRank() {
        return programRank;
    }

    public void setProgramRank(int programRank) {
        this.programRank = programRank;
    }

    public String getGetCraftsName() {
        return getCraftsName;
    }

    public void setGetCraftsName(String getCraftsName) {
        this.getCraftsName = getCraftsName;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public MyTopList(int craftsRank, String craftsImage, String craftsName, String hotDegree, String fansNumber, int programRank, String getCraftsName, String programName) {
        this.craftsRank = craftsRank;
        this.craftsImage = craftsImage;
        this.craftsName = craftsName;
        this.hotDegree = hotDegree;
        this.fansNumber = fansNumber;
        this.programRank = programRank;
        this.getCraftsName = getCraftsName;
        this.programName = programName;
    }

    @Override
    public String toString() {
        return "MyTopList{" +
                "craftsRank=" + craftsRank +
                ", craftsImage='" + craftsImage + '\'' +
                ", craftsName='" + craftsName + '\'' +
                ", hotDegree='" + hotDegree + '\'' +
                ", fansNumber='" + fansNumber + '\'' +
                ", programRank=" + programRank +
                ", getCraftsName='" + getCraftsName + '\'' +
                ", programName='" + programName + '\'' +
                '}';
    }
}
