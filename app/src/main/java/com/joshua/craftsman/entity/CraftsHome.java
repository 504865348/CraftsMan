package com.joshua.craftsman.entity;

/**
 * Created by nzz on 2017/7/18.
 */

public class CraftsHome {
    private String imageUrl;
    private String craftsmanName;
    private String introduction;

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

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public CraftsHome(String imageUrl, String craftsmanName, String introduction) {
        this.imageUrl = imageUrl;
        this.craftsmanName = craftsmanName;
        this.introduction = introduction;
    }

    @Override
    public String toString() {
        return "CraftsHome{" +
                "imageUrl='" + imageUrl + '\'' +
                ", craftsmanName='" + craftsmanName + '\'' +
                ", introduction='" + introduction + '\'' +
                '}';
    }
}
