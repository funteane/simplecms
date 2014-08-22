package com.adapapa.simplecms.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adapapa.simplecms.domain.model.UserInfo;
import com.dreammore.framework.common.dao.ICommonDAO;
import com.dreammore.framework.common.dao.PageBean;
import com.dreammore.framework.common.utils.Tools;

@Service
public class UserInfoServiceImpl implements IUserInfoService {

	@Autowired
	private ICommonDAO commonDAO;

	@Override
	public PageBean<UserInfo> getAllUserInfos(PageBean<UserInfo> pageBean,
			UserInfo userInfo, List<String> ids) {

		List<String> paramNames = new ArrayList<String>();
		List<Object> paramValues = new ArrayList<Object>();
		StringBuffer hql = new StringBuffer("FROM UserInfo WHERE 1 = 1 ");

		if (!Tools.empty(userInfo.getUserCode())) {
			hql.append("AND userCode  = :userCode ");
			paramNames.add("userCode");
			paramValues.add(userInfo.getUserCode());
		}

		if (!Tools.empty(userInfo.getUserName())) {
			hql.append("AND userName  = :userName ");
			paramNames.add("userName");
			paramValues.add(userInfo.getUserName());
		}

		if (!Tools.empty(userInfo.getNickName())) {
			hql.append("AND nickName  = :nickName ");
			paramNames.add("nickName");
			paramValues.add(userInfo.getNickName());
		}

		if (!Tools.empty(userInfo.getCellphone())) {
			hql.append("AND cellphone  = :cellphone ");
			paramNames.add("cellphone");
			paramValues.add(userInfo.getCellphone());
		}

		if (!Tools.empty(userInfo.getState())) {
			hql.append("AND state  = :state ");
			paramNames.add("state");
			paramValues.add(userInfo.getState());
		}

		if (!Tools.empty(ids)) {
			hql.append("AND id IN (:ids) ");
			paramNames.add("ids");
			paramValues.add(ids);
		}

		hql.append("ORDER BY id DESC");

		String[] names = new String[paramNames.size()];
		Object[] values = new Object[paramValues.size()];
		Tools.list2Array(paramNames, names);
		Tools.list2Array(paramValues, values);

		commonDAO.find(hql.toString(), pageBean, names, values);

		return pageBean;
	}

}
