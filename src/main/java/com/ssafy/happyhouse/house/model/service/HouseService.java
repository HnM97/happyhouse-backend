package com.ssafy.happyhouse.house.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.house.model.House;
import com.ssafy.happyhouse.region.Dong;
import com.ssafy.happyhouse.region.GuGun;
import com.ssafy.happyhouse.region.SiDo;

public interface HouseService {
	public List<House> selectApt(String aptCode) throws SQLException;
	public List<House> selectDong(String dongCode) throws SQLException;
	public List<House> selectDongYM(String dongCode, int dealYear, int dealMonth) throws SQLException;
	public List<House> searchByCondition(Map<String, Object> map) throws SQLException;
	public List<Map<String, String>> getSiDoList() throws SQLException;
	public List<Map<String, String>> getGuGunList(String siDoDongCode) throws SQLException;
	public List<Map<String, String>> getDongList(String guGunDongCode) throws SQLException;
	public String getRegFromKeyword(String keyword) throws SQLException;

	public List<Map<String, String>> getAllGuGunList() throws SQLException;

	public List<Map<String, String>> getAllDongList() throws SQLException;
}
