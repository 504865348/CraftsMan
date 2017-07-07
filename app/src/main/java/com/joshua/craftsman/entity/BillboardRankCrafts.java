package com.joshua.craftsman.entity;

/**
 * Created by nzz on 2017/5/29.
 * 底部按钮--榜单--工匠榜单排名(前2名):
 * 最大国工匠榜
 */

public class BillboardRankCrafts {
    private String craftsBillboardImg;//工匠榜单图片
    private String craftsBillboardTitle;//工匠榜单标题
    private String craftsTop1;//排名第一工匠的序号1
    private String craftsTop2;//排名第二工匠的序号2
    private String craftsNameTop1;//排名第一工匠的名字
    private String craftsNameTop2;//排名第二工匠的名字

    public String getCraftsBillboardImg() {
        return craftsBillboardImg;
    }

    public void setCraftsBillboardImg(String craftsBillboardImg) {
        this.craftsBillboardImg = craftsBillboardImg;
    }

    public String getCraftsBillboardTitle() {
        return craftsBillboardTitle;
    }

    public void setCraftsBillboardTitle(String craftsBillboardTitle) {
        this.craftsBillboardTitle = craftsBillboardTitle;
    }

    public String getCraftsTop1() {
        return craftsTop1;
    }

    public void setCraftsTop1(String craftsTop1) {
        this.craftsTop1 = craftsTop1;
    }

    public String getCraftsTop2() {
        return craftsTop2;
    }

    public void setCraftsTop2(String craftsTop2) {
        this.craftsTop2 = craftsTop2;
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

    public BillboardRankCrafts(String craftsBillboardImg, String craftsBillboardTitle, String craftsTop1, String craftsTop2, String craftsNameTop1, String craftsNameTop2) {
        this.craftsBillboardImg = craftsBillboardImg;
        this.craftsBillboardTitle = craftsBillboardTitle;
        this.craftsTop1 = craftsTop1;
        this.craftsTop2 = craftsTop2;
        this.craftsNameTop1 = craftsNameTop1;
        this.craftsNameTop2 = craftsNameTop2;
    }

    @Override
    public String toString() {
        return "BillboardRankCrafts{" +
                "craftsBillboardImg='" + craftsBillboardImg + '\'' +
                ", craftsBillboardTitle='" + craftsBillboardTitle + '\'' +
                ", craftsTop1='" + craftsTop1 + '\'' +
                ", craftsTop2='" + craftsTop2 + '\'' +
                ", craftsNameTop1='" + craftsNameTop1 + '\'' +
                ", craftsNameTop2='" + craftsNameTop2 + '\'' +
                '}';
    }
}
