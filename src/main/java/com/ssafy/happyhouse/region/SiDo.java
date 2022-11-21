package com.ssafy.happyhouse.region;

public class SiDo {
	private String dongCode; // 2자리 동 코드
	private String sidoName;
	public SiDo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SiDo(String dongCode, String sidoName) {
		super();
		this.dongCode = dongCode;
		this.sidoName = sidoName;
	}
	public String getDongCode() {
		return dongCode;
	}
	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
	}
	public String getSidoName() {
		return sidoName;
	}
	public void setSidoName(String sidoName) {
		this.sidoName = sidoName;
	}
	@Override
	public String toString() {
		return "SiDo [dongCode=" + dongCode + ", sidoName=" + sidoName + "]";
	}
	
}
