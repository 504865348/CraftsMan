package com.joshua.craftsman.entity;

/**
 * Created by nzz on 2017/5/8.
 * 首页--热门--看利器
 */

public class HotLook {
    private String programID; //节目ID
    private String toAlbumID; //节目所属专辑ID
    private String imageUrl; //图片
    private String programName; //节目名
    private String weaponClassify; //该节目类型为“看利器”系列

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

    public String getWeaponClassify() {
        return weaponClassify;
    }

    public void setWeaponClassify(String weaponClassify) {
        this.weaponClassify = weaponClassify;
    }

    public HotLook(String programID, String toAlbumID, String imageUrl, String programName, String weaponClassify) {
        this.programID = programID;
        this.toAlbumID = toAlbumID;
        this.imageUrl = imageUrl;
        this.programName = programName;
        this.weaponClassify = weaponClassify;
    }

    @Override
    public String toString() {
        return "HotLook{" +
                "programID='" + programID + '\'' +
                ", toAlbumID='" + toAlbumID + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", programName='" + programName + '\'' +
                ", weaponClassify='" + weaponClassify + '\'' +
                '}';
    }
}
