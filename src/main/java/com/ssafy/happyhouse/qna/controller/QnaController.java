package com.ssafy.happyhouse.qna.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ssafy.happyhouse.qna.model.Memo;
import com.ssafy.happyhouse.qna.model.Qna;
import com.ssafy.happyhouse.qna.model.service.QnaService;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.util.PageNavigation;
import com.ssafy.happyhouse.util.SizeConstant;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/qna")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST })
@Api(tags = "Qna API")
public class QnaController {

    private final Logger logger = LoggerFactory.getLogger(com.ssafy.happyhouse.qna.controller.QnaController.class);

    private QnaService qnaService;
    private Map<String, String> map;

    @Autowired
    public QnaController(QnaService qnaService) {
        logger.info("QnaController 생성자 호출!!!");
        this.qnaService = qnaService;
    }



    // interceptor 해야됨
    @ApiOperation(value = "Qna 목록", notes = "Qna의 전체 목록을 반환해 줍니다.")
    @ApiResponses({ @ApiResponse(code = 200, message = "Qna 목록 OK!!"), @ApiResponse(code = 404, message = "페이지없어!!"),
            @ApiResponse(code = 500, message = "서버에러!!") })
    @GetMapping("/qnas")
    private ResponseEntity<?> list(Map<String, String> map) {

        logger.debug("list parameter : {}", map);


        Map<String, Object> responseMap = new HashMap<String, Object>();
        int maxPgNo = 1;
        try {
            List<Qna> list = qnaService.listQna(map);
            logger.info("목록 조회 완료");

            if (list != null && !list.isEmpty()) {
//				PageNavigation pageNavigation = qnaService.makePageNavigation(map);
//				int cnt = qnaService.totalNoticeCount(map);
//				maxPgNo = (cnt / SizeConstant.SIZE_PER_LIST) + 1;
//
//				responseMap.put("list", list);
//				responseMap.put("maxPgNo", maxPgNo);
//				return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
                return new ResponseEntity<List<Qna>>(list, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<Integer>(maxPgNo, HttpStatus.NO_CONTENT);
            }

        } catch (Exception e) {
            return exceptionHandling(e);
        }
    }

//	@GetMapping("/qnas/search")
//	private ResponseEntity<?> search() {
//
//		try {
//			Notice qna = qnaService.search();
//
//			return new ResponseEntity<Notice>(qna, HttpStatus.OK);
//		}
//		catch (Exception e) {
//			return exceptionHandling(e);
//		}
//	}

    @ApiOperation(value = "Qna 작성", notes = "Qna 등록합니다.")
    @PostMapping("/qnas")
    private ResponseEntity<?> write(Qna qna) {

        logger.debug("write Qna : {}", qna);

        try {
//				Qna qna = new Notice();
//				for(int i=1;i<=200;i++) {
//					qna.setUserId(user.getUserId());
//					qna.setSubject(request.getParameter("subject")+i);
//					qna.setContent(request.getParameter("content")+i);
//					qnaService.writeNotice(qna);
//				}
            qnaService.writeQna(qna);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
        catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @ApiOperation(value = "Qna 조회", notes = "Qna를 조회합니다.")
    @GetMapping("/qnas/{articleno}")
    private ResponseEntity<?> view(@PathVariable("articleno") int qnaNo) {

        logger.debug("view qnaNo : {}", qnaNo);

        try {
            Qna qna = qnaService.getQna(qnaNo);
            qnaService.updateHit(qnaNo);
            logger.info("조회완료");
//			request.setAttribute("article", qna);
            return new ResponseEntity<Qna>(qna, HttpStatus.OK);
        }
        catch (Exception e) {
            return exceptionHandling(e);
        }
    }


    @ApiOperation(value = "Qna 수정", notes = "Qna를 수정합니다.")
    @PutMapping("/qnas")
    private ResponseEntity<?> modify(@RequestParam("articleno") int qnaNo, String subject, String content) {
        try {
            Qna qna = qnaService.getQna(qnaNo);

            logger.debug("modify Qna : {}", qna);

            if (qna != null) {
                qna.setSubject(subject);
                qna.setContent(content);
                qnaService.modifyQna(qna);
                logger.info("수정완료");
                return new ResponseEntity<Void>(HttpStatus.OK);
            }
            else {
                return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
            }

        }
        catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @ApiOperation(value = "Qna 삭제", notes = "Qna를 삭제합니다.")
    @DeleteMapping("/qnas")
    private ResponseEntity<?> delete(@RequestParam("articleno") int qnaNo, int pgno) {

        logger.debug("delete qnaNo : {}", qnaNo);

        try {
            qnaService.deleteQna(qnaNo);
            logger.info("삭제완료");
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
        catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @ApiOperation(value = "Memo 등록", notes = "Qna에 Memo를 등록합니다.")
    @PostMapping("/qnas/memo")
    private ResponseEntity<?> writeMemo(@RequestParam("articleno") int qnaNo, Memo memo){
        logger.debug("write Qna : {}", memo);

        try {
//				Qna qna = new Notice();
//				for(int i=1;i<=200;i++) {
//					qna.setUserId(user.getUserId());
//					qna.setSubject(request.getParameter("subject")+i);
//					qna.setContent(request.getParameter("content")+i);
//					qnaService.writeNotice(qna);
//				}
            qnaService.writeMemo(qnaNo, memo);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
        catch (Exception e) {

            return exceptionHandling(e);
        }
    }
    @ApiOperation(value = "Memo 삭제", notes = "Qna의 특정 Memo를 삭제합니다.")
    @DeleteMapping("/qnas/{articleno}/{memoNo}")
    private ResponseEntity<?> deleteMemo(@RequestParam("articleno") int qnaNo, @RequestParam("memono") int memoNo, int pgno) {

        logger.debug("delete memoNo : {}", memoNo);

        try {
            qnaService.deleteMemo(qnaNo, memoNo);
            logger.info("삭제완료");
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
        catch (Exception e) {
            return exceptionHandling(e);
        }
    }

    @ApiOperation(value = "Memo 조회", notes = "특정 Qna의 Memo들을 조회합니다.")
    @GetMapping("/qnas/{articleno}/memos")
    private ResponseEntity<?> listMemo(@PathVariable("articleno") int qnaNo){
        logger.debug("list Memo of qnaNo : {}", qnaNo);
        try{
            List<Memo> list = qnaService.listMemo(qnaNo);
            if (list != null && !list.isEmpty()) {
//				PageNavigation pageNavigation = qnaService.makePageNavigation(map);
//				int cnt = qnaService.totalNoticeCount(map);
//				maxPgNo = (cnt / SizeConstant.SIZE_PER_LIST) + 1;
//
//				responseMap.put("list", list);
//				responseMap.put("maxPgNo", maxPgNo);
//				return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
                return new ResponseEntity<List<Memo>>(list, HttpStatus.OK);
            }
            else {
                return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e){
            e.printStackTrace();
            return exceptionHandling(e);
        }
    }


    private ResponseEntity<?> exceptionHandling(Exception e) {
        return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
