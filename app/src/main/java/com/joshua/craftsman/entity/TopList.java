package com.joshua.craftsman.entity;

import java.util.List;

/**
 * Created by nzz on 2017/4/29.
 * 底部按钮--榜单
 */

public class TopList {
    private List<String> hottestProgram; //最火节目飙升榜
    private List<String> mostSubscription; //最多订阅经典版
    private List<String> payFine; //付费精品飙升榜
    private List<String> topCraftsman; //最大国工匠榜\

    public List<String> getHottestProgram() {
        return hottestProgram;
    }

    public void setHottestProgram(List<String> hottestProgram) {
        this.hottestProgram = hottestProgram;
    }

    public List<String> getMostSubscription() {
        return mostSubscription;
    }

    public void setMostSubscription(List<String> mostSubscription) {
        this.mostSubscription = mostSubscription;
    }

    public List<String> getPayFine() {
        return payFine;
    }

    public void setPayFine(List<String> payFine) {
        this.payFine = payFine;
    }

    public List<String> getTopCraftsman() {
        return topCraftsman;
    }

    public void setTopCraftsman(List<String> topCraftsman) {
        this.topCraftsman = topCraftsman;
    }
}
