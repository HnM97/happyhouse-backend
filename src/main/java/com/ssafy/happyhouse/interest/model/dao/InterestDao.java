package com.ssafy.happyhouse.interest.model.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.interest.model.Interest;

@Mapper
public interface InterestDao {
	int countInterest(String userId) throws SQLException;
	String[] getNames(String dongCode) throws SQLException;
	List<Interest> getInterestList(String userId) throws SQLException;
	void addInterest(String userId, String dongCode) throws SQLException;
	void deleteInterest(String userId, String dongCode) throws SQLException;
}
