/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.notice.entity;

import javax.validation.constraints.NotNull;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.jeeplus.common.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 培训通知Entity
 * @author mx
 * @version 2018-05-18
 */
public class YsNotice extends DataEntity<YsNotice> {
	
	private static final long serialVersionUID = 1L;
	private Integer proId;		// 项目关联
	private String noticeName;		// 培训通知名称
	private Date noticeTime;		// 培训通知时间
	private Integer depId;		// 部门外键（所属分组）
	private String content;		// 培训通知内容
	private Integer noticeState;		// 培训通知状态，1可用，2不可用
	private Integer topTime;		// 置顶时间
	private Integer creataId;		// 创建人外键
	private Date creataTime;		// 创建时间
	
	public YsNotice() {
		super();
	}

	public YsNotice(String id){
		super(id);
	}

	@NotNull(message="项目关联不能为空")
	@ExcelField(title="项目关联", align=2, sort=1)
	public Integer getProId() {
		return proId;
	}

	public void setProId(Integer proId) {
		this.proId = proId;
	}
	
	@ExcelField(title="培训通知名称", align=2, sort=2)
	public String getNoticeName() {
		return noticeName;
	}

	public void setNoticeName(String noticeName) {
		this.noticeName = noticeName;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="培训通知时间不能为空")
	@ExcelField(title="培训通知时间", align=2, sort=3)
	public Date getNoticeTime() {
		return noticeTime;
	}

	public void setNoticeTime(Date noticeTime) {
		this.noticeTime = noticeTime;
	}
	
	@NotNull(message="部门外键（所属分组）不能为空")
	@ExcelField(title="部门外键（所属分组）", dictType="", align=2, sort=4)
	public Integer getDepId() {
		return depId;
	}

	public void setDepId(Integer depId) {
		this.depId = depId;
	}
	
	@ExcelField(title="培训通知内容", align=2, sort=5)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@NotNull(message="培训通知状态，1可用，2不可用不能为空")
	@ExcelField(title="培训通知状态，1可用，2不可用", dictType="", align=2, sort=6)
	public Integer getNoticeState() {
		return noticeState;
	}

	public void setNoticeState(Integer noticeState) {
		this.noticeState = noticeState;
	}
	
	@NotNull(message="置顶时间不能为空")
	@ExcelField(title="置顶时间", align=2, sort=7)
	public Integer getTopTime() {
		return topTime;
	}

	public void setTopTime(Integer topTime) {
		this.topTime = topTime;
	}
	
	@NotNull(message="创建人外键不能为空")
	@ExcelField(title="创建人外键", align=2, sort=8)
	public Integer getCreataId() {
		return creataId;
	}

	public void setCreataId(Integer creataId) {
		this.creataId = creataId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="创建时间不能为空")
	@ExcelField(title="创建时间", align=2, sort=9)
	public Date getCreataTime() {
		return creataTime;
	}

	public void setCreataTime(Date creataTime) {
		this.creataTime = creataTime;
	}
	
}