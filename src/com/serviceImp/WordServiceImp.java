package com.serviceImp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dao.WordDao;
import com.service.WordService;

@Service("wordServiceImp")
public class WordServiceImp implements WordService{
	
	@Autowired
	WordDao wordDaoImp;
	
	@Override
	public List<?> getListBy(String entityName) {
		// TODO Auto-generated method stub
		return wordDaoImp.getListBy(entityName);
	}

	@Override
	public boolean delete(Object entity) {
		// TODO Auto-generated method stub
		return wordDaoImp.delete(entity);
	}

	@Override
	public boolean saveOrUpdate(Object entity) {
		// TODO Auto-generated method stub
		return wordDaoImp.saveOrUpdate(entity);
	}

	@Override
	public boolean save(Object entity) {
		// TODO Auto-generated method stub
		return wordDaoImp.save(entity);
	}

	@Override
	public boolean update(Object entity) {
		// TODO Auto-generated method stub
		return wordDaoImp.update(entity);
	}

	@Override
	public List<?> getListBy(String entityName, String descField) {
		// TODO Auto-generated method stub
		return wordDaoImp.getListBy(entityName, descField);
	}

	@Override
	public Object getEntityBy(Class<?> clazz, int id) {
		// TODO Auto-generated method stub
		return wordDaoImp.getEntityBy(clazz, id);
	}

	@Override
	public boolean deleteById(int wordId) {
		// TODO Auto-generated method stub
		return wordDaoImp.deleteById(wordId);
	}

}
