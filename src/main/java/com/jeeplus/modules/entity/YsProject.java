/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jeeplus.common.persistence.DataEntity;

/**
 * 项目管理Entity
 * @author wdy
 * @version 2018-05-17
 */
public class YsProject extends DataEntity<YsProject> {
	
	private static final long serialVersionUID = 1L;
	private String proName;		// 项目名称
	private Integer proClassId;		// 项目分类外键
	private Date startTime;		// 培训开始时间
	private Date endTime;		// 培训结束时间
	private Integer courseType;		// 1.必修2.选修3.需要报名
	private String depIds;		// 部门分组
	private Integer studyCount;		// 学习人数
	private Integer evaluate;		// 总评分数
	private String goal;		// 课程目标
	private String introduce;		// 课程介绍
	private Integer courseNumber;		// 项目课件数
	private Date createTime;		// 创建时间
	private Integer totalScore;		// 项目总学分
	private Date updateTime;		// 修改时间
	private Integer createId;		// 创建人外键
	private String createName;		// 创建人
	private Date sTime;		// 用于区间查找
	private Date eTime;		// 用于区间查找
	private String userIds; // 项目中学员id
	private String depName; //部门名称
	private String proClassName;//项目分类名称
	private String courseIds; // 课件的id集合
	private String testIds;  // 考试的id集合
	
	public String getProClassName() {
		return proClassName;
	}

	public void setProClassName(String proClassName) {
		this.proClassName = proClassName;
	}

	public String getDepName() {
		return depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}

	public String getUserIds() {
		return userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}

	public YsProject() {
		super();
	}

	public YsProject(String id){
		super(id);
	}
	
	public Date getSTime() {
		return sTime;
	}

	public Date getsTime() {
		return sTime;
	}

	public void setsTime(Date sTime) {
		this.sTime = sTime;
	}

	public Date geteTime() {
		return eTime;
	}

	public void seteTime(Date eTime) {
		this.eTime = eTime;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}
	
	public Integer getProClassId() {
		return proClassId;
	}

	public void setProClassId(Integer proClassId) {
		this.proClassId = proClassId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	public Integer getCourseType() {
		return courseType;
	}

	public void setCourseType(Integer courseType) {
		this.courseType = courseType;
	}
	

	public String getDepIds() {
		return depIds;
	}

	public void setDepIds(String depIds) {
		this.depIds = depIds;
	}

	public Integer getStudyCount() {
		return studyCount;
	}

	public void setStudyCount(Integer studyCount) {
		this.studyCount = studyCount;
	}
	
	public Integer getEvaluate() {
		return evaluate;
	}

	public void setEvaluate(Integer evaluate) {
		this.evaluate = evaluate;
	}
	
	public String getGoal() {
		return goal;
	}

	public void setGoal(String goal) {
		this.goal = goal;
	}
	
	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	
	public Integer getCourseNumber() {
		return courseNumber;
	}

	public void setCourseNumber(Integer courseNumber) {
		this.courseNumber = courseNumber;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Integer getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(Integer totalScore) {
		this.totalScore = totalScore;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public Integer getCreateId() {
		return createId;
	}

	public void setCreateId(Integer createId) {
		this.createId = createId;
	}

	public String getCourseIds() {
		return courseIds;
	}

	public void setCourseIds(String courseIds) {
		this.courseIds = courseIds;
	}

	public String getTestIds() {
		return testIds;
	}

	public void setTestIds(String testIds) {
		this.testIds = testIds;
	}
	
}