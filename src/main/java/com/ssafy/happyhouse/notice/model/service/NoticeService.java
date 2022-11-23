package com.ssafy.happyhouse.notice.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.notice.model.Notice;
import com.ssafy.happyhouse.notice.model.NoticeParameter;
import com.ssafy.happyhouse.util.PageNavigation;

public interface NoticeService {

	int writeNotice(Notice notice) throws SQLException;
//	List<Notice> listNotice(Map<String, String> map) throws SQLException;
	public List<Notice> listNotice(NoticeParameter noticeParameter) throws Exception;
	
//	PageNavigation makePageNavigation(Map<String, String> map) throws SQLException;
	PageNavigation makePageNavigation(NoticeParameter noticeParameter) throws Exception;
	int totalNoticeCount(Map<String, String> map) throws SQLException;
	
	Notice getNotice(int noticeNo) throws SQLException;
	void updateHit(int noticeNo) throws SQLException;
	void modifyNotice(Notice notice) throws SQLException;
	void deleteNotice(int noticeNo) throws SQLException;
}
