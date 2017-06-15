package com.joshua.craftsman.entity;

/**
 * Created by nzz on 2017/5/15.
 * 工匠-我的问答-未处理回答
 */

public class CraftsUnDealAns {
    private String askName; //提问者
    private String time; //提问时间
    private String content; //问题内容

    public String getAskName() {
        return askName;
    }

    public void setAskName(String askName) {
        this.askName = askName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public CraftsUnDealAns(String askName, String time, String content) {
        this.askName = askName;
        this.time = time;
        this.content = content;
    }

    @Override
    public String toString() {
        return "CraftsUnDealAns{" +
                "askName='" + askName + '\'' +
                ", time='" + time + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
