package kr.co.adamsoft.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import kr.co.adamsoft.domain.SpringUser;

@Component
public class LoginInterceptor implements HandlerInterceptor{
	//Controller 가 요청을 처리하기 전에 호출되는 메서드
	@Override
	public boolean preHandle(
			HttpServletRequest request,
			HttpServletResponse response,
			Object Handler) {
		//로그인 처리 요청을 하면 기존 로그인 정보를 삭제하기
		if(request.getMethod().equals("POST")) {
			request.getSession().removeAttribute("LOGIN");
		}
		
		return true;
		
	}
	
	//Controller가 예외 없이 정상적으로 처리한 경우 호출되는 메서드
	@Override
	public void postHandle(
			HttpServletRequest request,
			HttpServletResponse response,
			Object Handler, ModelAndView modelAndView) 
	throws Exception{
		//POST 요청이 오면
		if(request.getMethod().equals("POST")) {
			SpringUser springUser = 
				(SpringUser)request.getSession()
					.getAttribute("LOGIN");
			if(springUser != null) {
				Object dest = request.getSession().getAttribute("dest");
				response.sendRedirect(
							dest!=null ? (String)dest : "/");
				
			}
			
		}
	}
}







