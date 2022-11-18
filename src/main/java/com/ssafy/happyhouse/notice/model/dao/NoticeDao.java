package com.ssafy.happyhouse.notice.model.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.notice.model.Notice;

@Mapper
public interface NoticeDao {

	int writeNotice(Notice notice) throws SQLException;
	List<Notice> getNoticeAll(Map<String, String> map) throws SQLException;
	List<Notice> listNotice(Map<String, Object> map) throws SQLException;
	int totalNoticeCount(Map<String, String> map) throws SQLException;
	Notice getNotice(int noticeNo) throws SQLException;
	void updateHit(int noticeNo) throws SQLException;
	void modifyNotice(Notice notice) throws SQLException;
	void deleteNotice(int noticeNo) throws SQLException;
	int getTotalArticleCount(Map<String, Object> param) throws SQLException;
	
}
