package com.joshua.craftsman.entity;

/**
 * Created by nzz on 2017/5/2.
 * 工匠用户--我的榜单--最火节目飙升榜(节目----按播放次数排序)
 */

public class MyTopListHot {
    private String proRank; //节目名次
    private String proName; //节目标题
    private String craftsName; //工匠名

    public String getProRank() {
        return proRank;
    }

    public void setProRank(String proRank) {
        this.proRank = proRank;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getCraftsName() {
        return craftsName;
    }

    public void setCraftsName(String craftsName) {
        this.craftsName = craftsName;
    }

    public MyTopListHot(String proRank, String proName, String craftsName) {
        this.proRank = proRank;
        this.proName = proName;
        this.craftsName = craftsName;
    }

    @Override
    public String toString() {
        return "MyTopListHot{" +
                "proRank='" + proRank + '\'' +
                ", proName='" + proName + '\'' +
                ", craftsName='" + craftsName + '\'' +
                '}';
    }
}
