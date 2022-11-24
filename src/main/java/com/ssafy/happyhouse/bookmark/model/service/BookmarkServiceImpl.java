package com.ssafy.happyhouse.bookmark.model.service;
import com.ssafy.happyhouse.bookmark.model.Bookmark;
import com.ssafy.happyhouse.bookmark.model.dao.BookmarkDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class BookmarkServiceImpl implements BookmarkService{
    BookmarkDao bookmarkDao;

    @Autowired
    public BookmarkServiceImpl(BookmarkDao bookmarkDao) {
        super();
        this.bookmarkDao = bookmarkDao;
    }

    @Override
    public void createBookmark(String userId, String aptCode, String dongCode) throws SQLException {
        bookmarkDao.createBookmark(userId, aptCode, dongCode);
    }

    @Override
    public List<Bookmark> selectAptById(String userId) throws SQLException {
        return bookmarkDao.selectAptById(userId);
    }

    @Override
    public void deleteBookmark(String userId, String aptCode) throws SQLException {
        bookmarkDao.deleteBookmark(userId, aptCode);
    }

    @Override
    public int checkBookmark(String userId, String aptCode) throws SQLException {
        return bookmarkDao.checkBookmark(userId, aptCode);
    }
}
