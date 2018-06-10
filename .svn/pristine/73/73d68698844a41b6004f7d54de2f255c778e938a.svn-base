/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jeeplus.common.persistence.DataEntity;

/**
 * 用户信息Entity
 * @author wdy
 * @version 2018-05-14
 */
public class YsUser extends DataEntity<YsUser> {
	
	private static final long serialVersionUID = 1L;
	private String userName;		// 用户名
	private String fullName;		// 姓名
	private Integer gender;		// 性别 1.男2.女
	private String email;		// 邮箱
	private Date entryDate;		// 入职日期
	private String mobile;		// 电话
	private Integer role;		// 角色 1.学员2.教师3.管理员
	private Integer depId; //所属部门

	public YsUser() {
		super();
	}

	public YsUser(String id){
		super(id);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}
	
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	public Integer getDepId() {
		return depId;
	}

	public void setDepId(Integer depId) {
		this.depId = depId;
	}
	
}