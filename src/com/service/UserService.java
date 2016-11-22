package com.service;


public interface UserService extends EntityService{

	public boolean isHasByAttribute(String columnName , String value);
	
	public boolean updatePassword(String username , String password);
	
	public boolean isHasByAttributes(String columnName1 , String value1 , String columnName2 , String value2);
	
	public String getPassword(String username);
}
