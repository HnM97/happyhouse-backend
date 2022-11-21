package com.ssafy.happyhouse.user.model.service;

import java.sql.SQLException;
import java.util.Map;

import com.ssafy.happyhouse.user.model.User;

public interface UserService {

	int idCheck(String userId) throws SQLException;
	void joinMember(User user) throws SQLException;
	public User loginMember(Map<String, String> map) throws SQLException;
	void modifyMember(User user) throws SQLException;
	void deleteMember(String userId) throws SQLException;
	User searchById(String userId) throws SQLException;
	
	public User login(User user) throws Exception;
	public User userInfo(String userid) throws Exception;
	public void saveRefreshToken(String userid, String refreshToken) throws Exception;
	public Object getRefreshToken(String userid) throws Exception;
	public void deleRefreshToken(String userid) throws Exception;
}
