package com.ssafy.happyhouse.qna.model.service;

import com.ssafy.happyhouse.qna.model.Memo;
import com.ssafy.happyhouse.qna.model.Qna;
import com.ssafy.happyhouse.qna.model.dao.QnaDao;
import com.ssafy.happyhouse.util.PageNavigation;
import com.ssafy.happyhouse.util.SizeConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QnaServiceImpl implements QnaService {
    private QnaDao qnaDao;

    @Autowired
    public QnaServiceImpl(QnaDao qnaDao) {
        this.qnaDao = qnaDao;
    }
    @Override
    public List<Qna> listQna(Map<String, String> map) throws SQLException {
        Map<String, Object> param = new HashMap<String, Object>();
        String key = map.get("key");
        if ("userid".equals(key))
            key = "b.user_id";
        param.put("key", key == null ? "" : key);
        param.put("word", map.get("word") == null ? "" : map.get("word"));
        int pgNo = Integer.parseInt(map.get("pgno") == null ? "1" : map.get("pgno"));
        int start = pgNo * SizeConstant.LIST_SIZE - SizeConstant.LIST_SIZE;
        param.put("start", start);
        param.put("listsize", SizeConstant.LIST_SIZE);

        return qnaDao.listQna(param);
    }

    @Override
    public int writeQna(Qna qna) throws SQLException {
        return qnaDao.writeQna(qna);
    }


    @Override
    public int totalQnaCount(Map<String, String> map) throws SQLException {
        return qnaDao.totalQnaCount(map);
    }

    @Override
    public int getTotalArticleCount(Map<String, Object> param) throws SQLException {
        return qnaDao.getTotalArticleCount(param);
    }

    @Override
    public Qna getQna(int qnaNo) throws SQLException {
        return qnaDao.getQna(qnaNo);
    }

    @Override
    public void updateHit(int qnaNo) throws SQLException {
        qnaDao.updateHit(qnaNo);
    }

    @Override
    public void modifyQna(Qna qna) throws SQLException {
        qnaDao.modifyQna(qna);
    }

    @Override
    public void deleteQna(int qnaNo) throws SQLException {
        qnaDao.deleteQna(qnaNo);
    }

    @Override
    public PageNavigation makePageNavigation(Map<String, String> map) throws SQLException {
        PageNavigation pageNavigation = new PageNavigation();

        int naviSize = SizeConstant.NAVIGATION_SIZE;
        int sizePerPage = SizeConstant.LIST_SIZE;
        int currentPage = Integer.parseInt(map.get("pgno"));

        pageNavigation.setCurrentPage(currentPage);
        pageNavigation.setNaviSize(naviSize);
        Map<String, Object> param = new HashMap<String, Object>();
        String key = map.get("key");
        if ("userid".equals(key))
            key = "user_id";
        param.put("key", key == null ? "" : key);
        param.put("word", map.get("word") == null ? "" : map.get("word"));
        int totalCount = qnaDao.getTotalArticleCount(param);
        pageNavigation.setTotalCount(totalCount);
        int totalPageCount = (totalCount - 1) / sizePerPage + 1;
        pageNavigation.setTotalPageCount(totalPageCount);
        boolean startRange = currentPage <= naviSize;
        pageNavigation.setStartRange(startRange);
        boolean endRange = (totalPageCount - 1) / naviSize * naviSize < currentPage;
        pageNavigation.setEndRange(endRange);
        pageNavigation.makeNavigator();

        return pageNavigation;
    }

    @Override
    public int writeMemo(int qnaNo, Memo memo) throws SQLException {
        return qnaDao.writeMemo(qnaNo, memo);
    }

    @Override
    public void deleteMemo(int qnaNo, int memoNo) throws SQLException {
        qnaDao.deleteMemo(qnaNo, memoNo);
    }

    @Override
    public List<Memo> listMemo(int articleNo) {
        return qnaDao.listMemo(articleNo);
    }
}
