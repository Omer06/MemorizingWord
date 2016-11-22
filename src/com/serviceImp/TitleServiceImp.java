package com.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dao.TitleDao;
import com.service.TitleService;

@Service
public class TitleServiceImp implements TitleService{

	@Autowired
	TitleDao titleDaoImp;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List getListBy(String entityName) {
		// TODO Auto-generated method stub
		return titleDaoImp.getListBy(entityName);
	}

	@Override
	public boolean delete(Object entity) {
		// TODO Auto-generated method stub
		return titleDaoImp.delete(entity);
	}

	@Override
	public boolean saveOrUpdate(Object entity) {
		// TODO Auto-generated method stub
		return titleDaoImp.saveOrUpdate(entity);
	}

	@Override
	public boolean save(Object entity) {
		// TODO Auto-generated method stub
		return titleDaoImp.save(entity);
	}

	@Override
	public boolean update(Object entity) {
		// TODO Auto-generated method stub
		return titleDaoImp.update(entity);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List getListBy(String entityName, String descField) {
		// TODO Auto-generated method stub
		return titleDaoImp.getListBy(entityName, descField);
	}

	@Override
	public Object getEntityBy(Class<?> clazz, int id) {
		// TODO Auto-generated method stub
		return titleDaoImp.getEntityBy(clazz, id);
	}

	@Override
	public List<?> getListBy(String entityName, String username, String descField) {
		// TODO Auto-generated method stub
		return titleDaoImp.getListBy(entityName, username, descField);
	}

	@Override
	public boolean rename(int titleId, String newName) {
		// TODO Auto-generated method stub
		return titleDaoImp.rename(titleId, newName);
	}

}
