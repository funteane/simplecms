package com.adapapa.simplecms.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adapapa.simplecms.domain.model.Resource;
import com.dreammore.framework.common.dao.ICommonDAO;
import com.dreammore.framework.common.dao.PageBean;
import com.dreammore.framework.common.utils.Tools;

@Service
public class ResourceServiceImpl implements IResourceService {

	@Autowired
	private ICommonDAO commonDAO;

	@Override
	public PageBean<Resource> getAllResources(PageBean<Resource> pageBean,
			Resource resource, List<String> ids) {

		List<String> paramNames = new ArrayList<String>();
		List<Object> paramValues = new ArrayList<Object>();
		StringBuffer hql = new StringBuffer("FROM Resource WHERE 1 = 1 ");

		if (!Tools.empty(resource.getPublishedDate())) {
			hql.append("AND publishedDate  = :publishedDate ");
			paramNames.add("publishedDate");
			paramValues.add(resource.getPublishedDate());
		}

		if (!Tools.empty(resource.getPublisher())) {
			hql.append("AND publisher  = :publisher ");
			paramNames.add("publisher");
			paramValues.add(resource.getPublisher());
		}

		if (!Tools.empty(resource.getChecker())) {
			hql.append("AND checker  = :checker ");
			paramNames.add("checker");
			paramValues.add(resource.getChecker());
		}

		if (!Tools.empty(resource.getCheckedDate())) {
			hql.append("AND checkedDate  = :checkedDate ");
			paramNames.add("checkedDate");
			paramValues.add(resource.getCheckedDate());
		}

		if (!Tools.empty(resource.getResourceType())) {
			hql.append("AND resourceType  = :resourceType ");
			paramNames.add("resourceType");
			paramValues.add(resource.getResourceType());
		}

		if (!Tools.empty(resource.getChannel())) {
			hql.append("AND channel  = :channel ");
			paramNames.add("channel");
			paramValues.add(resource.getChannel());
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
