package com.ssafy.happyhouse.bookmark.model.dao;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.bookmark.model.Bookmark;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookmarkDao {
    public void createBookmark(String userId, String aptCode, String dongCode) throws SQLException;;
    public List<Bookmark> selectAptById(String userId) throws SQLException;
    public void deleteBookmark(String userId, String aptCode) throws SQLException;
}
