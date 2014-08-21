package com.adapapa.simplecms.domain.model;

import com.dreammore.framework.user.model.Enumable;
import com.dreammore.framework.user.model.Role;

public enum RoleInfo implements Role, Enumable {

	ADMINISTRATOR("网站管理员"), WEBSERVICER("网站客服人员"), 
	FOUNDER("创始人"), SHAREHOLDER("股东"), 
	GENERALMEMBER("一般会员"), AUTHENTICATIONMEMBER("认证会员"), TRADINGMEMBER("交易会员"), 
	OTHERUSER("其他人员");

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
