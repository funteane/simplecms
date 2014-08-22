package com.adapapa.simplecms.domain.model;

import com.dreammore.framework.user.model.Enumable;
import com.dreammore.framework.user.model.Role;

public enum RoleInfo implements Role, Enumable {

	ADMINISTRATOR("网站管理人员"),  
	EDITOR("网站编辑人员"),
	SERVICER("网站服务人员"),
	;

	private String value;

	private RoleInfo(String value) {
		this.value = value;
	}

	public String getName() {
		return name();
	}

	public String getValue() {
		return value;
	}

}
