package kr.co.adamsoft.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import kr.co.adamsoft.domain.SpringUser;

@Component
public class AuthInterceptor implements HandlerInterceptor{
	//Controller 가 요청을 처리하기 전에 호출되는 메서드
	@Override
	public boolean preHandle(
			HttpServletRequest request,
			HttpServletResponse response,
			Object Handler) {

		//로그인 여부를 확인하기 위해서 Session에 저장된 데이터가 가져오기
		SpringUser springUser = 
				(SpringUser)request.getSession().getAttribute("LOGIN");
		if(springUser == null) {
			try {
				//어떤 요청이 있었는지 확인
				String uri = request.getRequestURI();
				//파라미터 확인
				String query = request.getQueryString();
				if(query == null || query.equals("null")) {
					query = "";
				}else {
					query = "?" + query;
				}
				//GET 방식인 경우 저장
				if(request.getMethod().equals("GET")) {
					//로그인 페이지로 오게된 요청을 dest 라는 항목에 저장
					request.getSession().setAttribute("dest", uri + query);
				}
				
				response.sendRedirect("/interceptor/login");
			}catch(Exception e) {
				System.out.println(e.getLocalizedMessage());
			}
			return false;
		}

		return true;
	}

}
