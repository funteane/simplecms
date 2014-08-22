package com.adapapa.simplecms.domain.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.dreammore.framework.codegeneration.model.Comment;
import com.dreammore.framework.codegeneration.model.Validate;
import com.dreammore.framework.common.model.BaseUidEntity;
import com.dreammore.framework.user.model.User;


/**
 * 
 * @author Dreamone
 *
 */

@Entity
@Table(name = "GROUND_USER_INFO")
public class UserInfo extends BaseUidEntity implements User {

	private static final long serialVersionUID = 7421099974553366017L;

	@Comment(value = "用户ID", searchable = true)
	@Validate(required = true) 
	@Column(name = "USER_CODE")
	private String userCode; 

	@Comment(value = "用户姓名", searchable = true)
	@Column(name = "USER_NAME")
	@Validate(required = true, maxlength = "20") 
	private String userName;
	
	@Comment(value = "昵称", searchable = true)
	@Validate(required = true, maxlength = "20") 
	@Column(name = "NICK_NAME")
	private String nickName;

	@Comment(value = "手机号码", searchable = true)
	@Validate(required = true, maxlength = "20") 
	@Column(name = "CELL_PHONE", unique = true)
	private String cellphone;

	@Comment(value = "电子邮箱")
	@Validate(maxlength = "20") 
	@Column(name = "EMAIL")
	private String email;

	@Comment(value = "登录名")
	@Validate(required = true, maxlength = "20") 
	@Column(name = "LOGIN_NAME")
	private String loginName;

	@Comment(value = "密码")
	@Validate(required = true, minlength = "6", maxlength = "20") 
	@Column(name = "LOGIN_PWD")
	private String loginPwd;
	
	@Transient
	@Comment(value = "密码")
	@Validate(required = true, minlength = "6", maxlength = "20") 
	private String loginPwd2;

	//@Comment(value = "创建日期")
	@Column(name = "CREATE_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createDate;

	@Comment(value = "用户角色")
	@Validate(required = true) 
	@Column(name = "ROLE_INFO")
	private RoleInfo roleInfo;

	@Comment(value = "用户状态", searchable = true)
	@Column(name = "STATE")
	private UserState state;
	
	@Column(name = "GENDER")
	@Comment("性别") 
	@Validate(required = true) 
	private Boolean gender;
	
	/****************************** setter/getter *******************************************/
	
	
	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPwd() {
		return loginPwd;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public RoleInfo getRoleInfo() {
		return roleInfo;
	}

	public void setRoleInfo(RoleInfo roleInfo) {
		this.roleInfo = roleInfo;
	}

	public UserState getState() {
		return state;
	}

	public void setState(UserState state) {
		this.state = state;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

		
	public Boolean getGender() {
		return gender;
	}

	public void setGender(Boolean gender) {
		this.gender = gender;
	}
	

	public String getLoginPwd2() {
		return loginPwd2;
	}

	public void setLoginPwd2(String loginPwd2) {
		this.loginPwd2 = loginPwd2;
	}
	
	
	


	

}
