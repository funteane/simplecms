package com.adapapa.simplecms.application.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.adapapa.simplecms.common.GlobalContants;
import com.adapapa.simplecms.common.JSONSimpler;
import com.adapapa.simplecms.domain.model.UserInfo;
import com.adapapa.simplecms.domain.service.IUserInfoService;
import com.dreammore.framework.common.dao.PageBean;
import com.dreammore.framework.common.model.SearchParameter;
import com.dreammore.framework.common.service.ICommonService;
import com.dreammore.framework.common.utils.Constants;
import com.dreammore.framework.common.utils.Tools;

@Controller
public class UserInfoController extends AbstractController {

	@Autowired
	private ICommonService commonService;
	@Autowired
	private IUserInfoService userInfoService;

	@RequestMapping
	public ModelAndView toUserInfoList(String pageNo, UserInfo userInfo) {

		PageBean<UserInfo> pageBean = new PageBean<UserInfo>();
		pageBean.setCurrentPage(getPageNo(pageNo));
		pageBean.setLength(GlobalContants.DEFAULT_PAGE_SIZE);

		ModelAndView modelAndView = new ModelAndView("userInfo/userInfoList");
		pageBean = userInfoService.getAllUserInfos(pageBean, userInfo, null);
		modelAndView.addObject("pageBean", pageBean);
		// 处理查询信息
		List<SearchParameter> searchParameters = buildSearchParameters(userInfo);
		setAttribute(Constants.SEARCH_PARAMS_LIST, searchParameters);
		setAttribute(Constants.CURRENT_PAGE_NO, getPageNo(pageNo));
		modelAndView.addObject("searchParams", JSONSimpler.toJson(searchParameters));
		modelAndView.addObject("searchUrl", "userinfo/toUserInfo.do");
		// 处理通知信息
		Boolean show = getFlashAttribute("show", Boolean.class);
		Boolean success = getFlashAttribute("success", Boolean.class);
		List<String> messages = getFlashAttribute("messages", List.class);
		modelAndView.addObject("messages", messages);
		modelAndView.addObject("show", Tools.empty(show) ? false : show);
		modelAndView.addObject("success", Tools.empty(success) ? false : success);

		return modelAndView;
	}

	private List<SearchParameter> buildSearchParameters(UserInfo userInfo) {
		List<SearchParameter> searchParameters = new ArrayList<SearchParameter>();
//		searchParameters.add(new SearchParameter("userCode", userInfo.getUserCode()));
//		searchParameters.add(new SearchParameter("userName", userInfo.getUserName()));
//		searchParameters.add(new SearchParameter("nickName", userInfo.getNickName()));
//		searchParameters.add(new SearchParameter("cellphone", userInfo.getCellphone()));
		return searchParameters;
	}

	@RequestMapping
	public ModelAndView toUserInfo(Long id) {
		UserInfo userInfo;
		if (Tools.empty(id)) {
			userInfo = new UserInfo();
		} else {
			userInfo = commonService.get(UserInfo.class, id);
		}
		ModelAndView modelAndView = new ModelAndView("userInfo/userInfo");
		modelAndView.addObject("userInfo", userInfo);

		return modelAndView;
	}

	@RequestMapping
	public ModelAndView deleteUserInfo(String id) {
		UserInfo userInfo = new UserInfo();
		userInfo.setId(id);
		commonService.delete(userInfo);

		List<String> messages = new ArrayList<String>();
		messages.add("保存成功！");
		setAttribute("messages", messages);
		setAttribute("success", true);
		setAttribute("show", true);

		return createStateModelAndView("userinfo/toUserInfoList.do");
	}

	@RequestMapping
	public ModelAndView saveUserInfo(UserInfo userInfo) {
		List<String> messages = validate(userInfo);
		if (Tools.empty(messages)) {
			commonService.saveOrupdate(userInfo);
			messages.add("保存成功！");
		}
		setAttribute("messages", messages);
		setAttribute("success", true);
		setAttribute("show", true);

		return createStateModelAndView("userinfo/toUserInfoList.do");
	}

	private List<String> validate(UserInfo userInfo) {
		List<String> messages = new ArrayList<String>();
		if (Tools.empty(userInfo.getUserCode())) {
			messages.add("用户ID不能为空！");
		}
		if (Tools.empty(userInfo.getUserName())) {
			messages.add("用户姓名不能为空！");
		}
		if (!Tools.empty(userInfo.getUserName())
				&& userInfo.getUserName().length() > 20) {
			messages.add("用户姓名不能超过20个字符!");
		}
		if (Tools.empty(userInfo.getNickName())) {
			messages.add("昵称不能为空！");
		}
		if (!Tools.empty(userInfo.getNickName())
				&& userInfo.getNickName().length() > 20) {
			messages.add("昵称不能超过20个字符!");
		}
		if (Tools.empty(userInfo.getCellphone())) {
			messages.add("手机号码不能为空！");
		}
		if (!Tools.empty(userInfo.getCellphone())
				&& userInfo.getCellphone().length() > 20) {
			messages.add("手机号码不能超过20个字符!");
		}
		if (!Tools.empty(userInfo.getEmail())
				&& userInfo.getEmail().length() > 20) {
			messages.add("电子邮箱不能超过20个字符!");
		}
		if (Tools.empty(userInfo.getLoginName())) {
			messages.add("登录名不能为空！");
		}
		if (!Tools.empty(userInfo.getLoginName())
				&& userInfo.getLoginName().length() > 20) {
			messages.add("登录名不能超过20个字符!");
		}
		if (Tools.empty(userInfo.getLoginPwd())) {
			messages.add("密码不能为空！");
		}
		if (!Tools.empty(userInfo.getLoginPwd())
				&& userInfo.getLoginPwd().length() > 20) {
			messages.add("密码不能超过20个字符!");
		}
		if (!Tools.empty(userInfo.getLoginPwd())
				&& userInfo.getLoginPwd().length() < 6) {
			messages.add("密码不能少于6个字符!");
		}
		if (Tools.empty(userInfo.getLoginPwd2())) {
			messages.add("密码不能为空！");
		}
		if (!Tools.empty(userInfo.getLoginPwd2())
				&& userInfo.getLoginPwd2().length() > 20) {
			messages.add("密码不能超过20个字符!");
		}
		if (!Tools.empty(userInfo.getLoginPwd2())
				&& userInfo.getLoginPwd2().length() < 6) {
			messages.add("密码不能少于6个字符!");
		}
		if (Tools.empty(userInfo.getRoleInfo())) {
			messages.add("用户角色不能为空！");
		}
		if (Tools.empty(userInfo.getGender())) {
			messages.add("性别不能为空！");
		}
		return messages;
	}

}
