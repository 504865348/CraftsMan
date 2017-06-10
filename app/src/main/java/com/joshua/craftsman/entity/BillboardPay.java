package com.joshua.craftsman.entity;

/**
 * Created by nzz on 2017/5/2.
 * 榜单-付费精品飙升榜
 */

public class BillboardPay {
    //private String programNameTop1;//排名第一节目的名字
    //private String programNameTop2;//排名第二节目的名字
    private String rankNumber;//节目排名的名次
    private String programImg;//节目的缩略图或图片
    private String programTitle;//节目的标题
    private String authorName;//节目所属的作者名字
    //private String downloadUrl;//节目下载的Url地址

    public String getRankNumber() {
        return rankNumber;
    }

    public void setRankNumber(String rankNumber) {
        this.rankNumber = rankNumber;
    }

    public String getProgramImg() {
        return programImg;
    }

    public void setProgramImg(String programImg) {
        this.programImg = programImg;
    }

    public String getProgramTitle() {
        return programTitle;
    }

    public void setProgramTitle(String programTitle) {
        this.programTitle = programTitle;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public BillboardPay(String rankNumber, String programImg, String programTitle, String authorName) {
        this.rankNumber = rankNumber;
        this.programImg = programImg;
        this.programTitle = programTitle;
        this.authorName = authorName;
    }

    @Override
    public String toString() {
        return "BillboardPay{" +
                "rankNumber='" + rankNumber + '\'' +
                ", programImg='" + programImg + '\'' +
                ", programTitle='" + programTitle + '\'' +
                ", authorName='" + authorName + '\'' +
                '}';
    }
}
