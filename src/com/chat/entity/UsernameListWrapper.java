package com.chat.entity;

import java.util.ArrayList;
import java.util.List;


public class UsernameListWrapper implements TrafficMeterials{
	
	private List<String> usernameList = new ArrayList<String>();
	
	public UsernameListWrapper(List<String> usernameList){
		this.usernameList = usernameList;
	}
	
	public List<String> getUsernameList() {
		return usernameList;
	}
	
	public void setUsernameList(List<String> usernameList) {
		this.usernameList = usernameList;
	}

	@Override
	public String toString() {
		return "UsernameListWrapper [usernameList=" + usernameList + "]";
	}
	
}
