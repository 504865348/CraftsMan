package com.joshua.craftsman.entity;

import java.io.Serializable;

/**
 * Created by nzz on 2017/5/1.
 * 问答分类--问答，工匠
 */

public class QuesAnsClassify implements Serializable{
    private String id;//序列号
    private String userId;//提问者Id
    private String userName;//提问者名称
    private String craftsmanId;//工匠Id（回答者）
    private String craftsmanName;//工匠名称（回答者）
    private String listenNumber; //收听人数
    private String ansterTime;//回答时间
    private String vedioTimes;//音频时长
    private String money;// 匠币
    private String craftsImage; //工匠头像
    private String introduction; //工匠简介
    private String questionWord; //问题文字
    private String questionPic; //问题图片
    private String answerAmr; //音频答案 amr格式
    private String queTime;//提问时间
    private String isDeal;//是否处理


    public QuesAnsClassify(String id, String userId, String userName, String craftsmanId, String craftsmanName, String listenNumber, String ansterTime, String vedioTimes, String money, String craftsImage, String introduction, String questionWord, String questionPic, String answerAmr, String queTime, String isDeal) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.craftsmanId = craftsmanId;
        this.craftsmanName = craftsmanName;
        this.listenNumber = listenNumber;
        this.ansterTime = ansterTime;
        this.vedioTimes = vedioTimes;
        this.money = money;
        this.craftsImage = craftsImage;
        this.introduction = introduction;
        this.questionWord = questionWord;
        this.questionPic = questionPic;
        this.answerAmr = answerAmr;
        this.queTime = queTime;
        this.isDeal = isDeal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCraftsmanId() {
        return craftsmanId;
    }

    public void setCraftsmanId(String craftsmanId) {
        this.craftsmanId = craftsmanId;
    }

    public String getCraftsmanName() {
        return craftsmanName;
    }

    public void setCraftsmanName(String craftsmanName) {
        this.craftsmanName = craftsmanName;
    }

    public String getListenNumber() {
        return listenNumber;
    }

    public void setListenNumber(String listenNumber) {
        this.listenNumber = listenNumber;
    }

    public String getAnsterTime() {
        return ansterTime;
    }

    public void setAnsterTime(String ansterTime) {
        this.ansterTime = ansterTime;
    }

    public String getVedioTimes() {
        return vedioTimes;
    }

    public void setVedioTimes(String vedioTimes) {
        this.vedioTimes = vedioTimes;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getCraftsImage() {
        return craftsImage;
    }

    public void setCraftsImage(String craftsImage) {
        this.craftsImage = craftsImage;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getQuestionWord() {
        return questionWord;
    }

    public void setQuestionWord(String questionWord) {
        this.questionWord = questionWord;
    }

    public String getQuestionPic() {
        return questionPic;
    }

    public void setQuestionPic(String questionPic) {
        this.questionPic = questionPic;
    }

    public String getAnswerAmr() {
        return answerAmr;
    }

    public void setAnswerAmr(String answerAmr) {
        this.answerAmr = answerAmr;
    }

    public String getQueTime() {
        return queTime;
    }

    public void setQueTime(String queTime) {
        this.queTime = queTime;
    }

    public String getIsDeal() {
        return isDeal;
    }

    public void setIsDeal(String isDeal) {
        this.isDeal = isDeal;
    }

    @Override
    public String toString() {
        return "QuesAnsClassify{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", craftsmanId='" + craftsmanId + '\'' +
                ", craftsmanName='" + craftsmanName + '\'' +
                ", listenNumber='" + listenNumber + '\'' +
                ", ansterTime='" + ansterTime + '\'' +
                ", vedioTimes='" + vedioTimes + '\'' +
                ", money='" + money + '\'' +
                ", craftsImage='" + craftsImage + '\'' +
                ", introduction='" + introduction + '\'' +
                ", questionWord='" + questionWord + '\'' +
                ", questionPic='" + questionPic + '\'' +
                ", answerAmr='" + answerAmr + '\'' +
                ", queTime='" + queTime + '\'' +
                ", isDeal='" + isDeal + '\'' +
                '}';
    }
}
