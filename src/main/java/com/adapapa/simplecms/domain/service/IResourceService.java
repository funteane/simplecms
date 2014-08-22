package com.adapapa.simplecms.domain.service;

import java.util.List;

import com.adapapa.simplecms.domain.model.Resource;
import com.dreammore.framework.common.dao.PageBean;

public interface IResourceService {

	public PageBean<Resource> getAllResources(PageBean<Resource> pageBean,
			Resource resource, List<String> ids);

}
