package com.ssafy.happyhouse.qna.model.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.qna.model.Memo;
import com.ssafy.happyhouse.qna.model.Qna;
import com.ssafy.happyhouse.qna.model.QnaParameter;

@Mapper
public interface QnaDao {
	
    int writeQna(Qna qna) throws SQLException;
    List<Qna> getQnaAll(Map<String, String> map) throws SQLException;
    List<Qna> listQna(QnaParameter qnaParameter) throws SQLException;
    int totalQnaCount(Map<String, String> map) throws SQLException;
    Qna getQna(int qnaNo) throws SQLException;
    void updateHit(int qnaNo) throws SQLException;
    void modifyQna(Qna qna) throws SQLException;
    void deleteQna(int qnaNo) throws SQLException;
    int getTotalArticleCount(Map<String, Object> param) throws SQLException;
    int getTotalCount(QnaParameter qnaParameter) throws SQLException;

    int writeMemo(int qnaNo, Memo memo) throws SQLException;
    void deleteMemo(int qnaNo, int memoNo) throws SQLException;
//    int modifyMemo(Memo memo) throws SQLException;
    List<Memo> listMemo(int articleNo);
}
