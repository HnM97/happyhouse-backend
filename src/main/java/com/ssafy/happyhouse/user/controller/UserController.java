package com.ssafy.happyhouse.user.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.mindrot.jbcrypt.BCrypt;
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
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.user.model.User;
import com.ssafy.happyhouse.user.model.service.JwtServiceImpl;
import com.ssafy.happyhouse.user.model.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST })
@Api(tags = "회원 관리 API")
public class UserController extends HttpServlet {
	
	private final Logger logger = LoggerFactory.getLogger(UserController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private JwtServiceImpl jwtService;

	private UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		logger.info("UserController 생성자 호출!!!");
		this.userService = userService;
	}
	
	@ApiOperation(value = "회원 가입", notes = "회원가입을 합니다.")
	@PostMapping("/users")
	private ResponseEntity<?> join(@RequestBody User user) {
		user.setUserPwd(BCrypt.hashpw(user.getUserPwd(), BCrypt.gensalt()));		
		try {
			userService.joinMember(user);
			logger.info("회원가입 완료");
			User joinUser = userService.searchById(user.getUserId());
			return new ResponseEntity<User>(joinUser, HttpStatus.OK);
			
		} 
		catch (Exception e) {
			return exceptionHandling(e);
		}
	}


	@ApiOperation(value = "로그인", notes = "Access-token과 로그인 결과 메세지를 반환한다.", response = Map.class)
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(
//			@RequestBody @ApiParam(value = "로그인 시 필요한 회원정보(아이디(userId), 비밀번호(userPwd).", required = true) Map<String,String> map) {
			@RequestBody @ApiParam(value = "로그인 시 필요한 회원정보(아이디(userId), 비밀번호(userPwd).", required = true) User user) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		HttpStatus status = null;
		try {
			User loginUser = userService.login(user);
//			User loginUser = userService.searchById(map.get("userId"));
			logger.debug("로그인  : {}", loginUser);
			
//			if(loginUser != null && BCrypt.checkpw(map.get("userPwd"), loginUser.getUserPwd())) {
//				String token = jwtService.create("userId", loginUser.getUserId(), "access-token");// key, data, subject
//				logger.debug("로그인 토큰정보 : {}", token);
//				resultMap.put("access-token", token);
//				resultMap.put("userId", loginUser.getUserId());
//				resultMap.put("message", SUCCESS);
//				status = HttpStatus.ACCEPTED;
//			} 
			if (loginUser != null && BCrypt.checkpw(user.getUserPwd(), loginUser.getUserPwd())) {
				String accessToken = jwtService.createAccessToken("userid", loginUser.getUserId());// key, data
				String refreshToken = jwtService.createRefreshToken("userid", loginUser.getUserId());// key, data
				userService.saveRefreshToken(user.getUserId(), refreshToken);
				logger.debug("로그인 accessToken 정보 : {}", accessToken);
				logger.debug("로그인 refreshToken 정보 : {}", refreshToken);
				resultMap.put("access-token", accessToken);
				resultMap.put("refresh-token", refreshToken);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			}
			else {
				resultMap.put("message", FAIL);
				status = HttpStatus.ACCEPTED;
			}
		} 
		catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
//	@GetMapping("/users")
//	private ResponseEntity<?> login(String userId, String userPwd, String saveid) {
//		try {
//			User user = userService.loginMember(userId);
//			// BCrypt에서 salt를 가지고 있지 않기 때문에 로그인 시 입력된 비번과 암호문 비번 비교
//			if(user != null && BCrypt.checkpw(userPwd, user.getUserPwd())) { // 로그인 성공(id, pwd 일치!!!!)
//				
//				if("ok".equals(saveid)) { // 아이디 저장 체크 O.
//					Cookie cookie = new Cookie("ssafy_id", userId);
//					cookie.setMaxAge(60*60*24*365*40);
//					cookie.setPath(request.getContextPath());
//					
//					response.addCookie(cookie);
//				} else {
//					Cookie[] cookies = request.getCookies();
//					if(cookies != null) {
//						for(Cookie cookie : cookies) {
//							if(cookie.getName().equals("ssafy_id")) {
//								cookie.setMaxAge(0);
//								cookie.setPath(request.getContextPath());
//								
//								response.addCookie(cookie);
//								break;
//							}
//						}
//					}
//				}
//				
//				HttpSession session = request.getSession();
//				session.setAttribute("userinfo", user);
//				return "/interest?act=mvIndex";
//			} else { // 로그인 실패(id, pwd 불일치!!!!)
//				request.setAttribute("msg", "아이디 또는 비밀번호 확인 후 다시 로그인!!!");
//				return "/user/login.jsp";
//			}
//		} 
//		catch (Exception e) {
//			return exceptionHandling(e);
//		}
//	}
	

	@ApiOperation(value = "회원인증", notes = "회원 정보를 담은 Token을 반환한다.", response = Map.class)
	@GetMapping("/info/{userId}")
	public ResponseEntity<Map<String, Object>> getInfo(
			@PathVariable("userId") @ApiParam(value = "인증할 회원의 아이디.(userId)", required = true) String userId,
			HttpServletRequest request) {
//		logger.debug("userid : {} ", userId);
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.UNAUTHORIZED;
		if (jwtService.checkToken(request.getHeader("access-token"))) {
			logger.info("사용 가능한 토큰!!!");
			try {
//				로그인 사용자 정보.
				User user = userService.userInfo(userId);
				resultMap.put("userInfo", user);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			} 
			catch (Exception e) {
				logger.error("정보조회 실패 : {}", e);
				resultMap.put("message", e.getMessage());
				status = HttpStatus.INTERNAL_SERVER_ERROR;
			}
		} 
		else {
			logger.error("사용 불가능 토큰!!!");
			resultMap.put("message", FAIL);
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}


	@ApiOperation(value = "회원 정보 수정", notes = "회원 정보를 수정합니다.")
	@PutMapping("/users")
	private ResponseEntity<?> modify(User user) {
		user.setUserPwd(BCrypt.hashpw(user.getUserPwd(), BCrypt.gensalt()));
		try {
			userService.modifyMember(user);
			logger.info("회원 정보 수정 완료");
			User joinUser = userService.searchById(user.getUserId());
			return new ResponseEntity<User>(joinUser, HttpStatus.OK);
		} 
		catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@ApiOperation(value = "회원 탈퇴", notes = "회원을 목록에서 삭제합니다.")
	@DeleteMapping("/users")
	private ResponseEntity<?> delete(String userId) {
		try {
			userService.deleteMember(userId);
			logger.info("회원 탈퇴 완료");
			return new ResponseEntity<String>(userId, HttpStatus.OK);
		} 
		catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@ApiOperation(value = "로그아웃", notes = "회원 정보를 담은 Token을 제거한다.", response = Map.class)
	@GetMapping("/logout/{userid}")
	public ResponseEntity<?> removeToken(@PathVariable("userid") String userid) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		try {
			userService.deleRefreshToken(userid);
			resultMap.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
		} catch (Exception e) {
			logger.error("로그아웃 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);

	}

	@ApiOperation(value = "Access Token 재발급", notes = "만료된 access token을 재발급받는다.", response = Map.class)
	@PostMapping("/refresh")
	public ResponseEntity<?> refreshToken(@RequestBody User user, HttpServletRequest request)
			throws Exception {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		String token = request.getHeader("refresh-token");
		logger.debug("token : {}, memberDto : {}", token, user);
		if (jwtService.checkToken(token)) {
			if (token.equals(userService.getRefreshToken(user.getUserId()))) {
				String accessToken = jwtService.createAccessToken("userid", user.getUserId());
				logger.debug("token : {}", accessToken);
				logger.debug("정상적으로 액세스토큰 재발급!!!");
				resultMap.put("access-token", accessToken);
				resultMap.put("message", SUCCESS);
				status = HttpStatus.ACCEPTED;
			}
		} else {
			logger.debug("리프레쉬토큰도 사용불!!!!!!!");
			status = HttpStatus.UNAUTHORIZED;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	
	private ResponseEntity<?> exceptionHandling(Exception e) {
		return new ResponseEntity<String>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	

}
