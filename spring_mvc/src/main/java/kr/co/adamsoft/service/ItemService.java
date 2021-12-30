package kr.co.adamsoft.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.adamsoft.domain.Item;

public interface ItemService {
	public List<Item> getList(
			HttpServletRequest request, HttpServletResponse response);
}
