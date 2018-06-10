/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.entity;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jeeplus.common.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;
import com.jeeplus.modules.sys.entity.User;

/**
 * 项目评论Entity
 * @author wdy
 * @version 2018-05-21
 */
public class YsEvaluate extends DataEntity<YsEvaluate> {
	
	private static final long serialVersionUID = 1L;
	private Date evaluateTime;		// 项目评论的时间
	private String content;		// 项目评论的内容
	private User user;		// 项目评论的用户id
	private Integer proId;		// 项目外键
	private Integer evaluate;		// 评价等级 1.一颗星 2.两颗星 3.....
	private Date startTime;		// 培训开始时间
	private Date endTime;		// 培训结束时间
	private Integer totalCount;		// 应学人数
	private Integer studyingCount;  // 正在学习
	private Integer evaluateCount;  // 参与评价人数
	private List<Integer> evaluateList;	// 评价等级 
	
	
	public YsEvaluate() {
		super();
	}

	public YsEvaluate(String id){
		super(id);
	}
	
	public Integer getEvaluateCount() {
		return evaluateCount;
	}

	public void setEvaluateCount(Integer evaluateCount) {
		this.evaluateCount = evaluateCount;
	}

	public List<Integer> getEvaluateList() {
		return evaluateList;
	}

	public void setEvaluateList(List<Integer> evaluateList) {
		this.evaluateList = evaluateList;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public Integer getStudyingCount() {
		return studyingCount;
	}

	public void setStudyingCount(Integer studyingCount) {
		this.studyingCount = studyingCount;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEvaluateTime() {
		return evaluateTime;
	}

	public void setEvaluateTime(Date evaluateTime) {
		this.evaluateTime = evaluateTime;
	}
	
	@ExcelField(title="项目评论的内容", align=2, sort=2)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public Integer getProId() {
		return proId;
	}

	public void setProId(Integer proId) {
		this.proId = proId;
	}

	public Integer getEvaluate() {
		return evaluate;
	}

	public void setEvaluate(Integer evaluate) {
		this.evaluate = evaluate;
	}
	
	
	
}