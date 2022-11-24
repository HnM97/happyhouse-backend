package com.ssafy.happyhouse.notice.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.notice.model.Notice;
import com.ssafy.happyhouse.notice.model.NoticeParameter;
import com.ssafy.happyhouse.notice.model.service.NoticeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/notice")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST })
@Api(tags = "공지사항 API")
public class NoticeController {
	
	private final Logger logger = LoggerFactory.getLogger(NoticeController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	private static final String UNDEFINED = "undefined";
	
	
	private NoticeService noticeService;
	private Map<String, String> map;
	
	@Autowired
	public NoticeController(NoticeService noticeService) {
		logger.info("NoticeController 생성자 호출!!!");
		this.noticeService = noticeService;
	}

	@ApiOperation(value = "공지사항 목록", notes = "공지사항의 전체 목록을 반환해 줍니다.", response = List.class)
	@ApiResponses({ @ApiResponse(code = 200, message = "공지사항목록 OK!!"), @ApiResponse(code = 404, message = "페이지없어!!"),
		@ApiResponse(code = 500, message = "서버에러!!") })
	@GetMapping("/notices")
	public ResponseEntity<?> list(@ApiParam(value = "게시글을 얻기위한 부가정보.", required = true) NoticeParameter noticeParameter) throws Exception {
		
		Map<String, Object> responseMap = noticeService.makePageNavigation(noticeParameter);
		
		try {
			logger.info("listArticle - 호출");
			List<Notice> list = noticeService.listNotice(noticeParameter);
			logger.debug("Notice list size : {}", list.size());
			
			responseMap.put("list", list);
			responseMap.put("message", SUCCESS);
			return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
		}
		catch (Exception e) {
			return exceptionHandling(e);
		}
	}
//	// interceptor 해야됨
//	@ApiOperation(value = "공지사항 목록", notes = "공지사항의 전체 목록을 반환해 줍니다.")
//	@ApiResponses({ @ApiResponse(code = 200, message = "공지사항목록 OK!!"), @ApiResponse(code = 404, message = "페이지없어!!"),
//			@ApiResponse(code = 500, message = "서버에러!!") })
//	@GetMapping("/notices")
//	private ResponseEntity<?> list(Map<String, String> map) {
//	
//		logger.debug("list parameter : {}", map);
//		
//		
//		Map<String, Object> responseMap = new HashMap<String, Object>();
//		int maxPgNo = 1;
//		try {				
//			List<Notice> list = noticeService.listNotice(map);
//			logger.info("목록 조회 완료");
//			
//			if (list != null && !list.isEmpty()) {
////				PageNavigation pageNavigation = noticeService.makePageNavigation(map);
////				int cnt = noticeService.totalNoticeCount(map);
////				maxPgNo = (cnt / SizeConstant.SIZE_PER_LIST) + 1;
////				
////				responseMap.put("list", list);
////				responseMap.put("maxPgNo", maxPgNo);
////				return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
//				return new ResponseEntity<List<Notice>>(list, HttpStatus.OK);
//			}
//			else {
//				return new ResponseEntity<Integer>(maxPgNo, HttpStatus.NO_CONTENT);
//			}
//			
//		} catch (Exception e) {
//			return exceptionHandling(e);
//		}
//	}

//	@GetMapping("/notices/search")
//	private ResponseEntity<?> search() {
//		
//		try {				
//			Notice notice = noticeService.search();
//			
//			return new ResponseEntity<Notice>(notice, HttpStatus.OK);
//		} 
//		catch (Exception e) {
//			return exceptionHandling(e);
//		}
//	}
	
	@ApiOperation(value = "공지사항 작성", notes = "공지사항을 등록합니다.")
	@PostMapping("/notices")
	private ResponseEntity<?> write(@RequestBody Notice notice) {
		
		logger.debug("write Notice : {}", notice);
		
		try {				
//				Notice notice = new Notice();
//				for(int i=1;i<=200;i++) {
//					notice.setUserId(user.getUserId());
//					notice.setSubject(request.getParameter("subject")+i);
//					notice.setContent(request.getParameter("content")+i);
//					noticeService.writeNotice(notice);
//				}
			if (notice.getUserId() != null && notice.getSubject() != null && notice.getContent() != null) {
				noticeService.writeNotice(notice);
				return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);				
			}
			else {
				return new ResponseEntity<String>(FAIL, HttpStatus.BAD_REQUEST);
			}
		} 
		catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@ApiOperation(value = "공지사항 조회", notes = "공지사항을 조회합니다.")
	@GetMapping("/notices/{articleno}")
	private ResponseEntity<?> view(@PathVariable("articleno") int noticeNo) {
		
		logger.debug("view noticeNo : {}", noticeNo);
		
		try {
			Notice notice = noticeService.getNotice(noticeNo);
			noticeService.updateHit(noticeNo);
			logger.info("조회완료");
//			request.setAttribute("article", notice);
			return new ResponseEntity<Notice>(notice, HttpStatus.OK);
		} 
		catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@ApiOperation(value = "공지사항 수정", notes = "공지사항을 수정합니다.")
	@PutMapping("/notices")
//	private ResponseEntity<?> modify(@RequestParam("articleno") int noticeNo, String userId, String subject, String content) {
	private ResponseEntity<?> modify(@RequestBody  Map<String, Object> map) {
//		System.out.println("공지사항 수정" + map.get("articleno"));
		try {
			Notice notice = noticeService.getNotice(Integer.parseInt(map.get("articleno").toString()));
			
			logger.debug("modify Notice : {}", notice);
			
			if (notice != null) {
				notice.setSubject(String.valueOf(map.get("subject")));
				notice.setContent(String.valueOf(map.get("content")));
				noticeService.modifyNotice(notice);
				logger.info("수정완료");
				return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
			}
			else {
				return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
			}
			
		} 
		catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@ApiOperation(value = "공지사항 삭제", notes = "공지사항을 삭제합니다.")
	@DeleteMapping("/notices")
	private ResponseEntity<?> delete(@RequestParam("articleno") int noticeNo, int pgNo) {
		
		logger.debug("delete noticeNo : {}", noticeNo);
		
		try {
			noticeService.deleteNotice(noticeNo);
			logger.info("삭제완료");
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		} 
		catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	private ResponseEntity<?> exceptionHandling(Exception e) {
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
