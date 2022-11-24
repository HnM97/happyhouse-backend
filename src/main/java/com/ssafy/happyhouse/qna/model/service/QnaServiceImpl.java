package com.ssafy.happyhouse.qna.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.qna.model.Memo;
import com.ssafy.happyhouse.qna.model.Qna;
import com.ssafy.happyhouse.qna.model.QnaParameter;
import com.ssafy.happyhouse.qna.model.dao.QnaDao;
import com.ssafy.happyhouse.util.SizeConstant;

@Service
public class QnaServiceImpl implements QnaService {
	
    private QnaDao qnaDao;

    @Autowired
    public QnaServiceImpl(QnaDao qnaDao) {
        this.qnaDao = qnaDao;
    }
    
    
	@Override
	public List<Qna> listQna(QnaParameter qnaParameter) throws Exception {
		int start = qnaParameter.getPgNo() == 0 ? 0 : (qnaParameter.getPgNo() - 1) * qnaParameter.getSpp();
		qnaParameter.setStart(start);

		return qnaDao.listQna(qnaParameter);
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
    public Map<String, Object> makePageNavigation(QnaParameter qnaParameter) throws Exception {

        int naviSize = SizeConstant.NAVIGATION_SIZE;
        int sizePerPage = SizeConstant.LIST_SIZE;
        int currentPage = qnaParameter.getPgNo();

//        String key = map.get("key");
//        if ("userid".equals(key))
//            key = "user_id";
//        param.put("key", key == null ? "" : key);
//        param.put("word", map.get("word") == null ? "" : map.get("word"));
        
        int totalCount = qnaDao.getTotalCount(qnaParameter);
        int totalPageCount = (totalCount - 1) / sizePerPage + 1;
//        int totalPageCount = (totalCount - 1) / noticeParameter.getSpp() + 1;
        boolean startRange = currentPage <= naviSize;
//        boolean startRange = noticeParameter.getPgNo() <= naviSize;
        boolean endRange = (totalPageCount - 1) / naviSize * naviSize < currentPage;
//        boolean endRange = (totalPageCount - 1) / naviSize * naviSize < noticeParameter.getPgNo();

        int startPage = (qnaParameter.getPgNo() - 1) / naviSize * naviSize + 1;
		int endPage = startPage + naviSize - 1;
		if(totalPageCount < endPage)
			endPage = totalPageCount;    
        
        Map<String, Object> pageNavigationMap = new HashMap<String, Object>();
        
        pageNavigationMap.put("naviSize", naviSize);
		pageNavigationMap.put("currentPage", currentPage);
		pageNavigationMap.put("totalCount", totalCount);
		pageNavigationMap.put("totalPageCount", totalPageCount);
		pageNavigationMap.put("startRange", startRange);
		pageNavigationMap.put("endRange", endRange);
		pageNavigationMap.put("startPage", startPage);
		pageNavigationMap.put("endPage", endPage);
        
        return pageNavigationMap;
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
