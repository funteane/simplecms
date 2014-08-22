package com.adapapa.simplecms.application.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import com.adapapa.simplecms.domain.model.SiteConfig;
import com.adapapa.simplecms.domain.model.UserInfo;
import com.adapapa.simplecms.domain.service.ISiteConfigService;
import com.adapapa.simplecms.domain.service.IUserInfoService;
import com.dreammore.framework.common.service.ICommonService;
import com.dreammore.framework.common.utils.Constants;
import com.dreammore.framework.common.utils.Tools;
import com.dreammore.framework.common.web.BaseController;

public abstract class AbstractController extends BaseController {
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
	}
	
	@Autowired
	private ICommonService commonService;
	@Autowired
	private IUserInfoService userInfoService;
	@Autowired
	private ISiteConfigService siteConfigService;
	
	private static SiteConfig siteConfig;

	public UserInfo getUserInfo() {
		return getAttribute(Constants.USER_PRINCIPAL, UserInfo.class);
	}
	
	public Integer getPageNo(String pageNo){
		if(Tools.empty(pageNo)){
			return Integer.valueOf("1");
		}
		try {
			return Integer.parseInt(pageNo);
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
		}
		return Integer.valueOf("1");
	}
	
	
	public String getBaseUrl(HttpServletRequest request) {
		return request.getScheme().concat("://").concat(request.getServerName().concat(":")
				.concat(String.valueOf(request.getServerPort()))).concat(request.getContextPath());
	}



	public SiteConfig getSiteConfig() {
		if (Tools.empty(siteConfig)) {
			siteConfig = siteConfigService.getSiteConfig();
		}
		return siteConfig;
	}
	

}
