package com.joshua.craftsman.entity;

/**
 * Created by nzz on 2017/5/2.
 * 榜单-最大国工匠榜
 */

public class BillboardCraftsman {
    //private String rankNumber;//工匠排名的名次
    //private String craftsImg;//工匠头像
    //private String craftsName;//工匠名字
    //private String craftsIntroduction;//工匠简介
    private String id;//工匠排名的名次
    private String imageUrl;//工匠头像
    private String craftsmanName;//工匠名字
    private String introduction;//工匠简介

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

    public String getCraftsmanName() {
        return craftsmanName;
    }

    public void setCraftsmanName(String craftsmanName) {
        this.craftsmanName = craftsmanName;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public BillboardCraftsman(String id, String imageUrl, String craftsmanName, String introduction) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.craftsmanName = craftsmanName;
        this.introduction = introduction;
    }

    @Override
    public String toString() {
        return "BillboardCraftsman{" +
                "id='" + id + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", craftsmanName='" + craftsmanName + '\'' +
                ", introduction='" + introduction + '\'' +
                '}';
    }
}
