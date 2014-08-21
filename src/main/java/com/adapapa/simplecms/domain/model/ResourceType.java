package com.adapapa.simplecms.domain.model;

import com.dreammore.framework.user.model.Enumable;

public enum ResourceType implements Enumable {
	ORIGINAL("正本资源"), COMMENT("跟帖资源"), ;

	private String value;

	private ResourceType(String value) {
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