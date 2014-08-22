package com.adapapa.simplecms.domain.model;

import com.dreammore.framework.user.model.Enumable;


/**
 * 用户状态
 */
public enum UserState implements Enumable{
	
	NORMAL("正常"), FROZEN("冻结"), CLOSED("关闭"), ARREARAGE("欠费"), REGISTERED("注册")
	;
	
	private String value;
	
	private UserState(String value){
		this.value = value;
	}

	public String getName() {
		return name();
	}

	public String getValue() {
		return value;
	}
	
	

}
