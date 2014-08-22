package com.adapapa.simplecms.domain.service;

import java.util.List;

import com.adapapa.simplecms.domain.model.Channel;
import com.dreammore.framework.common.dao.PageBean;

public interface IChannelService {

	public PageBean<Channel> getAllChannels(PageBean<Channel> pageBean,
			Channel channel, List<String> ids);

}
