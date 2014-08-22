package com.adapapa.simplecms.application.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.adapapa.simplecms.domain.model.SiteConfig;
import com.adapapa.simplecms.domain.service.ISiteConfigService;
import com.dreammore.framework.common.service.ICommonService;
import com.dreammore.framework.common.utils.Tools;
import com.dreammore.framework.common.web.BaseController;

@Controller
public class SiteConfigController extends BaseController {

	@Autowired
	private ICommonService commonService;
	@Autowired
	private ISiteConfigService siteConfigService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				new SimpleDateFormat("yyyy-MM-dd"), true));
	}

	@RequestMapping
	public ModelAndView toSiteConfig() {
		SiteConfig siteConfig = siteConfigService.getSiteConfig();
		if (Tools.empty(siteConfig)) {
			siteConfig = new SiteConfig();
		} 
		ModelAndView modelAndView = new ModelAndView("siteConfig/siteConfig");
		modelAndView.addObject("siteConfig", siteConfig);

		return modelAndView;
	}


	@RequestMapping
	public ModelAndView saveSiteConfig(SiteConfig siteConfig) {
		List<String> messages = validate(siteConfig);

		if (Tools.empty(messages)) {
			commonService.saveOrupdate(siteConfig);
			messages.add("保存成功！");
		} 
		
		setAttribute("messages", messages);
		setAttribute("success", true);
		setAttribute("show", true);

		return createStateModelAndView("siteconfig/toSiteConfig.do");
	}

	private List<String> validate(SiteConfig siteConfig) {
		List<String> messages = new ArrayList<String>();
		if (Tools.empty(siteConfig.getShortName())) {
			messages.add("网站简称不能为空！");
		}
		if (!Tools.empty(siteConfig.getShortName())
				&& siteConfig.getShortName().length() > 20) {
			messages.add("网站简称不能超过20个字符!");
		}
		if (!Tools.empty(siteConfig.getShortName())
				&& siteConfig.getShortName().length() < 6) {
			messages.add("网站简称不能少于6个字符!");
		}
		if (Tools.empty(siteConfig.getSiteName())) {
			messages.add("网站全称不能为空！");
		}
		if (!Tools.empty(siteConfig.getSiteName())
				&& siteConfig.getSiteName().length() > 50) {
			messages.add("网站全称不能超过50个字符!");
		}
		if (!Tools.empty(siteConfig.getSiteName())
				&& siteConfig.getSiteName().length() < 6) {
			messages.add("网站全称不能少于6个字符!");
		}
		if (!Tools.empty(siteConfig.getIcpCode())
				&& siteConfig.getIcpCode().length() > 30) {
			messages.add("网站ICP备案号不能超过30个字符!");
		}
		return messages;
	}

}
