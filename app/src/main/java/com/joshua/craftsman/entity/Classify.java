package com.joshua.craftsman.entity;

import java.util.List;

/**
 * Created by nzz on 2017/4/29.
 * 首页--分类
 */

public class Classify {
    private List<String> houses; //房屋建筑
    private List<String> municipal; //市政公用
    private List<String> mechanical; //机电工程
    private List<String> highWays; //公路
    private List<String> waterConservancy; //水利水电
    private List<String> railWay; //铁路工程
    private List<String> mining; //矿业工程
    private List<String> airport; //民航机场工程
    private List<String> communications; //通信广电工程

    public List<String> getHouses() {
        return houses;
    }

    public void setHouses(List<String> houses) {
        this.houses = houses;
    }

    public List<String> getMunicipal() {
        return municipal;
    }

    public void setMunicipal(List<String> municipal) {
        this.municipal = municipal;
    }

    public List<String> getMechanical() {
        return mechanical;
    }

    public void setMechanical(List<String> mechanical) {
        this.mechanical = mechanical;
    }

    public List<String> getHighWays() {
        return highWays;
    }

    public void setHighWays(List<String> highWays) {
        this.highWays = highWays;
    }

    public List<String> getWaterConservancy() {
        return waterConservancy;
    }

    public void setWaterConservancy(List<String> waterConservancy) {
        this.waterConservancy = waterConservancy;
    }

    public List<String> getRailWay() {
        return railWay;
    }

    public void setRailWay(List<String> railWay) {
        this.railWay = railWay;
    }

    public List<String> getMining() {
        return mining;
    }

    public void setMining(List<String> mining) {
        this.mining = mining;
    }

    public List<String> getAirport() {
        return airport;
    }

    public void setAirport(List<String> airport) {
        this.airport = airport;
    }

    public List<String> getCommunications() {
        return communications;
    }

    public void setCommunications(List<String> communications) {
        this.communications = communications;
    }

    public Classify(List<String> houses, List<String> municipal, List<String> mechanical, List<String> highWays, List<String> waterConservancy, List<String> railWay, List<String> mining, List<String> airport, List<String> communications) {
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
                "houses=" + houses +
                ", municipal=" + municipal +
                ", mechanical=" + mechanical +
                ", highWays=" + highWays +
                ", waterConservancy=" + waterConservancy +
                ", railWay=" + railWay +
                ", mining=" + mining +
                ", airport=" + airport +
                ", communications=" + communications +
                '}';
    }
}
