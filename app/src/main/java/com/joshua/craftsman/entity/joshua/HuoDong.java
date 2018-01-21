package com.joshua.craftsman.entity.joshua;

import java.io.Serializable;

public class HuoDong implements Serializable{
	private String id;//序列号
	private String activityName;//工匠id（回答者）
	private String activityDetail;//提问者id
    private String activityDate; //问题文字
    
    public HuoDong(){
    	super();
    }
    
	public HuoDong(String activityName, String activityDetail, String activityDate) {
		
		id = null;
		activityName = activityName;
		activityDetail = activityDetail;
		activityDate = activityDate;
	}

	public String getid() {
		return id;
	}

	public String getactivityName() {
		return activityName;
	}

	public String getactivityDetail() {
		return activityDetail;
	}

	public String getactivityDate() {
		return activityDate;
	}

	public void setid(String id) {
		id = id;
	}

	public void setactivityName(String activityName) {
		activityName = activityName;
	}

	public void setactivityDetail(String activityDetail) {
		activityDetail = activityDetail;
	}

	public void setactivityDate(String activityDate) {
		activityDate = activityDate;
	}

	@Override
	public String toString() {
		return "Activity [id=" + id + ", activityName=" + activityName + ", activityDetail=" + activityDetail
				+ ", activityDate=" + activityDate + "]";
	}
    
    

}
