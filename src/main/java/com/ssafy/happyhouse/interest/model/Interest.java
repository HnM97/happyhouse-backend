package com.ssafy.happyhouse.interest.model;

public class Interest {
	private String userId;
	private String regionName;
	private String dongCode;
	
	public Interest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Interest(String userId, String regionName, String dongCode) {
		super();
		this.userId = userId;
		this.regionName = regionName;
		this.dongCode = dongCode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public String getDongCode() {
		return dongCode;
	}

	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
	}

	@Override
	public String toString() {
		return "Interest [userId=" + userId + ", regionName=" + regionName + ", dongCode=" + dongCode + "]";
	}

	
}
