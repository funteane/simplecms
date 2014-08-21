package com.adapapa.simplecms.domain.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.dreammore.framework.codegeneration.model.Comment;
import com.dreammore.framework.common.model.BaseUidEntity;

@Entity
@Table(name = "RESOURCE")
public class Resource extends BaseUidEntity {

	private static final long serialVersionUID = -9028376722931397929L;

	@Comment(value = "发布时间", searchable = true)
	@Column(name = "PUBLISHED_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date publishedDate;

	@Comment(value = "发布者", searchable = true)
	@Column(name = "RECORDER_ID")
	private UserInfo publisher;

	@Comment(value = "审核者", searchable = true)
	@Column(name = "CHECKER_ID")
	private UserInfo checker;

	@Comment(value = "审核时间", searchable = true)
	@Column(name = "CHECKED_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date checkedDate;

	@Comment(value = "资源类型", searchable = true)
	@Column(name = "RESOURCE_TYPE")
	private ResourceType resourceType;

	@Comment(value = "所属频道", searchable = true)
	@ManyToOne
	@JoinColumn(name = "CHANNEL")
	private Channel channel;

}
