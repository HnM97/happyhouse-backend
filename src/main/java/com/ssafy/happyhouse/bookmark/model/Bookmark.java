package com.ssafy.happyhouse.bookmark.model;

public class Bookmark {
    private String userId;
    private String aptCode;
    private String dongCode;
    private String apartmentName;
    private int buildYear;
    private String roadName;
    private String jibun;
    private double lng;
    private double lat;

    private int minAmount;
    private int maxAmount;

    public Bookmark() {
    }

    public Bookmark(String userId, String aptCode, String dongCode, String apartmentName, int buildYear, String roadName, String jibun, double lng, double lat, int minAmount, int maxAmount) {
        this.userId = userId;
        this.aptCode = aptCode;
        this.dongCode = dongCode;
        this.apartmentName = apartmentName;
        this.buildYear = buildYear;
        this.roadName = roadName;
        this.jibun = jibun;
        this.lng = lng;
        this.lat = lat;
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public int getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(int minAmount) {
        this.minAmount = minAmount;
    }

    public int getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(int maxAmount) {
        this.maxAmount = maxAmount;
    }

    @Override
    public String toString() {
        return "Bookmark{" +
                "userId='" + userId + '\'' +
                ", aptCode='" + aptCode + '\'' +
                ", dongCode='" + dongCode + '\'' +
                ", apartmentName='" + apartmentName + '\'' +
                ", buildYear=" + buildYear +
                ", roadName='" + roadName + '\'' +
                ", jibun='" + jibun + '\'' +
                ", lng=" + lng +
                ", lat=" + lat +
                ", minAmount=" + minAmount +
                ", maxAmount=" + maxAmount +
                '}';
    }
}
