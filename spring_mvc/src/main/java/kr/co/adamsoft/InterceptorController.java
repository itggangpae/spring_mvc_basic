package kr.co.adamsoft;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import kr.co.adamsoft.domain.SpringUser;
import kr.co.adamsoft.service.InterceptorUserService;

@Controller
public class InterceptorController {
	@GetMapping("/interceptor/login")
	public String login() {
		return "interceptor/login";
	}
	
	@GetMapping("/interceptor/logout")
	public String logout(HttpSession session) {
		//세션 초기화
		session.invalidate();
		//메인 페이지로 리다이렉트
		return "redirect:/";
	}
	
	@GetMapping("/board/boardlist")
	public String boardlist() {
		return "board/boardlist";
	}
	
	@GetMapping("/board/boardwrite")
	public String boardwrite() {
		return "board/boardwrite";
	}
	
	@Autowired
	private InterceptorUserService interceptorUserService;
	
	@PostMapping("/interceptor/login")
	public void login(HttpServletRequest request,
			HttpServletResponse response, 
			HttpSession session) {
		//로그인 시도
		SpringUser springUser = 
				interceptorUserService.login(request, response);
		//System.out.println(springUser);
		//로그인 결과를 LOGIN 키를 이용해서 세션에 저장
		session.setAttribute("LOGIN", springUser);
	}
}







