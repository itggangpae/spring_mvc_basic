package kr.co.adamsoft.service;

import kr.co.adamsoft.dao.SpringUserMapper;
import kr.co.adamsoft.domain.SpringUser;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Service
public class SpringUserServiceImpl implements SpringUserService {
	@Autowired
	private SpringUserMapper springUserDao;

	@Override
	public Map<String, Object> join(MultipartHttpServletRequest request, HttpServletResponse response) {
		//리턴할 결과를 저장한 인스턴스
		Map<String, Object> map = new HashMap<>();
		//결과 초기화
		//회원 가입 성공 여부
		map.put("result", false);
		//이메일 중복 검사 결과
		map.put("emailcheck", false);
		//닉네임 중복 검사 결과
		map.put("nicknamecheck", false);
		
		//파라미터를 읽기 - Controller에서 읽으면 하지 않아도 됨
		String email = request.getParameter("email");
		String pw = request.getParameter("pw");
		String nickname = request.getParameter("nickname");
		
		//파일의 경우
		MultipartFile image = request.getFile("image");
		//파일이 여러 개 라면 getFiles를 호출하고 List로 받아야 합니다.
		//List<MultipartFile> images = request.getFiles("image");
		
		//작업을 수행해야 하면 작업을 수행
		
		//email 중복체크를 수행
		String emailresult = springUserDao.emailCheck(email);
		if(emailresult == null) {
			map.put("emailcheck", true);
		}
		
		//nickname 중복 검사 수행
		String nicknameresult = springUserDao.nicknameCheck(nickname);
		if(nicknameresult == null) {
			map.put("nicknamecheck", true);
		}
		
		//파일 이름을 생성 - 파일 이름이 중복되지 않도록 하기 위해서
		String originalFileName = image.getOriginalFilename();
		//변경된 이름
		String changeFileName = 
				UUID.randomUUID().toString() + originalFileName;
		
		//이메일 과 닉네임 중복검사를 통과한 경우 회원가입
		if(emailresult == null && nicknameresult == null) {
			//Repository 의 메서드를 호출해야 하면 필요한 파라미터를 생성
			SpringUser springUser = new SpringUser();
			springUser.setEmail(email);
			springUser.setPw(pw);
			springUser.setNickname(nickname);
			springUser.setImage(changeFileName);
			//Repository 메서드를 호출 - 회원 가입
			int result = springUserDao.join(springUser);
			
			//성공하면 
			if(result > 0) {
				//파일 업로드
				String uploadPath = 
						request.getServletContext().getRealPath(
							"/profile");
				String filePath = uploadPath + "/" + changeFileName;
				File f = new File(filePath);
				try {
					image.transferTo(f);
					map.put("result", true);
				}catch(Exception e) {
					System.out.println(e.getLocalizedMessage());
				}
				
			}
		}
		
		
		
		
		return map;
	}
	

}












