package com.ssafy.happyhouse.house.model.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.happyhouse.house.model.House;
@Mapper
public interface HouseDao {
	public List<House> selectApt(String aptCode) throws SQLException;
	public List<House> selectDong(String dongCode) throws SQLException;
	public List<House> selectDongYM(String dongCode, int dealYear, int dealMonth) throws SQLException;
	public List<House> searchByCondition(Map<String, Object> map) throws SQLException;
	public List<Map<String, String>> getSiDoList() throws SQLException;
	public List<Map<String, String>> getGuGunList(String siDoDongCode) throws SQLException;
	public List<Map<String, String>> getDongList(String guGunDongCode) throws SQLException;
	
}
