package com.adapapa.simplecms.application.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.adapapa.simplecms.common.GlobalContants;
import com.adapapa.simplecms.common.JSONSimpler;
import com.adapapa.simplecms.domain.model.Resource;
import com.adapapa.simplecms.domain.service.IResourceService;
import com.dreammore.framework.common.dao.PageBean;
import com.dreammore.framework.common.model.SearchParameter;
import com.dreammore.framework.common.service.ICommonService;
import com.dreammore.framework.common.utils.Constants;
import com.dreammore.framework.common.utils.Tools;

@Controller
public class ResourceController extends AbstractController {

	@Autowired
	private ICommonService commonService;
	@Autowired
	private IResourceService resourceService;

	@RequestMapping
	public ModelAndView toResourceList(String pageNo, Resource resource) {

		PageBean<Resource> pageBean = new PageBean<Resource>();
		pageBean.setCurrentPage(getPageNo(pageNo));
		pageBean.setLength(GlobalContants.DEFAULT_PAGE_SIZE);

		ModelAndView modelAndView = new ModelAndView("resource/resourceList");
		pageBean = resourceService.getAllResources(pageBean, resource, null);
		modelAndView.addObject("pageBean", pageBean);
		// 处理查询信息
		List<SearchParameter> searchParameters = buildSearchParameters(resource);
		setAttribute(Constants.SEARCH_PARAMS_LIST, searchParameters);
		setAttribute(Constants.CURRENT_PAGE_NO, getPageNo(pageNo));
		modelAndView.addObject("searchParams", JSONSimpler.toJson(searchParameters));
		modelAndView.addObject("searchUrl", "resource/toResourceList.do");
		// 处理通知信息
		Boolean show = getFlashAttribute("show", Boolean.class);
		Boolean success = getFlashAttribute("success", Boolean.class);
		List<String> messages = getFlashAttribute("messages", List.class);
		modelAndView.addObject("messages", messages);
		modelAndView.addObject("show", Tools.empty(show) ? false : show);
		modelAndView.addObject("success", Tools.empty(success) ? false : success);

		return modelAndView;
	}

	private List<SearchParameter> buildSearchParameters(Resource resource) {
		List<SearchParameter> searchParameters = new ArrayList<SearchParameter>();
//		 searchParameters.add(new SearchParameter("publishedDate", resource.getPublishedDate()));
		 searchParameters.add(new SearchParameter("publisher", resource.getPublisher()));
		 searchParameters.add(new SearchParameter("checker", resource.getChecker()));
//		 searchParameters.add(new SearchParameter("checkedDate", resource.getCheckedDate()));
		 if (!Tools.empty(resource.getResourceType())) {
			 searchParameters.add(new SearchParameter("resourceType", resource.getResourceType().getName()));
		 }
		 if (!Tools.empty(resource.getChannel()) && !Tools.empty(resource.getChannel().getId())) {
			 searchParameters.add(new SearchParameter("channel", resource.getChannel().getId()));
		 }
		return searchParameters;
	}

	@RequestMapping
	public ModelAndView toResource(Long id) {
		Resource resource;
		if (Tools.empty(id)) {
			resource = new Resource();
		} else {
			resource = commonService.get(Resource.class, id);
		}
		ModelAndView modelAndView = new ModelAndView("resource/resource");
		modelAndView.addObject("resource", resource);

		return modelAndView;
	}

	@RequestMapping
	public ModelAndView deleteResource(String id) {
		Resource resource = new Resource();
		resource.setId(id);
		commonService.delete(resource);

		List<String> messages = new ArrayList<String>();
		messages.add("保存成功！");
		setAttribute("messages", messages);
		setAttribute("success", true);
		setAttribute("show", true);

		return createStateModelAndView("resource/toResourceList.do");
	}

	@RequestMapping
	public ModelAndView saveResource(Resource resource) {
		List<String> messages = validate(resource);
		if (Tools.empty(messages)) {
			commonService.saveOrupdate(resource);
			messages.add("保存成功！");
		}
		setAttribute("messages", messages);
		setAttribute("success", true);
		setAttribute("show", true);

		return createStateModelAndView("resource/toResourceList.do");
	}

	private List<String> validate(Resource resource) {
		List<String> messages = new ArrayList<String>();
		if (Tools.empty(resource.getPublishedDate())) {
			messages.add("发布时间不能为空！");
		}
		if (Tools.empty(resource.getResourceType())) {
			messages.add("资源类型不能为空！");
		}
		if (Tools.empty(resource.getChannel())) {
			messages.add("所属频道不能为空！");
		}
		if (Tools.empty(resource.getTitle())) {
			messages.add("文章标题不能为空！");
		}
		if (!Tools.empty(resource.getTitle())
				&& resource.getTitle().length() > 50) {
			messages.add("文章标题不能超过50个字符!");
		}
		if (Tools.empty(resource.getContent())) {
			messages.add("文章内容不能为空！");
		}
		if (Tools.empty(resource.getAuthor())) {
			messages.add("作者不能为空！");
		}
		if (!Tools.empty(resource.getAuthor())
				&& resource.getAuthor().length() > 20) {
			messages.add("作者不能超过20个字符!");
		}
		if (!Tools.empty(resource.getOrigin())
				&& resource.getOrigin().length() > 50) {
			messages.add("来源不能超过50个字符!");
		}
		if (!Tools.empty(resource.getTags())
				&& resource.getTags().length() > 100) {
			messages.add("tag标签不能超过100个字符!");
		}
		if (!Tools.empty(resource.getSummary())
				&& resource.getSummary().length() > 200) {
			messages.add("摘要不能超过200个字符!");
		}
		if (Tools.empty(resource.getCommentable())) {
			messages.add("是否允许评论不能为空！");
		}
		return messages;
	}

}
