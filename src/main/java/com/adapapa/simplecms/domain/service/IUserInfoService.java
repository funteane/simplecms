package com.adapapa.simplecms.domain.service;

import java.util.List;

import com.adapapa.simplecms.domain.model.UserInfo;
import com.dreammore.framework.common.dao.PageBean;

public interface IUserInfoService {

	public PageBean<UserInfo> getAllUserInfos(PageBean<UserInfo> pageBean,
			UserInfo userInfo, List<String> ids);

}
