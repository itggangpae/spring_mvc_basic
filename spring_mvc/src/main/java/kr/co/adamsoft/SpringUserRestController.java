package kr.co.adamsoft;

import kr.co.adamsoft.service.SpringUserService;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

//데이터를 제공하기 위한 Controller
@RestController
public class SpringUserRestController {
	@Autowired
	private SpringUserService springUserService;

	//회원 가입 요청을 처리할 메서드
	@PostMapping("/user/join")
	public Map<String, Object> join(
			MultipartHttpServletRequest request, 
			HttpServletResponse response){
		Map<String, Object> map = springUserService.join(request, response);
		System.out.println(map);
		return map;
	}

	//이메일 중복 검사 요청을 처리할 메서드
	@GetMapping("/user/emailcheck")
	public Map<String, Object> emailCheck(
			HttpServletRequest request, 
			HttpServletResponse response){
		return springUserService.emailCheck(request, response);
	}

	//닉네임 중복 검사 요청을 처리할 메서드
	@GetMapping("/user/nicknamecheck")
	public Map<String, Object> nicknameCheck(
			HttpServletRequest request, 
			HttpServletResponse response){
		return springUserService.nicknameCheck(request, response);
	}
	
	@PostMapping("/user/login")
	public Map<String, Object> login(
			HttpServletRequest request, 
			HttpServletResponse response){
		return springUserService.login(request, response);
	}
	
}










