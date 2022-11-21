package com.ssafy.happyhouse.interest.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.house.controller.HouseController;
import com.ssafy.happyhouse.interest.model.Interest;
import com.ssafy.happyhouse.interest.model.service.InterestService;


import ch.qos.logback.classic.Logger;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@Api(tags="관심지역 API")
@RequestMapping("/interest")	
public class InterestController{
	private static final Logger logger = (Logger) LoggerFactory.getLogger(InterestController.class);
	private InterestService interestService;   

	@Autowired
	public InterestController(InterestService interestService) {
		super();
		this.interestService = interestService;
	}
	//Interceptor 필요
	@ApiOperation(value = "지역 목록", notes = "지역 번호에 따라 지역 이름을 가져옵니다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "지역 이름 OK!!"), @ApiResponse(code = 404, message = "페이지없어!!"),
		@ApiResponse(code = 204, message = "결과 없음!!"), 
		@ApiResponse(code = 500, message = "서버에러!!") })
	@GetMapping("/view")
	private ResponseEntity<?> view(String regcode) {
		try {	
			String[] names = interestService.getNames(regcode);
			if(names != null && names.length > 0) {
				return new ResponseEntity<String[]>(names, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}



	// interceptor 필요
	@ApiOperation(value = "관심 지역 추가", notes = "관심 지역을 추가합니다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "관심 지역 등록 완료!!"), 
		@ApiResponse(code = 406, message = "등록 실패!!"),
		@ApiResponse(code = 500, message = "서버에러!!") })
	@PostMapping("/add")
	private ResponseEntity<?> add(@RequestBody Map<String, Object> map) throws IOException {
		try {	
			String userId = (String) map.get("userId");
			String regCode = (String) map.get("regCode");
			int cnt = interestService.countInterest(userId);
			int LIMIT_INTEREST = 10;
			String msg = "";
			if(cnt>=LIMIT_INTEREST) {
				msg = "관심 지역은 10개 까지만 추가가 가능합니다";
				return new ResponseEntity<String>(msg, HttpStatus.NOT_ACCEPTABLE);
			}
			else {
				interestService.addInterest(userId, regCode);
				return new ResponseEntity<Void>( HttpStatus.OK);
			}

		} catch (Exception e) {
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	// interceptor 필요
	@ApiOperation(value = "관심 지역 삭제", notes = "관심 지역을 삭제합니다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "관심 지역 삭제 완료!!"), 
		@ApiResponse(code = 500, message = "서버에러!!") })
	@DeleteMapping("/delete")
	private ResponseEntity<?> delete(@RequestBody Map<String, Object> map) throws IOException {
		
		try {
			String delInterest = (String) map.get("delInterest");
			String userId = (String) map.get("userId");
			
			String[] delList = delInterest.split(",");
			
			if(delList != null && delList.length > 0) {
				for(String regcode : delList) {
					logger.info(regcode);
					interestService.deleteInterest(userId, regcode);					
				}
				return new ResponseEntity<Void>(HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.OK);
			}


		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}


	// interceptor 필요
	@ApiOperation(value = "관심 지역 목록", notes = "관심 지역 목록을 가져옵니다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "관심 지역 목록 OK!!"), 
		@ApiResponse(code = 204, message = "결과 없음!!"),
		@ApiResponse(code = 500, message = "서버에러!!") })
	@GetMapping("/list/{userId}")
	private ResponseEntity<?> list(@PathVariable String userId) throws IOException {
		try {				
			List<Interest> list = interestService.getInterestList(userId);
			if(list != null & !list.isEmpty()) {
				return new ResponseEntity<List<Interest>>(list, HttpStatus.OK);
			} else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}




}
