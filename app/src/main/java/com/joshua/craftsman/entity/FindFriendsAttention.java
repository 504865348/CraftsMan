package com.joshua.craftsman.entity;

/**
 * Created by nzz on 2017/5/1.
 * 找匠友--推荐关注
 */

public class FindFriendsAttention {
    private String craftsImage; //工匠头像
    private String craftsName; //工匠名
    private String introduction; //工匠简介

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

    public FindFriendsAttention(String craftsImage, String craftsName, String introduction) {
        this.craftsImage = craftsImage;
        this.craftsName = craftsName;
        this.introduction = introduction;
    }

    @Override
    public String toString() {
        return "FindFriendsAttention{" +
                "craftsImage='" + craftsImage + '\'' +
                ", craftsName='" + craftsName + '\'' +
                ", introduction='" + introduction + '\'' +
                '}';
    }
}
