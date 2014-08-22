package com.adapapa.simplecms.domain.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;

import com.dreammore.framework.codegeneration.model.Comment;
import com.dreammore.framework.codegeneration.model.Validate;
import com.dreammore.framework.common.model.BaseUidEntity;

@Entity
@Table(name = "CHANNEL")
public class Channel extends BaseUidEntity {

	private static final long serialVersionUID = 6499295372444055569L;

	@Comment(value = "创建时间")
	@Column(name = "CREATE_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;

	@Comment(value = "创建人")
	@Column(name = "RECORDER")
	private String recorder;

	@Comment(value = "栏目名称")
	@Validate(maxlength = "20", required = true)
	@Column(name = "CHANNEL_NAME", length = 20)
	private String channelName;

	@Comment(value = "栏目说明")
	@Column(name = "DESCRIPTION")
	@Validate(maxlength = "250")
	private String description;

	@Comment(value = "是否单页")
	@Column(name = "SINGLE")
	@Type(type = "yes_no")
	private boolean single = true; // 叶子节点是单页

	@Comment(value = "是否出现在首页")
	@Validate(required = true)
	@Column(name = "INDEX_SHOWN")
	@Type(type = "yes_no")
	private Boolean indexShown;

	@Comment("上次栏目")
	@ManyToOne(cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "PARENT_ID")
	private Channel parent;

	@Comment("下级栏目")
	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.REMOVE }, mappedBy = "father", fetch = FetchType.LAZY)
	private List<Channel> children;

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getRecorder() {
		return recorder;
	}

	public void setRecorder(String recorder) {
		this.recorder = recorder;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isSingle() {
		return single;
	}

	public void setSingle(boolean single) {
		this.single = single;
	}

	public Boolean getIndexShown() {
		return indexShown;
	}

	public void setIndexShown(Boolean indexShown) {
		this.indexShown = indexShown;
	}

	public Channel getParent() {
		return parent;
	}

	public void setParent(Channel parent) {
		this.parent = parent;
	}

	public List<Channel> getChildren() {
		return children;
	}

	public void setChildren(List<Channel> children) {
		this.children = children;
	}
	
	
	

}
