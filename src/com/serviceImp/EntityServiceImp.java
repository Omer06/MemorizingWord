package com.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.EntityDao;
import com.service.EntityService;

@Service("entityServiceImp")
public class EntityServiceImp implements EntityService{

	@Autowired
	EntityDao entityDaoImp;
	
	@Override
	public List<?> getListBy(String entityName) {
		// TODO Auto-generated method stub
		return entityDaoImp.getListBy(entityName);
	}

	@Override
	public boolean delete(Object entity) {
		// TODO Auto-generated method stub
		return entityDaoImp.delete(entity);
	}

	@Override
	public boolean saveOrUpdate(Object entity) {
		// TODO Auto-generated method stub
		return entityDaoImp.saveOrUpdate(entity);
	}

	@Override
	public boolean save(Object entity) {
		// TODO Auto-generated method stub
		return entityDaoImp.save(entity);
	}

	@Override
	public boolean update(Object entity) {
		// TODO Auto-generated method stub
		return entityDaoImp.update(entity);
	}

	@Override
	public List<?> getListBy(String entityName, String descField) {
		// TODO Auto-generated method stub
		return entityDaoImp.getListBy(entityName, descField);
	}

	@Override
	public Object getEntityBy(Class<?> clazz, int id) {
		// TODO Auto-generated method stub
		return entityDaoImp.getEntityBy(clazz, id);
	}
	
	

}
