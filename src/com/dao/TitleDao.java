package com.dao;

import java.util.List;

public interface TitleDao extends EntityDao{
	
	public List<?> getListBy(String entityName , String username , String descField);
	
	public boolean rename (int titleId , String newName);

}
