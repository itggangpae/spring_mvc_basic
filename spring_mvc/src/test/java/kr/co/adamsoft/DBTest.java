package kr.co.adamsoft;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//JUnit 과 Spring-Test를 같이 사용하겠다는 설정
@RunWith(SpringJUnit4ClassRunner.class)
//Spring Bean Configuration 파일의 내용을 실행하겠다는 설정
@ContextConfiguration(locations =
	{"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class DBTest {

}
