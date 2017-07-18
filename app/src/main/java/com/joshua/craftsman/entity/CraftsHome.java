package com.joshua.craftsman.entity;

/**
 * Created by nzz on 2017/7/18.
 */

public class CraftsHome {
    private String ImageUrl;
    private String CraftsmanName;
    private String Introducton;

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getCraftsmanName() {
        return CraftsmanName;
    }

    public void setCraftsmanName(String craftsmanName) {
        CraftsmanName = craftsmanName;
    }

    public String getIntroducton() {
        return Introducton;
    }

    public void setIntroducton(String introducton) {
        Introducton = introducton;
    }

    public CraftsHome(String imageUrl, String craftsmanName, String introducton) {
        ImageUrl = imageUrl;
        CraftsmanName = craftsmanName;
        Introducton = introducton;
    }

    @Override
    public String toString() {
        return "CraftsHome{" +
                "ImageUrl='" + ImageUrl + '\'' +
                ", CraftsmanName='" + CraftsmanName + '\'' +
                ", Introducton='" + Introducton + '\'' +
                '}';
    }
}
