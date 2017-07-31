package com.joshua.craftsman.entity;

/**
 * Created by nzz on 2017/5/1.
 * 工匠用户--我的榜单--工匠榜单
 */

public class MyTopListCrafts {
    private String craftsRank; //工匠名次
    private String craftsImage; //工匠头像
    private String craftsName; //工匠名
    private String hotDegree; //热度(回答问题的个数)
    private String fansNumber; //粉丝数

    public String getCraftsRank() {
        return craftsRank;
    }

    public void setCraftsRank(String craftsRank) {
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

    public MyTopListCrafts(String craftsRank, String craftsImage, String craftsName, String hotDegree, String fansNumber) {
        this.craftsRank = craftsRank;
        this.craftsImage = craftsImage;
        this.craftsName = craftsName;
        this.hotDegree = hotDegree;
        this.fansNumber = fansNumber;
    }

    @Override
    public String toString() {
        return "MyTopListCrafts{" +
                "craftsRank='" + craftsRank + '\'' +
                ", craftsImage='" + craftsImage + '\'' +
                ", craftsName='" + craftsName + '\'' +
                ", hotDegree='" + hotDegree + '\'' +
                ", fansNumber='" + fansNumber + '\'' +
                '}';
    }
}
