package com.adapapa.simplecms.domain.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.dreammore.framework.common.model.BaseUidEntity;


/**
 * 站点配置
 */
@Entity
@Table(name = "SITE_CONFIG")
public class SiteConfig extends BaseUidEntity{

	private static final long serialVersionUID = 4569801136455180362L;
	
	private String siteName;
	
	private String keywords;
	
	private String description;
	
	

}
