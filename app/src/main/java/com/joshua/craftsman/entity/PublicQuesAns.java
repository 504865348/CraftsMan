package com.joshua.craftsman.entity;

/**
 * Created by nzz on 2017/5/1.
 * 普通用户--我的问答
 */
public class PublicQuesAns {
    private String questionID; //问题ID
    /* 未处理提问 */
    private String questionedName; //被提问者名字
    private String content; //提问内容
    private long quesTime; //提问时间
    /* 我的提问--前3个属性同“未处理提问” */
    private String answerTime; //语音回答时长
    private int listenNumber; //收听人数

    public String getQuestionID() {
        return questionID;
    }

    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }

    public String getQuestionedName() {
        return questionedName;
    }

    public void setQuestionedName(String questionedName) {
        this.questionedName = questionedName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getQuesTime() {
        return quesTime;
    }

    public void setQuesTime(long quesTime) {
        this.quesTime = quesTime;
    }

    public String getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(String answerTime) {
        this.answerTime = answerTime;
    }

    public int getListenNumber() {
        return listenNumber;
    }

    public void setListenNumber(int listenNumber) {
        this.listenNumber = listenNumber;
    }

    public PublicQuesAns(String questionID, String questionedName, String content, long quesTime, String answerTime, int listenNumber) {
        this.questionID = questionID;
        this.questionedName = questionedName;
        this.content = content;
        this.quesTime = quesTime;
        this.answerTime = answerTime;
        this.listenNumber = listenNumber;
    }

    @Override
    public String toString() {
        return "PublicQuesAns{" +
                "questionID='" + questionID + '\'' +
                ", questionedName='" + questionedName + '\'' +
                ", content='" + content + '\'' +
                ", quesTime=" + quesTime +
                ", answerTime='" + answerTime + '\'' +
                ", listenNumber=" + listenNumber +
                '}';
    }
}
