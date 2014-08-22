package com.adapapa.simplecms.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adapapa.simplecms.domain.model.SiteConfig;
import com.dreammore.framework.common.dao.ICommonDAO;
import com.dreammore.framework.common.utils.Tools;

@Service
public class SiteConfigServiceImpl implements ISiteConfigService {

	@Autowired
	private ICommonDAO commonDAO;
	
	@Override
	public SiteConfig getSiteConfig() {
		String hql = "FROM SiteConfig";
		List<SiteConfig> results = commonDAO.find(hql);
		return Tools.empty(results) ? null : results.get(0);
	}

}
