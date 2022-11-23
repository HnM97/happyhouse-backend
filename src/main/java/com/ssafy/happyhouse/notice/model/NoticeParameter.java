package com.ssafy.happyhouse.notice.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "NoticeParameter : 공지사항 파라미터 정보", description = "공지사항의 글을 얻기 위한 부가적인 파라미터 정보")
public class NoticeParameter {

	@ApiModelProperty(value = "현재 페이지 번호")
	private int pgNo;
	@ApiModelProperty(value = "페이지당 글갯수")
	private int spp;
	@ApiModelProperty(value = "페이지의 시작 글번호")
	private int start;
	@ApiModelProperty(value = "검색 조건")
	private String key;
	@ApiModelProperty(value = "검색어")
	private String word;
	
	public NoticeParameter() {
		pgNo = 1;
		spp = 15;
	}

	public int getPgNo() {
		return pgNo;
	}

	public void setPgNo(int pgNo) {
		pgNo = pgNo == 0 ? 1 : pgNo;
		this.pgNo = pgNo;
	}

	public int getSpp() {
		return spp;
	}

	public void setSpp(int spp) {
		this.spp = spp;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	@Override
	public String toString() {
		return "NoticeParameter [pgNo=" + pgNo + ", spp=" + spp + ", start=" + start + ", key=" + key + ", word=" + word
				+ "]";
	}

}

