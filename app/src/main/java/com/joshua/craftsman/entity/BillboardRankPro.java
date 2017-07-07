package com.joshua.craftsman.entity;

/**
 * Created by nzz on 2017/5/29.
 * 底部按钮--榜单--节目榜单排名(每个具体榜单的前2名):
 * 最火节目飙升榜;最多订阅经典榜;付费精品飙升榜
 */

public class BillboardRankPro {
    private String proBillboardImg;//节目榜单图片
    private String proBillboardTitle;//节目榜单标题
    private String proTop1;//排名第一节目的序号1
    private String proTop2;//排名第二节目的序号2
    private String proNameTop1;//排名第一节目的名字
    private String proNameTop2;//排名第二节目的名字

    public String getProBillboardImg() {
        return proBillboardImg;
    }

    public void setProBillboardImg(String proBillboardImg) {
        this.proBillboardImg = proBillboardImg;
    }

    public String getProBillboardTitle() {
        return proBillboardTitle;
    }

    public void setProBillboardTitle(String proBillboardTitle) {
        this.proBillboardTitle = proBillboardTitle;
    }

    public String getProTop1() {
        return proTop1;
    }

    public void setProTop1(String proTop1) {
        this.proTop1 = proTop1;
    }

    public String getProTop2() {
        return proTop2;
    }

    public void setProTop2(String proTop2) {
        this.proTop2 = proTop2;
    }

    public String getProNameTop1() {
        return proNameTop1;
    }

    public void setProNameTop1(String proNameTop1) {
        this.proNameTop1 = proNameTop1;
    }

    public String getProNameTop2() {
        return proNameTop2;
    }

    public void setProNameTop2(String proNameTop2) {
        this.proNameTop2 = proNameTop2;
    }

    public BillboardRankPro(String proBillboardImg, String proBillboardTitle, String proTop1, String proTop2, String proNameTop1, String proNameTop2) {
        this.proBillboardImg = proBillboardImg;
        this.proBillboardTitle = proBillboardTitle;
        this.proTop1 = proTop1;
        this.proTop2 = proTop2;
        this.proNameTop1 = proNameTop1;
        this.proNameTop2 = proNameTop2;
    }

    @Override
    public String toString() {
        return "BillboardRankPro{" +
                "proBillboardImg='" + proBillboardImg + '\'' +
                ", proBillboardTitle='" + proBillboardTitle + '\'' +
                ", proTop1='" + proTop1 + '\'' +
                ", proTop2='" + proTop2 + '\'' +
                ", proNameTop1='" + proNameTop1 + '\'' +
                ", proNameTop2='" + proNameTop2 + '\'' +
                '}';
    }
}
