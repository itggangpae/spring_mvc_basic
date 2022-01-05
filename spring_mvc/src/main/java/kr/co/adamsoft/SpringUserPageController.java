package kr.co.adamsoft;

import kr.co.adamsoft.service.SpringUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SpringUserPageController {
	@Autowired
	private SpringUserService springUserService;

	@GetMapping("/user/join")
	public String join() {
		return "user/join";
	}
}
