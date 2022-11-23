package com.ssafy.happyhouse.user.model.dao;

import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.user.model.User;

@Mapper
public interface UserDao {

	int idCheck(String userId) throws SQLException;
	void joinMember(User user) throws SQLException;
	public User loginMember(Map<String, String> map) throws SQLException;
	void modifyMember(User user) throws SQLException;
	void deleteMember(String userId) throws SQLException;
	User searchById(String userId) throws SQLException;
	
	public User login(User user) throws SQLException;
	public User userInfo(String userid) throws SQLException;
	public void saveRefreshToken(Map<String, String> map) throws SQLException;
	public Object getRefreshToken(String userid) throws SQLException;
	public void deleteRefreshToken(Map<String, String> map) throws SQLException;
}
