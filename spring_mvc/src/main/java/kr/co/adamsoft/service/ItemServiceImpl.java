package kr.co.adamsoft.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.co.adamsoft.dao.ItemDao;
import kr.co.adamsoft.dao.ItemHibernateDao;
import kr.co.adamsoft.dao.ItemMapper;
import kr.co.adamsoft.domain.Item;

@Service
public class ItemServiceImpl implements ItemService {
	@Autowired
	//private ItemDao itemDao;
	private ItemMapper itemDao;
	
	@Autowired
	private ItemHibernateDao itemHibernateDao;

	@Override
	@Transactional
	public List<Item> getList(
			HttpServletRequest request, 
			HttpServletResponse response) {
		//return itemDao.getList();
		return itemHibernateDao.getList();
	}

	@Override
	@Transactional
	public Item getItem(Integer itemid) {
		//return itemDao.getItem(itemid);
		return itemHibernateDao.getItem(itemid);
	}
}
