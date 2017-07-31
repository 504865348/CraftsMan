package com.joshua.craftsman.entity;


/**
 * Created by nzz on 2017/6/5.
 * 首页--分类
 * 通过参数获取具体分类的相关数据：参数-具体分类
 * houses-房屋建筑; municipal-市政公用; mechanical-机电工程
 * highWays-公路; waterConservancy-水利水电; railWay-铁路工程
 * mining-矿业工程; airport-民航机场工程; communications-通信广电工程
 */

public class Classify {
    private String albumImage; //图片
    private String title; //专辑标题
    private String craftsmanName; //工匠名
    private String introduction; //专辑简介
    private String model; //所属模块(匠心独运 讲政策 听专题 看利器)

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

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Classify(String albumImage, String title, String craftsmanName, String introduction, String model) {
        this.albumImage = albumImage;
        this.title = title;
        this.craftsmanName = craftsmanName;
        this.introduction = introduction;
        this.model = model;
    }

    @Override
    public String toString() {
        return "Classify{" +
                "albumImage='" + albumImage + '\'' +
                ", title='" + title + '\'' +
                ", craftsmanName='" + craftsmanName + '\'' +
                ", introduction='" + introduction + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}
