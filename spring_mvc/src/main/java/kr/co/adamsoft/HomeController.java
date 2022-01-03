package kr.co.adamsoft;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



import kr.co.adamsoft.domain.Item;
import kr.co.adamsoft.domain.Member;
import kr.co.adamsoft.domain.User;
import kr.co.adamsoft.service.ItemService;


//빈이 자동 생성
@Controller
public class HomeController {
	@Autowired
	private ItemService itemService;
	
	// /로 요청이 GET 방식으로 오면 호출되는 메서드
	@RequestMapping(value = "/", method = RequestMethod.GET)
	//문자열을 리턴하면 이 이름이 뷰 이름이 되고 기본적으로 포워딩 됩니다.
	/// 요청이 오면 home 으로 포워딩 합니다.
	//home 으로 포워딩 할 때 ViewResolver 설정에 의해서 
	//WEB-INT/views/home.jsp 파일로 포워딩 합니다.
	public String home(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		//데이터를 저장해서 전달
		model.addAttribute(
			"list", itemService.getList(request, response));
		return "home";
	}
	
	@RequestMapping(value="/hello", method = RequestMethod.GET)
	public String hello() {
		return "hello";
	}
	
	//detail 요청의 가장 마지막 부분을 num에 대입
	@RequestMapping(value="/detail/{num}", method=RequestMethod.GET)
	//위의 num 부분을 @PathVariable 의 num 에 대입
	public void detail(@PathVariable int num) {
		System.out.println(num);
	}
	
	@RequestMapping(value="/param", method = RequestMethod.GET)
	public String param() {
		return "param";
	}
	
	/*
	 	//HttpServletRequest 객체를 이용한 파라미터 읽기
		//없는 파라미터 이름을 이용하면 null
		//파라미터에 입력된 값이 없는 경우는 ""
	@RequestMapping(value="/param", method=RequestMethod.POST)
	public String param(HttpServletRequest request) {
	
		String name = request.getParameter("name");
		String age = request.getParameter("age");
		String gender = request.getParameter("gender");
		String job = request.getParameter("job");
		
		System.out.println(name);
		System.out.println(age);
		System.out.println(gender);
		System.out.println(job);
		
		return "hello";
	}
	*/
	
	
	/*
	//@RequestParam을 이용하는 방법
	@RequestMapping(value="/param", method=RequestMethod.POST)
	public String param(
			@RequestParam("name")String name,
			@RequestParam("age") String age, 
			@RequestParam("gender") String gender, 
			@RequestParam("job") String job) {
		
		System.out.println(name);
		System.out.println(age);
		System.out.println(gender);
		System.out.println(job);
		
		return "hello";
	}
	*/
	
	//Command 객체를 이용하는 방법
	@RequestMapping(value="/param", method=RequestMethod.POST)
	public String param(User user) {
		System.out.println(user);
		return "hello";
	}
	
	//모든 요청 처리 결과에 전달하는 데이터를 생성해주는 메서드
	@ModelAttribute("map")
	public Map<String, Object> allPass(){
		Map<String, Object> map = new HashMap<>();
		map.put("parameter", "클라이언트가 서버에게 전달하는 데이터");
		map.put("attribute", "서버가 클라이언트에게 전달하는 데이터");
		return map;
	}
	
	@RequestMapping(value="/forward", method=RequestMethod.GET)
	public String forward(Model model) {
		//request.setAttribute("이름", 데이터) 와 동일한 효과
		model.addAttribute("data", "forwarding 할 때 데이터 전달");
		return "result";
	}
	
	@RequestMapping(value="/redirect", method=RequestMethod.GET)
	public String redirect(Model model,
			HttpSession session, RedirectAttributes rattr) {
		//redirect 의 경우는 이 문장은 아무런 의미가 없습니다.
		//이렇게 작성하면 request 에 저장되는데 redirect 하면 request는 다시 생성됩니다.
		//model.addAttribute("data", "forwarding 할 때 데이터 전달");
		
		//이 경우는 session을 초기화하거나 data를 삭제하지 않는 한 계속 유지
		session.setAttribute("data", "value");
		
		//이 경우는 세션에 저장했다가 한 번 redirect 하고 나면 자동 소멸됩니다.
		rattr.addFlashAttribute("attr", "value");
		
		//여기서 result는 Controller 에게 돌아오기 위한 URL 이어야 합니다.
		//어딘가에 result를 처리해주는 메서드가 있어야 합니다.
		//데이터에 변화가 생기면 특별한 경우가 아니고는 redirect
		return "redirect:result";
	}
	
	//상세보기 처리를 위한 메서드 - 파라미터를 이용하는 방식
	/*
	@RequestMapping(value="/detail.html")
	public String getItem(
			@RequestParam("itemid") Integer itemid, 
			Model model){
		//서비스 메서드 호출
		Item item = itemService.getItem(itemid);
		//출력할 데이터 저장
		model.addAttribute("item", item);
		//출력할 뷰 이름 리턴
		return "detail";
	}
	*/
	
	@RequestMapping(value="/detail.html/{itemid}")
	public String getItem(
			@PathVariable Integer itemid, 
			Model model){
		//서비스 메서드 호출
		Item item = itemService.getItem(itemid);
		//출력할 데이터 저장
		model.addAttribute("item", item);
		//출력할 뷰 이름 리턴
		return "detail";
	}
	
	//exception 요청이 발생했을 때 input.jsp를 출력하도록 해주는 메서드
	/*
	@RequestMapping(value="/exception", method=RequestMethod.GET)
	public String exception() {
		return "input";
	}
	*/
	
	//input.jsp 의 form에서 submit을 눌렀을 때 처리하도록 해주는 메서드
	//dividend 와 divisor 파라미터의 값을 읽어서 나누셈을 해서 Model 에 저장하고
	//result.jsp 파일에 그 내용을 출력하도록 하기
	@GetMapping("/cal")
	public String cal(@RequestParam("dividend") int dividend,
			@RequestParam("divisor") int divisor, Model model) {
		model.addAttribute("result", dividend/divisor);
		return "result";
	}
	
	//ArithmeticException 이 발생하면 출력할 페이지를 설정하는 메서드
	@ExceptionHandler(ArithmeticException.class)
	public String handleException(Exception e, Model model) {
		model.addAttribute("content", e.getLocalizedMessage());
		return "error/exception";
	}
	
	//message 요청이 Get 방식으로 오면 처리하는 메서드
	@GetMapping("/message")
	public String message(
			@ModelAttribute("member") Member member) {
		return "loginform";
	}
	
	//모든 결과 페이지에 loginTypes 라는 이름의 메서드의 리턴값이 전달
	@ModelAttribute("loginTypes")
	public List<String> loginTypes(){
		List<String> list = new ArrayList<>();
		list.add("일반회원");
		list.add("VIP회원");
		list.add("관리자");
		return list;
	}
}




