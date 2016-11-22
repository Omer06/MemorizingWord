package com.entities;

public class StudyOptions {

	private String pageName;
	private int titleId;

	public StudyOptions() { }

	public StudyOptions(String pageName, int titleId) {
		super();
		this.pageName = pageName;
		this.titleId = titleId;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public int getTitleId() {
		return titleId;
	}

	public void setTitleId(int titleId) {
		this.titleId = titleId;
	}

	@Override
	public String toString() {
		return "StudyOptions [pageName=" + pageName + ", titleId=" + titleId + "]";
	}
	
}
