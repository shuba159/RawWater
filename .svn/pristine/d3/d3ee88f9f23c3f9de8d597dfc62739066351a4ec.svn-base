/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.dynamic.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import com.jeeplus.common.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 动态浏览Entity
 * @author mx
 * @version 2018-05-18
 */
public class YsDynamic extends DataEntity<YsDynamic> {
	
	private static final long serialVersionUID = 1L;
	private Date publishTime;		// 动态信息发布时间
	private String dynamicTitle;		// 动态信息标题
	private String content;		// 动态信息内容
	private String address;		// 动态信息图片路径
	private Integer publishState;		// 动态信息状态，1已发布，2未发布
	
	public YsDynamic() {
		super();
	}

	public YsDynamic(String id){
		super(id);
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="动态信息发布时间不能为空")
	@ExcelField(title="动态信息发布时间", align=2, sort=1)
	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
	
	@ExcelField(title="动态信息标题", align=2, sort=2)
	public String getDynamicTitle() {
		return dynamicTitle;
	}

	public void setDynamicTitle(String dynamicTitle) {
		this.dynamicTitle = dynamicTitle;
	}
	
	@ExcelField(title="动态信息内容", align=2, sort=3)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@ExcelField(title="动态信息图片路径", align=2, sort=4)
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	@NotNull(message="动态信息状态，1已发布，2未发布不能为空")
	@ExcelField(title="动态信息状态，1已发布，2未发布", align=2, sort=5)
	public Integer getPublishState() {
		return publishState;
	}

	public void setPublishState(Integer publishState) {
		this.publishState = publishState;
	}
	
}