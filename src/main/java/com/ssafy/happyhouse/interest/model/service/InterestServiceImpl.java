package com.ssafy.happyhouse.interest.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.interest.model.Interest;
import com.ssafy.happyhouse.interest.model.dao.InterestDao;

@Service
public class InterestServiceImpl implements InterestService {
	private InterestDao interestDao;
	
	@Autowired
	public InterestServiceImpl(InterestDao interestDao) {
		super();
		this.interestDao = interestDao;
	}

	@Override
	public List<Interest> getInterestList(String userId) throws SQLException {
		return interestDao.getInterestList(userId);
	}

	@Override
	public void addInterest(String userId, String dongCode) throws SQLException{
		interestDao.addInterest(userId, dongCode);
	}

	@Override
	public void deleteInterest(String userId, String dongCode) throws SQLException {
		interestDao.deleteInterest(userId, dongCode);
	}

	@Override
	public String[] getNames(String dongCode) throws SQLException {
		return interestDao.getNames(dongCode);
	}

	@Override
	public int countInterest(String userId) throws SQLException {
		return interestDao.countInterest(userId);
	}

}
