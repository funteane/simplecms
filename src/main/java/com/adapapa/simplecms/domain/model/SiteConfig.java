package com.adapapa.simplecms.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.dreammore.framework.codegeneration.model.Comment;
import com.dreammore.framework.codegeneration.model.Validate;
import com.dreammore.framework.common.model.BaseUidEntity;

/**
 * 站点配置
 */
@Entity
@Table(name = "SITE_CONFIG")
public class SiteConfig extends BaseUidEntity {

	private static final long serialVersionUID = 4569801136455180362L;

	@Comment(value = "网站简称")
	@Validate(minlength = "6", maxlength = "20", required = true)
	@Column(name = "SHORT_NAME", length = 20)
	private String shortName;
	
	@Comment(value = "网站全称")
	@Validate(minlength = "6", maxlength = "50", required = true)
	@Column(name = "SITE_NAME", length = 50)
	private String siteName;

	@Comment(value = "IP地址")
	@Column(name = "IP", length = 50)
	private String ip;

	@Comment(value = "网站关键词")
	@Lob
	@Column(name = "KEYWORDS")
	private String keywords;

	@Comment(value = "网站描述")
	@Lob
	@Column(name = "DESCRIPTION")
	private String description;

	@Comment(value = "网站简介")
	@Lob
	@Column(name = "INTRODUCTION")
	private String introduction;

	@Comment(value = "网站ICP备案号")
	@Validate(maxlength = "30")
	@Column(name = "ICP_CODE")
	private String icpCode;

	@Comment(value = "网站版权信息")
	@Column(name = "COPYRIGHT")
	private String copyright;

	@Comment(value = "网站模板路径")
	@Column(name = "TEMPLATE")
	private String template;
	
	//-------------------------------------------------------------------------------------//

	@Comment(value = "公司名称")
	@Column(name = "COMPANY")
	private String company;
	
	@Comment(value = "公司地址")
	@Column(name = "ADDRESS")
	private String address;

	@Comment(value = "公司邮编")
	@Column(name = "POST_CODE")
	private String postCode;

	@Comment(value = "联系人email")
	@Column(name = "EMAIL")
	private String email;

	@Comment(value = "联系人电话")
	@Column(name = "TELEPHONE")
	private String telephone;

	@Comment(value = "联系人手机")
	@Column(name = "MOBILEPHONE")
	private String mobilephone;

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getIcpCode() {
		return icpCode;
	}

	public void setIcpCode(String icpCode) {
		this.icpCode = icpCode;
	}

	public String getCopyright() {
		return copyright;
	}

	public void setCopyright(String copyright) {
		this.copyright = copyright;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getMobilephone() {
		return mobilephone;
	}

	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}
	
	
	
}
