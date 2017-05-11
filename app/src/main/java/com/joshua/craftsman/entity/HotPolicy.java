package com.joshua.craftsman.entity;

/**
 * Created by nzz on 2017/5/8.
 * 首页--热门--讲政策
 */

public class HotPolicy {
    private String programID; //节目ID
    private String toAlbumID; //节目所属专辑ID
    private String imageUrl; //图片
    private String programName; //节目名
    private String policyClassify; //该节目类型为“讲政策”系列

    public String getProgramID() {
        return programID;
    }

    public void setProgramID(String programID) {
        this.programID = programID;
    }

    public String getToAlbumID() {
        return toAlbumID;
    }

    public void setToAlbumID(String toAlbumID) {
        this.toAlbumID = toAlbumID;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getPolicyClassify() {
        return policyClassify;
    }

    public void setPolicyClassify(String policyClassify) {
        this.policyClassify = policyClassify;
    }

    public HotPolicy(String programID, String toAlbumID, String imageUrl, String programName, String policyClassify) {
        this.programID = programID;
        this.toAlbumID = toAlbumID;
        this.imageUrl = imageUrl;
        this.programName = programName;
        this.policyClassify = policyClassify;
    }

    @Override
    public String toString() {
        return "HotPolicy{" +
                "programID='" + programID + '\'' +
                ", toAlbumID='" + toAlbumID + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", programName='" + programName + '\'' +
                ", policyClassify='" + policyClassify + '\'' +
                '}';
    }
}
