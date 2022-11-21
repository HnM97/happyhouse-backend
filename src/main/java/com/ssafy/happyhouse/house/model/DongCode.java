package com.ssafy.happyhouse.house.model;

public class DongCode {
	private String dongCode;
	private String sidoName;
	private String gugunName;
	private String dongName;
	
	public DongCode() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DongCode(String dongCode, String sidoName, String gugunName, String dongName) {
		super();
		this.dongCode = dongCode;
		this.sidoName = sidoName;
		this.gugunName = gugunName;
		this.dongName = dongName;
	}
	public String getDongCode() {
		return dongCode;
	}
	public String getSidoName() {
		return sidoName;
	}
	public String getGugunName() {
		return gugunName;
	}
	public String getDongName() {
		return dongName;
	}
	@Override
	public String toString() {
		return "DongCode [dongCode=" + dongCode + ", sidoName=" + sidoName + ", gugunName=" + gugunName + ", dongName="
				+ dongName + "]";
	}
	
}
