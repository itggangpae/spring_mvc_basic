package kr.co.adamsoft.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.adamsoft.domain.Item;

public interface ItemService {
	//전체 보기를 위한 메서드
	public List<Item> getList(
			HttpServletRequest request, HttpServletResponse response);
	//상세보기를 위한 메서드
	//파라미터를 Controller에서 읽도록 하기
	//Service 에서 읽는 경우에는 Integer 대신에 HttpServletRequest
	public Item getItem(Integer itemid);
	
}
