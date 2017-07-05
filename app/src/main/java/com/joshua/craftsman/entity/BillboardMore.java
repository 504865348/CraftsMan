package com.joshua.craftsman.entity;

/**
 * Created by nzz on 2017/5/2.
 * 榜单-最多订阅经典榜
 */

public class BillboardMore {
    //private String rankNumber;//节目排名的名次
   // private String programImg;//节目的缩略图或图片
    //private String programTitle;//节目的标题
    //private String authorName;//节目所属的作者名字
    //private String downloadUrl;//节目下载的Url地址
    private String id;//节目排名的名次
    private String recordImage;//节目的缩略图或图片
    private String recordTitle;//节目的标题
    private String name;//节目所属的作者名字

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public BillboardMore(String id, String recordImage, String recordTitle, String name) {
        this.id = id;
        this.recordImage = recordImage;
        this.recordTitle = recordTitle;
        this.name = name;
    }

    @Override
    public String toString() {
        return "BillboardMore{" +
                "id='" + id + '\'' +
                ", recordImage='" + recordImage + '\'' +
                ", recordTitle='" + recordTitle + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
