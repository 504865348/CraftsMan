package com.joshua.craftsman.entity;

import java.util.List;

/**
 * Created by nzz on 2017/5/1.
 * 普通用户--我的匠币
 */
public class PublicCoins {
    private int coinsNumber; //匠币数
    private List<String> recharge; //充值记录
    private List<String> consumption; //消费记录

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

    public PublicCoins(int coinsNumber, List<String> recharge, List<String> consumption) {
        this.coinsNumber = coinsNumber;
        this.recharge = recharge;
        this.consumption = consumption;
    }

    @Override
    public String toString() {
        return "PublicCoins{" +
                "coinsNumber=" + coinsNumber +
                ", recharge=" + recharge +
                ", consumption=" + consumption +
                '}';
    }
}
