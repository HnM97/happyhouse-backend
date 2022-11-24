package com.ssafy.happyhouse.bookmark.model.service;

import com.ssafy.happyhouse.bookmark.model.Bookmark;

import java.sql.SQLException;
import java.util.List;
public interface BookmarkService {
    public void createBookmark(String userId, String aptCode,String dongCode) throws SQLException;;
    public List<Bookmark> selectAptById(String userId) throws SQLException;
    public void deleteBookmark(String userId, String aptCode) throws SQLException;
    public int checkBookmark(String userId, String aptCode) throws  SQLException;
}
