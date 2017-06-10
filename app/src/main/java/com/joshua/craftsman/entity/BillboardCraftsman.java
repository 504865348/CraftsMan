package com.joshua.craftsman.entity;

/**
 * Created by nzz on 2017/5/2.
 * 榜单-最大国工匠榜
 */

public class BillboardCraftsman {
    //private String craftsNameTop1;//排名第一工匠的名字
    //private String craftsNameTop2;//排名第二工匠的名字
    private String rankNumber;//工匠排名的名次
    private String craftsImg;//工匠头像
    private String craftsName;//工匠名字
    private String craftsIntroduction;//工匠简介

    public String getRankNumber() {
        return rankNumber;
    }

    public void setRankNumber(String rankNumber) {
        this.rankNumber = rankNumber;
    }

    public String getCraftsImg() {
        return craftsImg;
    }

    public void setCraftsImg(String craftsImg) {
        this.craftsImg = craftsImg;
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

    public BillboardCraftsman(String rankNumber, String craftsImg, String craftsName, String craftsIntroduction) {
        this.rankNumber = rankNumber;
        this.craftsImg = craftsImg;
        this.craftsName = craftsName;
        this.craftsIntroduction = craftsIntroduction;
    }

    @Override
    public String toString() {
        return "BillboardCraftsman{" +
                "rankNumber='" + rankNumber + '\'' +
                ", craftsImg='" + craftsImg + '\'' +
                ", craftsName='" + craftsName + '\'' +
                ", craftsIntroduction='" + craftsIntroduction + '\'' +
                '}';
    }
}
