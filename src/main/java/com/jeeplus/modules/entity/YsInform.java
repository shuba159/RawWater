/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jeeplus.common.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 通知Entity
 * @author wdy
 * @version 2018-05-22
 */
public class YsInform extends DataEntity<YsInform> {
	
	private static final long serialVersionUID = 1L;
	private Integer proId;		// 项目外键
	private String informName;		// 通知名称
	private Date informTime;		// 通知时间
	private String content;		// 通知内容
	private Integer createId;		// 创建人
	private String strIds;		// 创建时间
	
	public YsInform() {
		super();
	}

	public YsInform(String id){
		super(id);
	}

	public Integer getProId() {
		return proId;
	}

	public void setProId(Integer proId) {
		this.proId = proId;
	}

	public String getInformName() {
		return informName;
	}

	public void setInformName(String informName) {
		this.informName = informName;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getInformTime() {
		return informTime;
	}

	public void setInformTime(Date informTime) {
		this.informTime = informTime;
	}
	
	@ExcelField(title="通知内容", align=2, sort=4)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public Integer getCreateId() {
		return createId;
	}

	public void setCreateId(Integer createId) {
		this.createId = createId;
	}

	public String getStrIds() {
		return strIds;
	}

	public void setStrIds(String strIds) {
		this.strIds = strIds;
	}
	
}