package com.joshua.craftsman.entity;

/**
 * Created by nzz on 2017/4/28.
 */

/**
 * 用户类
 */
public class User {
    private String headImage;//头像
    private String phoneNumber;//手机号
    private String verificationCode;//验证码
    private String password;//密码


    @Override
    public String toString() {
        return "User{" +
                "headImage='" + headImage + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", verificationCode='" + verificationCode + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public User(String headImage, String phoneNumber, String verificationCode, String password) {
        this.headImage = headImage;
        this.phoneNumber = phoneNumber;
        this.verificationCode = verificationCode;
        this.password = password;
    }

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

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
