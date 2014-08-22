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

import com.adapapa.simplecms.common.GlobalContants;
import com.adapapa.simplecms.common.JSONSimpler;
import com.adapapa.simplecms.domain.model.Channel;
import com.adapapa.simplecms.domain.service.IChannelService;
import com.dreammore.framework.common.dao.PageBean;
import com.dreammore.framework.common.model.SearchParameter;
import com.dreammore.framework.common.service.ICommonService;
import com.dreammore.framework.common.utils.Constants;
import com.dreammore.framework.common.utils.Tools;

@Controller
public class ChannelController extends AbstractController {
	

	@Autowired
	private ICommonService commonService;
	@Autowired
	private IChannelService channelService;

	@RequestMapping
	public ModelAndView toChannelList(String pageNo, Channel channel) {

		PageBean<Channel> pageBean = new PageBean<Channel>();
		pageBean.setCurrentPage(getPageNo(pageNo));
		pageBean.setLength(GlobalContants.DEFAULT_PAGE_SIZE);

		ModelAndView modelAndView = new ModelAndView("channel/channelList");
		pageBean = channelService.getAllChannels(pageBean, channel, null);
		modelAndView.addObject("pageBean", pageBean);
		// 处理查询信息
		List<SearchParameter> searchParameters = buildSearchParameters(channel);
		setAttribute(Constants.SEARCH_PARAMS_LIST, searchParameters);
		setAttribute(Constants.CURRENT_PAGE_NO, getPageNo(pageNo));
		modelAndView.addObject("searchParams", JSONSimpler.toJson(searchParameters));
		modelAndView.addObject("searchUrl", "channel/toChannelList.do");
		// 处理通知信息
		Boolean show = getFlashAttribute("show", Boolean.class);
		Boolean success = getFlashAttribute("success", Boolean.class);
		List<String> messages = getFlashAttribute("messages", List.class);
		modelAndView.addObject("messages", messages);
		modelAndView.addObject("show", Tools.empty(show) ? false : show);
		modelAndView.addObject("success", Tools.empty(success) ? false : success);

		return modelAndView;
	}

	private List<SearchParameter> buildSearchParameters(Channel channel) {
		List<SearchParameter> searchParameters = new ArrayList<SearchParameter>();
		return searchParameters;
	}

	@RequestMapping
	public ModelAndView toChannel(Long id) {
		Channel channel;
		if (Tools.empty(id)) {
			channel = new Channel();
		} else {
			channel = commonService.get(Channel.class, id);
		}
		ModelAndView modelAndView = new ModelAndView("channel/channel");
		modelAndView.addObject("channel", channel);

		return modelAndView;
	}

	@RequestMapping
	public ModelAndView deleteChannel(String id) {
		Channel channel = new Channel();
		channel.setId(id);
		commonService.delete(channel);

		List<String> messages = new ArrayList<String>();
		messages.add("保存成功！");
		setAttribute("messages", messages);
		setAttribute("success", true);
		setAttribute("show", true);

		return createStateModelAndView("channel/toChannelList.do");
	}

	@RequestMapping
	public ModelAndView saveChannel(Channel channel) {
		List<String> messages = validate(channel);
		if (Tools.empty(messages)) {
			commonService.saveOrupdate(channel);
			messages.add("保存成功！");
		}
		setAttribute("messages", messages);
		setAttribute("success", true);
		setAttribute("show", true);

		return createStateModelAndView("channel/toChannelList.do");
	}

	private List<String> validate(Channel channel) {
		List<String> messages = new ArrayList<String>();
		if (Tools.empty(channel.getChannelName())) {
			messages.add("栏目名称不能为空！");
		}
		if (!Tools.empty(channel.getChannelName())
				&& channel.getChannelName().length() > 20) {
			messages.add("栏目名称不能超过20个字符!");
		}
		if (!Tools.empty(channel.getDescription())
				&& channel.getDescription().length() > 250) {
			messages.add("栏目说明不能超过250个字符!");
		}
		if (Tools.empty(channel.getIndexShown())) {
			messages.add("是否出现在首页不能为空！");
		}
		return messages;
	}

}
