package kr.co.adamsoft.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.adamsoft.dao.ItemDao;
import kr.co.adamsoft.domain.Item;

@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	private ItemDao itemDao;

	@Override
	public List<Item> getList(
			HttpServletRequest request, 
			HttpServletResponse response) {
		return itemDao.getList();
	}
}
