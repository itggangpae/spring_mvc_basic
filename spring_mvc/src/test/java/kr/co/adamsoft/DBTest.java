package kr.co.adamsoft;

import java.sql.Connection;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.co.adamsoft.dao.SpringUserMapper;
import kr.co.adamsoft.domain.SpringUser;

//JUnit 과 Spring-Test를 같이 사용하겠다는 설정
@RunWith(SpringJUnit4ClassRunner.class)
//Spring Bean Configuration 파일의 내용을 실행하겠다는 설정
@ContextConfiguration(locations =
	{"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class DBTest {
	@Autowired
	private DataSource ds;
	
	@Test
	public void connectionTest() {
		//여기서 Exception 이 발생하면
		//DataSource 의 property 이름 문제나 
		//driverClassName(MySQL 의존성 설정 문제 포함) 문제
		System.out.println("데이터베이스:" + ds);
		
		//데이터베이스 연결 테스트
		//try() 안에서 생성한 자원은 close를 호출하지 않아도 자동 해제
		try(Connection con = ds.getConnection()){
			System.out.println(con);
		}catch(Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
	}
	
	//@Autowired
	//private SqlSession sqlSession;
	
	//@Test
	//public void mybatisTest() {
		//System.out.println("MyBatis 객체:" + sqlSession);
	//}
	
	@Autowired
	private SpringUserMapper springUserMapper;
	
	//@Test
	public void emailCheckTest() {
		//존재하는 경우는 자신의 이메일 리턴
		System.out.println(
				springUserMapper.emailCheck("ggangpae1@gmail.com"));
		//존재하지 않는 경우는 null
		System.out.println(
				springUserMapper.emailCheck("ggangpae2@gmail.com"));
		
	}
	
	//@Test
	public void nicknameCheckTest() {
		//존재하는 경우는 자신의 닉네임 리턴
		System.out.println(
				springUserMapper.nicknameCheck("군계"));
		//존재하지 않는 경우는 null
		System.out.println(
				springUserMapper.nicknameCheck("싸움닭"));
			
	}
	
	@Test
	public void joinTest() {
		SpringUser user = new SpringUser();
		user.setEmail("ggangpae2@gmail.com");
		user.setPw("1234");
		user.setNickname("싸움닭");
		user.setImage("default.png");
		
		//insert, delete, update 는 영향받은 행의 개수가 리턴됨
		System.out.println(springUserMapper.join(user));
	}
	
}