package com.joshua.craftsman.entity;

/**
 * Created by nzz on 2017/4/29.
 * 底部按钮--榜单(每个具体榜单的前2名)
 */

public class Billboard {
    /*private String proHotNameTop1;//最火节目飙升榜--排名第一的节目名字
    private String proHotNameTop2;//最火节目飙升榜--排名第二的节目名字
    private String proMoreNameTop1;//最多订阅经典榜--排名第一的节目名字
    private String proMoreNameTop2;//最多订阅经典榜--排名第二的节目名字
    private String proPayNameTop1;//付费精品飙升榜--排名第一的节目名字
    private String proPayNameTop2;//付费精品飙升榜--排名第二的节目名字
    private String craftsNameTop1;//最大国工匠榜--排名第一的工匠名字
    private String craftsNameTop2;//最大国工匠榜--排名第二的工匠名字*/

    /* 从服务器拿到的字段 */
    private String proHotNameTop1;
    private String proHotNameTop2;
    private String proHotNameTop3;
    private String proHotNameTop4;
    private String proHotNameTop5;
    private String proHotNameTop6;
    private String proHotNameTop7;
    private String proHotNameTop8;

    public String getProHotNameTop1() {
        return proHotNameTop1;
    }

    public void setProHotNameTop1(String proHotNameTop1) {
        this.proHotNameTop1 = proHotNameTop1;
    }

    public String getProHotNameTop2() {
        return proHotNameTop2;
    }

    public void setProHotNameTop2(String proHotNameTop2) {
        this.proHotNameTop2 = proHotNameTop2;
    }

    public String getProHotNameTop3() {
        return proHotNameTop3;
    }

    public void setProHotNameTop3(String proHotNameTop3) {
        this.proHotNameTop3 = proHotNameTop3;
    }

    public String getProHotNameTop4() {
        return proHotNameTop4;
    }

    public void setProHotNameTop4(String proHotNameTop4) {
        this.proHotNameTop4 = proHotNameTop4;
    }

    public String getProHotNameTop5() {
        return proHotNameTop5;
    }

    public void setProHotNameTop5(String proHotNameTop5) {
        this.proHotNameTop5 = proHotNameTop5;
    }

    public String getProHotNameTop6() {
        return proHotNameTop6;
    }

    public void setProHotNameTop6(String proHotNameTop6) {
        this.proHotNameTop6 = proHotNameTop6;
    }

    public String getProHotNameTop7() {
        return proHotNameTop7;
    }

    public void setProHotNameTop7(String proHotNameTop7) {
        this.proHotNameTop7 = proHotNameTop7;
    }

    public String getProHotNameTop8() {
        return proHotNameTop8;
    }

    public void setProHotNameTop8(String proHotNameTop8) {
        this.proHotNameTop8 = proHotNameTop8;
    }

    public Billboard(String proHotNameTop1, String proHotNameTop2, String proHotNameTop3, String proHotNameTop4, String proHotNameTop5, String proHotNameTop6, String proHotNameTop7, String proHotNameTop8) {
        this.proHotNameTop1 = proHotNameTop1;
        this.proHotNameTop2 = proHotNameTop2;
        this.proHotNameTop3 = proHotNameTop3;
        this.proHotNameTop4 = proHotNameTop4;
        this.proHotNameTop5 = proHotNameTop5;
        this.proHotNameTop6 = proHotNameTop6;
        this.proHotNameTop7 = proHotNameTop7;
        this.proHotNameTop8 = proHotNameTop8;
    }

    @Override
    public String toString() {
        return "Billboard{" +
                "proHotNameTop1='" + proHotNameTop1 + '\'' +
                ", proHotNameTop2='" + proHotNameTop2 + '\'' +
                ", proHotNameTop3='" + proHotNameTop3 + '\'' +
                ", proHotNameTop4='" + proHotNameTop4 + '\'' +
                ", proHotNameTop5='" + proHotNameTop5 + '\'' +
                ", proHotNameTop6='" + proHotNameTop6 + '\'' +
                ", proHotNameTop7='" + proHotNameTop7 + '\'' +
                ", proHotNameTop8='" + proHotNameTop8 + '\'' +
                '}';
    }
}
