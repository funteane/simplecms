package com.adapapa.simplecms.domain.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.dreammore.framework.common.model.BaseUidEntity;

@Entity
@Table(name = "CHANNEL")
public class Channel extends BaseUidEntity{

	private static final long serialVersionUID = 6499295372444055569L;
	
	@Column(name = "CREATE_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;
	
	@Column(name = "RECORDER")
	private UserInfo recorder;
	
	private String channelName;
	
	
	private Channel parent;
	
	private List<Channel> children;
	
	
	

}
