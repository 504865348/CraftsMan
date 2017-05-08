package com.joshua.craftsman.entity;

import java.util.List;

/**
 * Created by nzz on 2017/5/1.
 * 工匠用户--我的匠币
 */

public class CraftsCoins {
    private String craftsCoinsID; //我的匠币对应的工匠用户ID
    private String publicCoinsID; //我的匠币对应的普通用户ID
    private int coinsNumber; //匠币数
    private List<String> recharge; //充值记录
    private List<String> consumption; //消费记录
    private List<String> income; //收入记录

    public String getCraftsCoinsID() {
        return craftsCoinsID;
    }

    public void setCraftsCoinsID(String craftsCoinsID) {
        this.craftsCoinsID = craftsCoinsID;
    }

    public String getPublicCoinsID() {
        return publicCoinsID;
    }

    public void setPublicCoinsID(String publicCoinsID) {
        this.publicCoinsID = publicCoinsID;
    }

    public int getCoinsNumber() {
        return coinsNumber;
    }

    public void setCoinsNumber(int coinsNumber) {
        this.coinsNumber = coinsNumber;
    }

    public List<String> getRecharge() {
        return recharge;
    }

    public void setRecharge(List<String> recharge) {
        this.recharge = recharge;
    }

    public List<String> getConsumption() {
        return consumption;
    }

    public void setConsumption(List<String> consumption) {
        this.consumption = consumption;
    }

    public List<String> getIncome() {
        return income;
    }

    public void setIncome(List<String> income) {
        this.income = income;
    }

    public CraftsCoins(String craftsCoinsID, String publicCoinsID, int coinsNumber, List<String> recharge, List<String> consumption, List<String> income) {
        this.craftsCoinsID = craftsCoinsID;
        this.publicCoinsID = publicCoinsID;
        this.coinsNumber = coinsNumber;
        this.recharge = recharge;
        this.consumption = consumption;
        this.income = income;
    }
}
