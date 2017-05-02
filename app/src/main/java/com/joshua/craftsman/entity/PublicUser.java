package com.joshua.craftsman.entity;

/**
 * Created by nzz on 2017/4/28.
 * 普通用户
 */

import java.util.List;

public class PublicUser {
    private String headImage; //头像
    private String phoneNumber; //手机号
    private String password; //密码
    private String userName; //用户名
    private int concernNumber; //关注数量
    private List<String> subscription; //订阅列表(订阅的是专辑，参见DownloadAlbum类)
    private List<String> collection; //收藏列表(收藏的是节目，参见DownloadProgram类)
    private List<String> order; //我的订单(参见Order类)
    private List<String> publicCoins; //我的匠币(参见PublicCoins类)
    private List<String> publicQuesAns; //我的问答(参见PublicQuesAns类)

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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getConcernNumber() {
        return concernNumber;
    }

    public void setConcernNumber(int concernNumber) {
        this.concernNumber = concernNumber;
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

    public List<String> getPublicCoins() {
        return publicCoins;
    }

    public void setPublicCoins(List<String> publicCoins) {
        this.publicCoins = publicCoins;
    }

    public List<String> getPublicQuesAns() {
        return publicQuesAns;
    }

    public void setPublicQuesAns(List<String> publicQuesAns) {
        this.publicQuesAns = publicQuesAns;
    }

    public PublicUser(String headImage, String phoneNumber, String password, String userName, int concernNumber, List<String> subscription, List<String> collection, List<String> order, List<String> publicCoins, List<String> publicQuesAns) {
        this.headImage = headImage;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.userName = userName;
        this.concernNumber = concernNumber;
        this.subscription = subscription;
        this.collection = collection;
        this.order = order;
        this.publicCoins = publicCoins;
        this.publicQuesAns = publicQuesAns;
    }

    @Override
    public String toString() {
        return "PublicUser{" +
                "headImage='" + headImage + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", userName='" + userName + '\'' +
                ", concernNumber=" + concernNumber +
                ", subscription=" + subscription +
                ", collection=" + collection +
                ", order=" + order +
                ", publicCoins=" + publicCoins +
                ", publicQuesAns=" + publicQuesAns +
                '}';
    }
}
