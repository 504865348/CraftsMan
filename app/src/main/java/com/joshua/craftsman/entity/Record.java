package com.joshua.craftsman.entity;

/**
 * ============================================================
 * <p>
 * 版 权 ： 吴奇俊  (c) 2017
 * <p>
 * 作 者 : 吴奇俊
 * <p>
 * 版 本 ： 1.0
 * <p>
 * 创建日期 ： 2017/7/7 21:54
 * <p>
 * 描 述 ：
 * <p>
 * ============================================================
 **/

public class Record {
    private String Id;//序列号
    private String RecordImage;//录制视频或音频的图片
    private String RecordVedio;//录制视频或音频
    private String RecordTitle;//标题
    private String Name;//录制人名字
    private String RecordTime; //录制时间
    private String ReleaseTime;//发布时间
    private String Duration; //视频/音频时长
    private String CommentTimes;//评论次数
    private String PlayTimes;//播放次数
    private String BookTimes;//订阅次数
    private String BuyTimes;//购买次数
    private String Special;//专辑
    private String Type;//类型
    private String Introduction;//介绍

    public Record(String id, String recordImage, String recordVedio, String recordTitle, String name, String recordTime,
                  String releaseTime, String duration, String commentTimes, String playTimes, String bookTimes,
                  String buyTimes, String special, String type,String introduction) {
        Id = null;
        RecordImage = recordImage;
        RecordVedio = recordVedio;
        RecordTitle = recordTitle;
        Name = name;
        RecordTime = recordTime;
        ReleaseTime = releaseTime;
        Duration = duration;
        CommentTimes = commentTimes;
        PlayTimes = playTimes;
        BookTimes = bookTimes;
        BuyTimes = buyTimes;
        Special = special;
        Type = type;
        Introduction=introduction;
    }

    public String getIntroduction() {
        return Introduction;
    }

    public void setIntroduction(String introduction) {
        Introduction = introduction;
    }
    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getRecordImage() {
        return RecordImage;
    }

    public void setRecordImage(String recordImage) {
        RecordImage = recordImage;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getRecordTime() {
        return RecordTime;
    }

    public void setRecordTime(String recordTime) {
        RecordTime = recordTime;
    }

    public String getDuration() {
        return Duration;
    }

    public void setDuration(String duration) {
        Duration = duration;
    }

    public String getPlayTimes() {
        return PlayTimes;
    }

    public void setPlayTimes(String playTimes) {
        PlayTimes = playTimes;
    }

    public String getBookTimes() {
        return BookTimes;
    }

    public void setBookTimes(String bookTimes) {
        BookTimes = bookTimes;
    }

    public String getBuyTimes() {
        return BuyTimes;
    }

    public void setBuyTimes(String buyTimes) {
        BuyTimes = buyTimes;
    }


    public String getRecordTitle() {
        return RecordTitle;
    }


    public void setRecordTitle(String recordTitle) {
        RecordTitle = recordTitle;
    }


    public String getReleaseTime() {
        return ReleaseTime;
    }


    public void setReleaseTime(String releaseTime) {
        ReleaseTime = releaseTime;
    }


    public String getCommentTimes() {
        return CommentTimes;
    }


    public void setCommentTimes(String commentTimes) {
        CommentTimes = commentTimes;
    }


    public String getRecordVedio() {
        return RecordVedio;
    }


    public void setRecordVedio(String recordVedio) {
        RecordVedio = recordVedio;
    }


    public String getType() {
        return Type;
    }


    public void setType(String type) {
        Type = type;
    }


    public String getSpecial() {
        return Special;
    }


    public void setSpecial(String special) {
        Special = special;
    }


    @Override
    public String toString() {
        return "MyRecording [Id=" + Id + ", RecordImage=" + RecordImage + ", RecordVedio=" + RecordVedio
                + ", RecordTitle=" + RecordTitle + ", Name=" + Name + ", RecordTime=" + RecordTime + ", ReleaseTime="
                + ReleaseTime + ", Duration=" + Duration + ", CommentTimes=" + CommentTimes + ", PlayTimes=" + PlayTimes
                + ", BookTimes=" + BookTimes + ", BuyTimes=" + BuyTimes + ", Special=" + Special + ", Type=" + Type
                + "]";
    }

}
