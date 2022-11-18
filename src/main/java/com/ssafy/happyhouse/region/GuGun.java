package com.ssafy.happyhouse.region;

public class GuGun {
	private String dongCode; // 5자리 동 코드
	private String gugunName;
	public GuGun() {
		super();
		// TODO Auto-generated constructor stub
	}
	public GuGun(String dongCode, String gugunName) {
		super();
		this.dongCode = dongCode;
		this.gugunName = gugunName;
	}
	public String getDongCode() {
		return dongCode;
	}
	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
	}
	public String getGugunName() {
		return gugunName;
	}
	public void setGugunName(String gugunName) {
		this.gugunName = gugunName;
	}
	@Override
	public String toString() {
		return "GuGun [dongCode=" + dongCode + ", gugunName=" + gugunName + "]";
	}
	
}
