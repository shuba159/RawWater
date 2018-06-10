/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.mien.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import com.jeeplus.common.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 教学风采Entity
 * @author mx
 * @version 2018-05-18
 */
public class YsMien extends DataEntity<YsMien> {
	
	private static final long serialVersionUID = 1L;
	private String mienTitle;		// 教学风采主题
	private Date mienTime;		// 风采发帖时间
	private String studentName;		// 学生姓名
	private String teacherName;		// 教师姓名
	private String content;		// 教学风采内容
	private Integer publishState;		// 发布状态，1 已发布，2未发布
	private Integer depId;		// 部位外键
	
	public YsMien() {
		super();
	}

	public YsMien(String id){
		super(id);
	}

	@ExcelField(title="教学风采主题", align=2, sort=1)
	public String getMienTitle() {
		return mienTitle;
	}

	public void setMienTitle(String mienTitle) {
		this.mienTitle = mienTitle;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="风采发帖时间不能为空")
	@ExcelField(title="风采发帖时间", align=2, sort=2)
	public Date getMienTime() {
		return mienTime;
	}

	public void setMienTime(Date mienTime) {
		this.mienTime = mienTime;
	}
	
	@ExcelField(title="学生姓名", align=2, sort=3)
	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	
	@ExcelField(title="教师姓名", align=2, sort=4)
	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}
	
	@ExcelField(title="教学风采内容", align=2, sort=5)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	@NotNull(message="发布状态，1 已发布，2未发布不能为空")
	@ExcelField(title="发布状态，1 已发布，2未发布", align=2, sort=6)
	public Integer getPublishState() {
		return publishState;
	}

	public void setPublishState(Integer publishState) {
		this.publishState = publishState;
	}
	
	@ExcelField(title="部位外键", align=2, sort=7)
	public Integer getDepId() {
		return depId;
	}

	public void setDepId(Integer depId) {
		this.depId = depId;
	}
	
}