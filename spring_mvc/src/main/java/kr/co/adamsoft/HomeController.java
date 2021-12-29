package kr.co.adamsoft;

import java.util.Locale;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


//빈이 자동 생성
@Controller
public class HomeController {
	
	// /로 요청이 GET 방식으로 오면 호출되는 메서드
	@RequestMapping(value = "/", method = RequestMethod.GET)
	//문자열을 리턴하면 이 이름이 뷰 이름이 되고 기본적으로 포워딩 됩니다.
	/// 요청이 오면 home 으로 포워딩 합니다.
	//home 으로 포워딩 할 때 ViewResolver 설정에 의해서 
	//WEB-INT/views/home.jsp 파일로 포워딩 합니다.
	public String home(Locale locale, Model model) {
		return "home";
	}
	
	@RequestMapping(value="/hello", method = RequestMethod.GET)
	public String hello() {
		return "hello";
	}
	
}







