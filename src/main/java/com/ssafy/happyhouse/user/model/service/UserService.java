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
}
