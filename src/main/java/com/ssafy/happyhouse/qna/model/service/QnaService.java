package com.ssafy.happyhouse.qna.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.qna.model.Memo;
import com.ssafy.happyhouse.qna.model.Qna;
import com.ssafy.happyhouse.qna.model.QnaParameter;

public interface QnaService {
	
    int writeQna(Qna qna) throws SQLException;
//    List<Qna> listQna(Map<String, String> map) throws SQLException;
    List<Qna> listQna(QnaParameter qnaParameter) throws Exception;
    
//    PageNavigation makePageNavigation(Map<String, String> map) throws SQLException;
    Map<String, Object> makePageNavigation(QnaParameter qnaParameter) throws Exception;
    int totalQnaCount(Map<String, String> map) throws SQLException;
    int getTotalArticleCount(Map<String, Object> param) throws SQLException;
    
    Qna getQna(int qnaNo) throws SQLException;
    void updateHit(int qnaNo) throws SQLException;
    void modifyQna(Qna qna) throws SQLException;
    void deleteQna(int qnaNo) throws SQLException;

    // Memo
    int writeMemo(int qnaNo,Memo memo) throws SQLException;
    void deleteMemo(int qnaNo, int memoNo) throws SQLException;
    //    int modifyMemo(Memo memo) throws SQLException;
    List<Memo> listMemo(int articleNo);
}
