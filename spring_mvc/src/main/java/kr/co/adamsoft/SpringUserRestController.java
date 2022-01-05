package kr.co.adamsoft;

import kr.co.adamsoft.service.SpringUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringUserRestController {
	@Autowired
	private SpringUserService springUserService;

}
