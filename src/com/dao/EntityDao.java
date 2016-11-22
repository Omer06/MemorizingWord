package com.dao;

import java.util.List;

public interface EntityDao {
	
	public List<?> getListBy(String entityName);

	public boolean delete(Object entity);

	public boolean saveOrUpdate(Object entity);
	
	public boolean save(Object entity);
	
	public boolean update(Object entity);

	public List<?> getListBy(String entityName , String descField);

	public Object getEntityBy(Class<?> clazz, int id);
	
}
