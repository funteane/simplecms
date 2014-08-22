package com.adapapa.simplecms.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adapapa.simplecms.domain.model.Channel;
import com.dreammore.framework.common.dao.ICommonDAO;
import com.dreammore.framework.common.dao.PageBean;
import com.dreammore.framework.common.utils.Tools;

@Service
public class ChannelServiceImpl implements IChannelService{

   @Autowired
   private ICommonDAO commonDAO;

   @Override
   public PageBean<Channel> getAllChannels(PageBean<Channel> pageBean, Channel channel, List<String> ids) {

      List<String> paramNames = new ArrayList<String>();
      List<Object> paramValues = new ArrayList<Object>();
      StringBuffer hql = new StringBuffer("FROM Channel WHERE 1 = 1 ");

      if(!Tools.empty(ids)){
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
