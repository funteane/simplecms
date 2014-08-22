package com.adapapa.simplecms.domain.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.dreammore.framework.codegeneration.model.Comment;
import com.dreammore.framework.codegeneration.model.Validate;
import com.dreammore.framework.common.model.BaseUidEntity;

@Entity
@Table(name = "RESOURCE")
public class Resource extends BaseUidEntity {

	private static final long serialVersionUID = -9028376722931397929L;

	@Comment(value = "发布时间", searchable = true)
	@Validate(required = true)
	@Column(name = "PUBLISHED_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date publishedDate;

	@Comment(value = "发布者", searchable = true)
	@Column(name = "RECORDER_ID")
	private String publisher;

	@Comment(value = "审核者", searchable = true)
	@Column(name = "CHECKER_ID")
	private String checker;

	@Comment(value = "审核时间", searchable = true)
	@Column(name = "CHECKED_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date checkedDate;

	@Comment(value = "资源类型", searchable = true)
	@Validate(required = true)
	@Column(name = "RESOURCE_TYPE")
	private ResourceType resourceType;

	@Comment(value = "所属频道", searchable = true)
	@Validate(required = true)
	@ManyToOne
	@JoinColumn(name = "CHANNEL_ID")
	private Channel channel;
	
	@Comment(value = "文章标题")
	@Validate(maxlength = "50", required = true)
	@Column(name="TITLE", length = 50)
	private String title; 

	@Comment("文章内容")
	@Validate(required = true)
	@Lob
	@Column(name="CONTENT", length = 100)
	private String content; 
	
	@Comment("作者")
	@Validate(required = true, maxlength = "20")
	@Column(name = "AUTHOR", length = 20)
	private String author; 

	@Comment("来源")
	@Validate(maxlength = "50")
	@Column(name="ORIGIN", length = 50)
	private String origin; 
	
	@Comment("tag标签")
	@Validate(maxlength = "100")
	@Column(name = "TAGS", length = 100)
	private String tags;
	
	@Comment("摘要")
	@Validate(maxlength = "200")
	@Column(name = "SUMMARY", length = 200)
	private String summary; 
	
	@Comment("访问次数")
	@Column(name="VISIT_TOTLAL")
	private Integer visitTotal = 0; // 总共访问次数

//	@Column(name="recommend_level")
//	private Integer recommendLevel; // 推荐级别
//	@Column(name="comment_count")
//	private Integer commentCount=0; // 评论数量
//	@Column(name="related_id")
//	private String relatedID; // 相关文章ID
//	@org.hibernate.annotations.Type(type="yes_no")
	
	@Comment(value = "是否允许评论")
	@Validate(required = true)
	@Column(name="COMMENTABLE")
	private Boolean commentable = false; // 是否允许评论
	
	@Comment(value = "审核状态")
	@Column(name="check_state")
	private CheckState checkState; //状态
	
	@Comment(value = "正本资源")
	@ManyToOne
	@JoinColumn(name = "PARENT_ID")
	private Resource parent;
	
	@Comment(value = "评论资源")
	@OneToMany
	private List<Resource> children ;
	
	@Comment("图片路径")
	@Column(name = "IMAGE_URL")
	private String imageUrl; //多个图片路径间通过;分割
	
	@Comment("附件路径")
	@Column(name = "ATTACH_URL")
	private String attachUrl; //多个附件路径间通过;分割

	public Date getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(Date publishedDate) {
		this.publishedDate = publishedDate;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getChecker() {
		return checker;
	}

	public void setChecker(String checker) {
		this.checker = checker;
	}

	public Date getCheckedDate() {
		return checkedDate;
	}

	public void setCheckedDate(Date checkedDate) {
		this.checkedDate = checkedDate;
	}

	public ResourceType getResourceType() {
		return resourceType;
	}

	public void setResourceType(ResourceType resourceType) {
		this.resourceType = resourceType;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public Integer getVisitTotal() {
		return visitTotal;
	}

	public void setVisitTotal(Integer visitTotal) {
		this.visitTotal = visitTotal;
	}

	public Boolean getCommentable() {
		return commentable;
	}

	public void setCommentable(Boolean commentable) {
		this.commentable = commentable;
	}

	public CheckState getCheckState() {
		return checkState;
	}

	public void setCheckState(CheckState checkState) {
		this.checkState = checkState;
	}

	public Resource getParent() {
		return parent;
	}

	public void setParent(Resource parent) {
		this.parent = parent;
	}

	public List<Resource> getChildren() {
		return children;
	}

	public void setChildren(List<Resource> children) {
		this.children = children;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getAttachUrl() {
		return attachUrl;
	}

	public void setAttachUrl(String attachUrl) {
		this.attachUrl = attachUrl;
	}
	
	
	

}
