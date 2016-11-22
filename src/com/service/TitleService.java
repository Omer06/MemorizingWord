package com.service;

import java.util.List;

public interface TitleService extends EntityService{
	
	public List<?> getListBy(String entityName , String username , String descField);
	
	public boolean rename (int titleId , String newName);

}
