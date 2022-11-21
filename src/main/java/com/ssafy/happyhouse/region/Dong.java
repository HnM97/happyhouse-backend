package com.ssafy.happyhouse.region;

public class Dong {
	private String dongCode; // 8자리 동 코드
	private String dongName;
	public Dong() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Dong(String dongCode, String dongName) {
		super();
		this.dongCode = dongCode;
		this.dongName = dongName;
	}
	public String getDongCode() {
		return dongCode;
	}
	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
	}
	public String getDongName() {
		return dongName;
	}
	public void setDongName(String dongName) {
		this.dongName = dongName;
	}
	@Override
	public String toString() {
		return "Dong [dongCode=" + dongCode + ", dongName=" + dongName + "]";
	}
	
}
