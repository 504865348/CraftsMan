package com.joshua.craftsman.entity;

/**
 * Created by nzz on 2017/5/8.
 * 首页--热门--大国工匠
 */

public class HotCraftsman {
    private String imageUrl; //图片
    private String craftsmanName; //工匠名

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCraftsmanName() {
        return craftsmanName;
    }

    public void setCraftsmanName(String craftsmanName) {
        this.craftsmanName = craftsmanName;
    }

    public HotCraftsman(String imageUrl, String craftsmanName) {
        this.imageUrl = imageUrl;
        this.craftsmanName = craftsmanName;
    }

    @Override
    public String toString() {
        return "HotCraftsman{" +
                "imageUrl='" + imageUrl + '\'' +
                ", craftsmanName='" + craftsmanName + '\'' +
                '}';
    }
}
