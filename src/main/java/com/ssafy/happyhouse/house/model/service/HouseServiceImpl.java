package com.ssafy.happyhouse.house.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.happyhouse.house.model.House;
import com.ssafy.happyhouse.house.model.dao.HouseDao;
import com.ssafy.happyhouse.region.Dong;
import com.ssafy.happyhouse.region.GuGun;
import com.ssafy.happyhouse.region.SiDo;

@Service
public class HouseServiceImpl implements HouseService {
	HouseDao houseDao;
	
	@Autowired
	public HouseServiceImpl(HouseDao houseDao) {
		super();
		this.houseDao = houseDao;
	}

	@Override
	public List<House> selectDong(String dongCode) throws SQLException {
		return houseDao.selectDong(dongCode);
	}
	
	@Override
	public List<House> selectDongYM(String dongCode, int dealYear, int dealMonth) throws SQLException {
		return houseDao.selectDongYM(dongCode, dealYear, dealMonth);
	}

	@Override
	public List<Map<String, String>> getSiDoList() throws SQLException {
		return houseDao.getSiDoList();
	}

	@Override
	public List<Map<String, String>> getGuGunList(String siDoDongCode) throws SQLException {
		return houseDao.getGuGunList(siDoDongCode);
	}

	@Override
	public List<Map<String, String>> getDongList(String guGunDongCode) throws SQLException {
		return houseDao.getDongList(guGunDongCode);
	}

	@Override
	public List<House> selectApt(String aptCode) throws SQLException {
		return houseDao.selectApt(aptCode);
	}

	@Override
	public List<House> searchByCondition(Map<String,Object> map) throws SQLException {
		return houseDao.searchByCondition(map);
	}

	@Override
	public List<Map<String, String>> getAllGuGunList() throws SQLException {
		return houseDao.getAllGuGunList();
	}

	@Override
	public List<Map<String, String>> getAllDongList() throws SQLException {
		return houseDao.getAllDongList();
	}

	@Override
	public String getRegFromKeyword(String keyword) throws SQLException {
		List<Map<String, String>> sido = getSiDoList();
		for(Map<String,String> s: sido){
			if(s.get("sidoName").contains(keyword)){
				return s.get("dongCode");
			}
		}

		List<Map<String, String>> gugun = getAllGuGunList();

		for(Map<String,String> g: gugun){
			if(!g.containsKey("gugunname")) continue;
			if(g.get("gugunname").contains(keyword)){
				return g.get("dongcode");
			}
		}
		List<Map<String, String>> dong = getAllDongList();
		for(Map<String,String> d: dong) {
			if(!d.containsKey("dongname")) continue;
			if (d.get("dongname").contains(keyword)) {
				return d.get("dongcode");
			}
		}
		return "none";
	}
}
