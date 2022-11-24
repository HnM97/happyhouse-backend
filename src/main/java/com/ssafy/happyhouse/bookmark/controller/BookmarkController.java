package com.ssafy.happyhouse.bookmark.controller;

import com.ssafy.happyhouse.bookmark.model.Bookmark;
import com.ssafy.happyhouse.bookmark.model.service.BookmarkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;



import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;
import java.util.Map;

@RestController
@Api(tags= "북마크 API")
@RequestMapping("/bookmark")
public class BookmarkController {
    private static final Logger logger = LoggerFactory.getLogger(BookmarkController.class);
    BookmarkService bookmarkService;
    @Autowired
    public BookmarkController(BookmarkService bookmarkService) {
        this.bookmarkService = bookmarkService;
    }

    @ApiOperation(value = "북마크 설정", notes = "특정 유저가 북마크를 설정합니다.")
    @ApiResponses({ @ApiResponse(code = 200, message = "북마크 설정 OK!!"), @ApiResponse(code = 404, message = "페이지없어!!"),
            @ApiResponse(code = 500, message = "서버에러!!") })
    @PostMapping
    private ResponseEntity<?> create(@RequestBody Map<String, String> map) {
        logger.info(map.get("userId") + " " + map.get("aptCode"));
        String dongCode = map.get("aptCode").substring(0,8);
            try{
                bookmarkService.createBookmark(map.get("userId"), map.get("aptCode"), dongCode);
                return new ResponseEntity<Void>(HttpStatus.OK);
            } catch (Exception e){
                e.printStackTrace();
                return exceptionHandling(e);
        }
    }
    @ApiOperation(value = "북마크 목록 불러오기", notes = "특정 유저의 북마크 목록을 가져옵니다.")
    @ApiResponses({ @ApiResponse(code = 200, message = "북마크 리스트 OK!!"), @ApiResponse(code = 404, message = "페이지없어!!"),
            @ApiResponse(code = 204, message = "결과 없음!!"),
            @ApiResponse(code = 500, message = "서버에러!!") })
    @GetMapping
    private ResponseEntity<?> list(String userId){
        try{
            List<Bookmark> bookMarkList = bookmarkService.selectAptById(userId);
            if(bookMarkList != null && !bookMarkList.isEmpty()){
                return new ResponseEntity<List<Bookmark>>(bookMarkList, HttpStatus.OK);
            } else{
                return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e){
            return exceptionHandling(e);
        }
    }

    @ApiOperation(value = "북마크 해제", notes = "특정 유저가 북마크를 해제합니다.")
    @ApiResponses({ @ApiResponse(code = 200, message = "북마크 해제 OK!!"), @ApiResponse(code = 404, message = "페이지없어!!"),
            @ApiResponse(code = 500, message = "서버에러!!") })
    @DeleteMapping
    private ResponseEntity<?> delete(String userId, String aptCode){
        try{
            bookmarkService.deleteBookmark(userId, aptCode);
            return new ResponseEntity<String>(HttpStatus.OK);
        } catch (Exception e){
            return exceptionHandling(e);
        }
    }

    private ResponseEntity<?> exceptionHandling(Exception e) {
        return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

