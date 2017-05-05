package com.joshua.craftsman.entity;

import java.util.List;

/**
 * Created by nzz on 2017/4/28.
 * 首页--热门
 */

public class Hot {
    private List<String> craftsman; //大国工匠
    private List<String> skills; //匠心独运
    private List<String> policy; //讲政策
    private List<String> listen; //听专题
    private List<String> look; //看利器

    public List<String> getCraftsman() {
        return craftsman;
    }

    public void setCraftsman(List<String> craftsman) {
        this.craftsman = craftsman;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public List<String> getPolicy() {
        return policy;
    }

    public void setPolicy(List<String> policy) {
        this.policy = policy;
    }

    public List<String> getListen() {
        return listen;
    }

    public void setListen(List<String> listen) {
        this.listen = listen;
    }

    public List<String> getLook() {
        return look;
    }

    public void setLook(List<String> look) {
        this.look = look;
    }

    public Hot(List<String> craftsman, List<String> skills, List<String> policy, List<String> listen, List<String> look) {
        this.craftsman = craftsman;
        this.skills = skills;
        this.policy = policy;
        this.listen = listen;
        this.look = look;
    }

    @Override
    public String toString() {
        return "Hot{" +
                "craftsman=" + craftsman +
                ", skills=" + skills +
                ", policy=" + policy +
                ", listen=" + listen +
                ", look=" + look +
                '}';
    }
}
