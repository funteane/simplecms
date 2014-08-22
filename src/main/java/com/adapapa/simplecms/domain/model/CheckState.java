package com.adapapa.simplecms.domain.model;

import com.dreammore.framework.user.model.Enumable;

public enum CheckState implements Enumable{
	
	DRAFT("草稿"),
	UNCHECKED("未审核"),
	PASSED("通过"),
	UNPASSED("未通过"),
	;
	
	private String value;
	
	private CheckState (String value) {
		this.value = value;
	}

	@Override
	public String getName() {
		return name();
	}

	@Override
	public String getValue() {
		return value;
	}
}
