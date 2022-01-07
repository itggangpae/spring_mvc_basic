package kr.co.adamsoft.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.adamsoft.dao.SpringUserMapper;
import kr.co.adamsoft.domain.SpringUser;
import kr.co.adamsoft.util.CryptoUtil;

@Service
public class InterceptorUserServiceImpl implements InterceptorUserService {
	@Autowired
	private SpringUserMapper springUserDao;

	@Override
	public SpringUser login(HttpServletRequest request, HttpServletResponse response) {
		SpringUser springUser = null;

		//파라미터 읽어오기
		String email = request.getParameter("email");
		String pw = request.getParameter("userpw");

		//로그인 처리를 위해서 모든 User 찾아오기
		List<SpringUser> list = springUserDao.login();
		//System.out.println(list);
		
		//로그인 처리
		try {
			for(SpringUser user : list) {
			
				if(CryptoUtil.decryptAES256(
						user.getEmail(), "itggangpae").equals(email)
						&& BCrypt.checkpw(pw, user.getPw())) {
					springUser = new SpringUser();
					springUser.setEmail(email);
					springUser.setNickname(user.getNickname());
					springUser.setImage(user.getImage());
					break;
				}
			}
		}
		catch(Exception e) {
			System.out.println(e.getLocalizedMessage());
		}


		return springUser;
	}

}
