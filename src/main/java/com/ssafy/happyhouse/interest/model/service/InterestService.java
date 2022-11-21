package com.ssafy.happyhouse.interest.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.happyhouse.interest.model.Interest;

public interface InterestService {
	int countInterest(String userId) throws SQLException;
	public String[] getNames(String dongCode) throws SQLException;
	List<Interest> getInterestList(String userId) throws SQLException;
	void addInterest(String userId, String dongCode) throws SQLException;
	void deleteInterest(String userId, String dongCode) throws SQLException;
}
