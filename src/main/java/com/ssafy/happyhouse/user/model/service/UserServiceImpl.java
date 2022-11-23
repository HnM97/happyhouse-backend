package com.ssafy.happyhouse.user.model.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.user.model.User;
import com.ssafy.happyhouse.user.model.dao.UserDao;

@Service
public class UserServiceImpl implements UserService {

	private UserDao userDao;
	
	@Autowired
	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public int idCheck(String userId) throws SQLException {
		return userDao.idCheck(userId);
	}

	@Override
	public void joinMember(User user) throws SQLException {
		userDao.joinMember(user);
	}

	@Override
	public User loginMember(Map<String, String> map) throws SQLException {
		return userDao.loginMember(map);
	}

	@Override
	public void modifyMember(User user) throws SQLException {
		userDao.modifyMember(user);
	}

	@Override
	public void deleteMember(String userId) throws SQLException {
		userDao.deleteMember(userId);
	}

	@Override
	public User searchById(String userId) throws SQLException {
		return userDao.searchById(userId);
		
	}
	
	@Override
	public User login(User user) throws Exception {
		if (user.getUserId() == null || user.getUserPwd() == null)
			return null;
		return userDao.login(user);
	}

	@Override
	public User userInfo(String userid) throws Exception {
		return userDao.userInfo(userid);
	}

	@Override
	public void saveRefreshToken(String userid, String refreshToken) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("token", refreshToken);
		userDao.saveRefreshToken(map);
	}

	@Override
	public Object getRefreshToken(String userid) throws Exception {
		return userDao.getRefreshToken(userid);
	}

	@Override
	public void deleRefreshToken(String userid) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("userid", userid);
		map.put("token", null);
		userDao.deleteRefreshToken(map);
	}

}
