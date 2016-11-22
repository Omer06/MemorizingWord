package com.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dao.UserDao;
import com.service.UserService;

@Service
public class UserServiceImp implements UserService{

	@Autowired
	UserDao userDaoImp;
	
	@Override
	public List<?> getListBy(String entityName) {
		// TODO Auto-generated method stub
		return userDaoImp.getListBy(entityName);
	}

	@Override
	public boolean delete(Object entity) {
		// TODO Auto-generated method stub
		return userDaoImp.delete(entity);
	}

	@Override
	public boolean saveOrUpdate(Object entity) {
		// TODO Auto-generated method stub
		return userDaoImp.saveOrUpdate(entity);
	}

	@Override
	public boolean save(Object entity) {
		// TODO Auto-generated method stub
		return userDaoImp.save(entity);
	}

	@Override
	public boolean update(Object entity) {
		// TODO Auto-generated method stub
		return userDaoImp.update(entity);
	}

	@Override
	public List<?> getListBy(String entityName, String descField) {
		// TODO Auto-generated method stub
		return userDaoImp.getListBy(entityName, descField);
	}

	@Override
	public Object getEntityBy(Class<?> clazz, int id) {
		// TODO Auto-generated method stub
		return userDaoImp.getEntityBy(clazz, id);
	}

	@Override
	public boolean isHasByAttribute(String columnName, String value) {
		// TODO Auto-generated method stub
		return userDaoImp.isHasByAttribute(columnName, value);
	}

	@Override
	public boolean updatePassword(String username, String password) {
		// TODO Auto-generated method stub
		return userDaoImp.updatePassword(username, password);
	}

	@Override
	public boolean isHasByAttributes(String columnName1 , String value1 , String columnName2 , String value2) {
		// TODO Auto-generated method stub
		return userDaoImp.isHasByAttributes(columnName1, value1, columnName2, value2);
	}

	@Override
	public String getPassword(String username) {
		// TODO Auto-generated method stub
		return userDaoImp.getPassword(username);
	}

	

}
