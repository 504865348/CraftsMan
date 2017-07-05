package com.joshua.craftsman.entity;

/**
 * Created by nzz on 2017/4/29.
 * 底部按钮--榜单(每个具体榜单的前2名)
 */

public class Billboard {
    private String proHotNameTop1;//最火节目飙升榜--排名第一的节目名字
    private String proHotNameTop2;//最火节目飙升榜--排名第二的节目名字
    private String proMoreNameTop1;//最多订阅经典榜--排名第一的节目名字
    private String proMoreNameTop2;//最多订阅经典榜--排名第二的节目名字
    private String proPayNameTop1;//付费精品飙升榜--排名第一的节目名字
    private String proPayNameTop2;//付费精品飙升榜--排名第二的节目名字
    private String craftsNameTop1;//最大国工匠榜--排名第一的工匠名字
    private String craftsNameTop2;//最大国工匠榜--排名第二的工匠名字

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

    public String getProMoreNameTop1() {
        return proMoreNameTop1;
    }

    public void setProMoreNameTop1(String proMoreNameTop1) {
        this.proMoreNameTop1 = proMoreNameTop1;
    }

    public String getProMoreNameTop2() {
        return proMoreNameTop2;
    }

    public void setProMoreNameTop2(String proMoreNameTop2) {
        this.proMoreNameTop2 = proMoreNameTop2;
    }

    public String getProPayNameTop1() {
        return proPayNameTop1;
    }

    public void setProPayNameTop1(String proPayNameTop1) {
        this.proPayNameTop1 = proPayNameTop1;
    }

    public String getProPayNameTop2() {
        return proPayNameTop2;
    }

    public void setProPayNameTop2(String proPayNameTop2) {
        this.proPayNameTop2 = proPayNameTop2;
    }

    public String getCraftsNameTop1() {
        return craftsNameTop1;
    }

    public void setCraftsNameTop1(String craftsNameTop1) {
        this.craftsNameTop1 = craftsNameTop1;
    }

    public String getCraftsNameTop2() {
        return craftsNameTop2;
    }

    public void setCraftsNameTop2(String craftsNameTop2) {
        this.craftsNameTop2 = craftsNameTop2;
    }

    public Billboard(String proHotNameTop1, String proHotNameTop2, String proMoreNameTop1, String proMoreNameTop2, String proPayNameTop1, String proPayNameTop2, String craftsNameTop1, String craftsNameTop2) {
        this.proHotNameTop1 = proHotNameTop1;
        this.proHotNameTop2 = proHotNameTop2;
        this.proMoreNameTop1 = proMoreNameTop1;
        this.proMoreNameTop2 = proMoreNameTop2;
        this.proPayNameTop1 = proPayNameTop1;
        this.proPayNameTop2 = proPayNameTop2;
        this.craftsNameTop1 = craftsNameTop1;
        this.craftsNameTop2 = craftsNameTop2;
    }

    @Override
    public String toString() {
        return "Billboard{" +
                "proHotNameTop1='" + proHotNameTop1 + '\'' +
                ", proHotNameTop2='" + proHotNameTop2 + '\'' +
                ", proMoreNameTop1='" + proMoreNameTop1 + '\'' +
                ", proMoreNameTop2='" + proMoreNameTop2 + '\'' +
                ", proPayNameTop1='" + proPayNameTop1 + '\'' +
                ", proPayNameTop2='" + proPayNameTop2 + '\'' +
                ", craftsNameTop1='" + craftsNameTop1 + '\'' +
                ", craftsNameTop2='" + craftsNameTop2 + '\'' +
                '}';
    }
}
