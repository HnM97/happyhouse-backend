package com.ssafy.happyhouse.house.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ssafy.happyhouse.house.model.House;
import com.ssafy.happyhouse.house.model.service.HouseService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@RestController
@Api(tags="아파트  API")
@RequestMapping("/house")
public class HouseController{
	private static final Logger logger = LoggerFactory.getLogger(HouseController.class);
	HouseService houseService;

	@Autowired   
	public HouseController(HouseService houseService) {
		super();
		this.houseService = houseService;
	}

	// Interceptor 필요
	@ApiOperation(value = "아파트 거래내역", notes = "특정 아파트의 거래내역을 가져옵니다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "아파트 거래내역 OK!!"), @ApiResponse(code = 404, message = "페이지없어!!"),
		@ApiResponse(code = 204, message = "결과 없음!!"), 
		@ApiResponse(code = 500, message = "서버에러!!") })
	@GetMapping("/view")
	private ResponseEntity<?> view(String aptCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		logger.info("view called!!!");
		try {
			List<House> aptInfos = houseService.selectApt(aptCode);

			if(aptInfos != null && !aptInfos.isEmpty()) {
				House apt = new House();
				apt.setApartmentName(aptInfos.get(0).getApartmentName());
				apt.setBuildYear(aptInfos.get(0).getBuildYear());
				apt.setRoadName(aptInfos.get(0).getRoadName());
				apt.setJibun(aptInfos.get(0).getJibun());
				map.put("aptInfos", aptInfos);
				map.put("apt", apt);
				return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}



	// Interceptor 해야됨
	@ApiOperation(value = "아파트 목록", notes = "특정 동의 모든 아파트 목록을 보여줍니다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "아파트 목록 OK!!"), @ApiResponse(code = 404, message = "페이지없어!!"),
		@ApiResponse(code = 204, message = "결과 없음!!"), 
		@ApiResponse(code = 500, message = "서버에러!!") })
	@GetMapping("/list")
	private ResponseEntity<?> list(String regcode) {
		try {			
			logger.info("list");
			logger.info(regcode);
			List<House> list = houseService.selectDong(regcode);
			if(list != null && !list.isEmpty()) {
				return new ResponseEntity<List<House>>(list, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@ApiOperation(value = "검색 조건 설정", notes = "시,군구,동에 대한 정보를 가져옵니다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "시, 군구, 동 OK!!"), @ApiResponse(code = 404, message = "페이지없어!!"),
		@ApiResponse(code = 500, message = "서버에러!!") })
	@GetMapping("/option")
	private ResponseEntity<?> option(String regcode) {
		logger.info(regcode);
		List<Map<String,String>> list = null;
		try {
			if("init".equals(regcode)) {
				list =  houseService.getSiDoList();
			}
			else if(regcode.length()==2) {
				list = houseService.getGuGunList(regcode);
			}
			else if(regcode.length()==5) {
				list = houseService.getDongList(regcode);
			}

			return new ResponseEntity<List<Map<String,String>>>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
