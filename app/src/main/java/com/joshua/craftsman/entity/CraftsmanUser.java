package com.joshua.craftsman.entity;

import java.util.List;

/**
 * Created by nzz on 2017/4/28.
 * 工匠用户
 */
public class CraftsmanUser {
    private String headImage; //头像
    private String phoneNumber; //手机号
    private String password; //密码
    private String CraftsmanName; //工匠名
    private int concernNumber; //关注数量
    private int fansNumber; //粉丝数量
    private List<String> subscription; //订阅列表(订阅的是专辑，参见DownloadAlbum类)
    private List<String> collection; //收藏列表(收藏的是节目，参见DownloadProgram类)
    private List<String> order; //我的订单(参见Order类)
    private List<String> craftsmanCoins; //我的匠币(参见CraftsCoins类)
    private List<String> craftsQuesAns; //我的问答(参见CraftsQuesAns类)
    private List<String> myAlbum; //我的专辑(参见MyAlbum类)
    private List<String> myTopList; //我的榜单(参见MyTopList类)
    private List<String> myRecroding; //我的录制(参见MyRecroding类)

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCraftsmanName() {
        return CraftsmanName;
    }

    public void setCraftsmanName(String craftsmanName) {
        CraftsmanName = craftsmanName;
    }

    public int getConcernNumber() {
        return concernNumber;
    }

    public void setConcernNumber(int concernNumber) {
        this.concernNumber = concernNumber;
    }

    public int getFansNumber() {
        return fansNumber;
    }

    public void setFansNumber(int fansNumber) {
        this.fansNumber = fansNumber;
    }

    public List<String> getSubscription() {
        return subscription;
    }

    public void setSubscription(List<String> subscription) {
        this.subscription = subscription;
    }

    public List<String> getCollection() {
        return collection;
    }

    public void setCollection(List<String> collection) {
        this.collection = collection;
    }

    public List<String> getOrder() {
        return order;
    }

    public void setOrder(List<String> order) {
        this.order = order;
    }

    public List<String> getCraftsmanCoins() {
        return craftsmanCoins;
    }

    public void setCraftsmanCoins(List<String> craftsmanCoins) {
        this.craftsmanCoins = craftsmanCoins;
    }

    public List<String> getCraftsQuesAns() {
        return craftsQuesAns;
    }

    public void setCraftsQuesAns(List<String> craftsQuesAns) {
        this.craftsQuesAns = craftsQuesAns;
    }

    public List<String> getMyAlbum() {
        return myAlbum;
    }

    public void setMyAlbum(List<String> myAlbum) {
        this.myAlbum = myAlbum;
    }

    public List<String> getMyTopList() {
        return myTopList;
    }

    public void setMyTopList(List<String> myTopList) {
        this.myTopList = myTopList;
    }

    public List<String> getMyRecroding() {
        return myRecroding;
    }

    public void setMyRecroding(List<String> myRecroding) {
        this.myRecroding = myRecroding;
    }

    public CraftsmanUser(String headImage, String phoneNumber, String password, String craftsmanName, int concernNumber, int fansNumber, List<String> subscription, List<String> collection, List<String> order, List<String> craftsmanCoins, List<String> craftsQuesAns, List<String> myAlbum, List<String> myTopList, List<String> myRecroding) {
        this.headImage = headImage;
        this.phoneNumber = phoneNumber;
        this.password = password;
        CraftsmanName = craftsmanName;
        this.concernNumber = concernNumber;
        this.fansNumber = fansNumber;
        this.subscription = subscription;
        this.collection = collection;
        this.order = order;
        this.craftsmanCoins = craftsmanCoins;
        this.craftsQuesAns = craftsQuesAns;
        this.myAlbum = myAlbum;
        this.myTopList = myTopList;
        this.myRecroding = myRecroding;
    }

    @Override
    public String toString() {
        return "CraftsmanUser{" +
                "headImage='" + headImage + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", CraftsmanName='" + CraftsmanName + '\'' +
                ", concernNumber=" + concernNumber +
                ", fansNumber=" + fansNumber +
                ", subscription=" + subscription +
                ", collection=" + collection +
                ", order=" + order +
                ", craftsmanCoins=" + craftsmanCoins +
                ", craftsQuesAns=" + craftsQuesAns +
                ", myAlbum=" + myAlbum +
                ", myTopList=" + myTopList +
                ", myRecroding=" + myRecroding +
                '}';
    }
}
