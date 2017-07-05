package com.joshua.craftsman.entity;


/**
 * Created by nzz on 2017/6/5.
 * 首页--分类
 * 通过参数获取具体分类的相关数据：参数-具体分类
 * houses-房屋建筑; municipal-市政公用; mechanical-机电工程
 * highWays-公路; waterConservancy-水利水电; railWay-铁路工程
 * mining-矿业工程; airport-民航机场工程; communications-通信广电工程
 */

public class Classify {
    private String recordImage; //节目图片
    private String recordTitle; //标题
    private String name; //作者名字

    public String getRecordImage() {
        return recordImage;
    }

    public void setRecordImage(String recordImage) {
        this.recordImage = recordImage;
    }

    public String getRecordTitle() {
        return recordTitle;
    }

    public void setRecordTitle(String recordTitle) {
        this.recordTitle = recordTitle;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Classify(String recordImage, String recordTitle, String name) {
        this.recordImage = recordImage;
        this.recordTitle = recordTitle;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Classify{" +
                "recordImage='" + recordImage + '\'' +
                ", recordTitle='" + recordTitle + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
