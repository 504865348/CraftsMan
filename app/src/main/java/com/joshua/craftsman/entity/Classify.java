package com.joshua.craftsman.entity;

/**
 * Created by nzz on 2017/4/29.
 * 首页--分类
 */

public class Classify {
    private String houses; //房屋建筑
    private String municipal; //市政公用
    private String mechanical; //机电工程
    private String highWays; //公路
    private String waterConservancy; //水利水电
    private String railWay; //铁路工程
    private String mining; //矿业工程
    private String airport; //民航机场工程
    private String communications; //通信广电工程

    public String getHouses() {
        return houses;
    }

    public void setHouses(String houses) {
        this.houses = houses;
    }

    public String getMunicipal() {
        return municipal;
    }

    public void setMunicipal(String municipal) {
        this.municipal = municipal;
    }

    public String getMechanical() {
        return mechanical;
    }

    public void setMechanical(String mechanical) {
        this.mechanical = mechanical;
    }

    public String getHighWays() {
        return highWays;
    }

    public void setHighWays(String highWays) {
        this.highWays = highWays;
    }

    public String getWaterConservancy() {
        return waterConservancy;
    }

    public void setWaterConservancy(String waterConservancy) {
        this.waterConservancy = waterConservancy;
    }

    public String getRailWay() {
        return railWay;
    }

    public void setRailWay(String railWay) {
        this.railWay = railWay;
    }

    public String getMining() {
        return mining;
    }

    public void setMining(String mining) {
        this.mining = mining;
    }

    public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }

    public String getCommunications() {
        return communications;
    }

    public void setCommunications(String communications) {
        this.communications = communications;
    }

    public Classify(String houses, String municipal, String mechanical, String highWays, String waterConservancy, String railWay, String mining, String airport, String communications) {
        this.houses = houses;
        this.municipal = municipal;
        this.mechanical = mechanical;
        this.highWays = highWays;
        this.waterConservancy = waterConservancy;
        this.railWay = railWay;
        this.mining = mining;
        this.airport = airport;
        this.communications = communications;
    }

    @Override
    public String toString() {
        return "Classify{" +
                "houses='" + houses + '\'' +
                ", municipal='" + municipal + '\'' +
                ", mechanical='" + mechanical + '\'' +
                ", highWays='" + highWays + '\'' +
                ", waterConservancy='" + waterConservancy + '\'' +
                ", railWay='" + railWay + '\'' +
                ", mining='" + mining + '\'' +
                ", airport='" + airport + '\'' +
                ", communications='" + communications + '\'' +
                '}';
    }
}
