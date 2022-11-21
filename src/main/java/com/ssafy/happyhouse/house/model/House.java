package com.ssafy.happyhouse.house.model;

public class House {
	// db에서 가져올 view
	// 화면에 뿌릴 컬럼만 가져온 것
	private String aptCode;
	private String dongCode;
	private String apartmentName;
	private int buildYear;
	private String dealAmount; 
	private String area;
	private int dealYear;
	private int dealMonth;
	private int dealDay;
	private String roadName;
	private String jibun;
	private double lng;
	private double lat;

	


	@Override
	public String toString() {
		return "House [aptCode=" + aptCode + ", dongCode=" + dongCode + ", apartmentName=" + apartmentName
				+ ", buildYear=" + buildYear + ", dealAmount=" + dealAmount + ", area=" + area + ", dealYear="
				+ dealYear + ", dealMonth=" + dealMonth + ", dealDay=" + dealDay + ", roadName=" + roadName + ", jibun="
				+ jibun + ", lng=" + lng + ", lat=" + lat + "]";
	}




	public House(String aptCode, String dongCode, String apartmentName, int buildYear, String dealAmount,
			String area, int dealYear, int dealMonth, int dealDay, String roadName, String jibun, double lng,
			double lat) {
		super();
		this.aptCode = aptCode;
		this.dongCode = dongCode;
		this.apartmentName = apartmentName;
		this.buildYear = buildYear;
		this.dealAmount = dealAmount;
		this.area = area;
		this.dealYear = dealYear;
		this.dealMonth = dealMonth;
		this.dealDay = dealDay;
		this.roadName = roadName;
		this.jibun = jibun;
		this.lng = lng;
		this.lat = lat;
	}

	public House() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getAptCode() {
		return aptCode;
	}




	public void setAptCode(String aptCode) {
		this.aptCode = aptCode;
	}




	public String getDongCode() {
		return dongCode;
	}




	public void setDongCode(String dongCode) {
		this.dongCode = dongCode;
	}




	public String getApartmentName() {
		return apartmentName;
	}




	public void setApartmentName(String apartmentName) {
		this.apartmentName = apartmentName;
	}




	public int getBuildYear() {
		return buildYear;
	}




	public void setBuildYear(int buildYear) {
		this.buildYear = buildYear;
	}




	public String getDealAmount() {
		return dealAmount;
	}




	public void setDealAmount(String dealAmount) {
		this.dealAmount = dealAmount;
	}




	public String getArea() {
		return area;
	}




	public void setArea(String area) {
		this.area = area;
	}




	public int getDealYear() {
		return dealYear;
	}




	public void setDealYear(int dealYear) {
		this.dealYear = dealYear;
	}




	public int getDealMonth() {
		return dealMonth;
	}




	public void setDealMonth(int dealMonth) {
		this.dealMonth = dealMonth;
	}




	public int getDealDay() {
		return dealDay;
	}




	public void setDealDay(int dealDay) {
		this.dealDay = dealDay;
	}




	public String getRoadName() {
		return roadName;
	}




	public void setRoadName(String roadName) {
		this.roadName = roadName;
	}




	public String getJibun() {
		return jibun;
	}




	public void setJibun(String jibun) {
		this.jibun = jibun;
	}




	public double getLng() {
		return lng;
	}




	public void setLng(double lng) {
		this.lng = lng;
	}




	public double getLat() {
		return lat;
	}




	public void setLat(double lat) {
		this.lat = lat;
	}



}
