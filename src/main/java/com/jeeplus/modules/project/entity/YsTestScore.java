/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.project.entity;

import javax.validation.constraints.NotNull;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.jeeplus.common.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 按人评卷Entity
 * @author hjm
 * @version 2018-05-29
 */
public class YsTestScore extends DataEntity<YsTestScore> {
	
	private static final long serialVersionUID = 1L;
	private Integer proId;		// 项目外键
	private Integer papId;		// 试卷外键
	private Integer userId;		// 用户外键
	private Integer depId;		// 部门外键
	private Integer score;		// 考试分数
	private Date commitTime;		// 交卷时间
	private Date evaluateTime;		// 评卷时间
	private Integer evaluateName;		// 评卷人_关联用户ID
	private Integer ispath;		// 1.及格2.未及格
	
	private String userIds;    // 临时数据 为了查询时输入用户名为String类型  可以查出数据
	private String depIds;    // 临时数据 为了查询时输入用户名为String类型  可以查出数据
	private Integer testId;    // 临时数据，为了点击按人评卷后出的页面的数据
	public String getUserIds() {
		return userIds;
	}

	public void setUserIds(String userIds) {
		this.userIds = userIds;
	}

	public String getDepIds() {
		return depIds;
	}

	public void setDepIds(String depIds) {
		this.depIds = depIds;
	}

	
	public Integer geTestId() {
		return testId;
	}

	public void setTestId(Integer testId) {
		this.testId = testId;
	}
	
	public YsTestScore() {
		super();
	}

	public YsTestScore(String id){
		super(id);
	}

	@ExcelField(title="项目外键", align=2, sort=1)
	public Integer getProId() {
		return proId;
	}

	public void setProId(Integer proId) {
		this.proId = proId;
	}
	
	@ExcelField(title="试卷外键", align=2, sort=2)
	public Integer getPapId() {
		return papId;
	}

	public void setPapId(Integer papId) {
		this.papId = papId;
	}
	
	@NotNull(message="用户外键不能为空")
	@ExcelField(title="用户外键", align=2, sort=3)
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	@NotNull(message="部门外键不能为空")
	@ExcelField(title="部门外键", align=2, sort=4)
	public Integer getDepId() {
		return depId;
	}

	public void setDepId(Integer depId) {
		this.depId = depId;
	}
	
	@NotNull(message="考试分数不能为空")
	@ExcelField(title="考试分数", align=2, sort=5)
	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="交卷时间不能为空")
	@ExcelField(title="交卷时间", align=2, sort=6)
	public Date getCommitTime() {
		return commitTime;
	}

	public void setCommitTime(Date commitTime) {
		this.commitTime = commitTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="评卷时间不能为空")
	@ExcelField(title="评卷时间", align=2, sort=7)
	public Date getEvaluateTime() {
		return evaluateTime;
	}

	public void setEvaluateTime(Date evaluateTime) {
		this.evaluateTime = evaluateTime;
	}
	
	@NotNull(message="评卷人_关联用户ID不能为空")
	@ExcelField(title="评卷人_关联用户ID", align=2, sort=8)
	public Integer getEvaluateName() {
		return evaluateName;
	}

	public void setEvaluateName(Integer evaluateName) {
		this.evaluateName = evaluateName;
	}
	
	@ExcelField(title="1.及格2.未及格", align=2, sort=9)
	public Integer getIspath() {
		return ispath;
	}

	public void setIspath(Integer ispath) {
		this.ispath = ispath;
	}

	@Override
	public String toString() {
		return "YsTestScore [proId=" + proId + ", papId=" + papId + ", userId=" + userId + ", depId=" + depId
				+ ", score=" + score + ", commitTime=" + commitTime + ", evaluateTime=" + evaluateTime
				+ ", evaluateName=" + evaluateName + ", ispath=" + ispath + ", userIds=" + userIds + ", depIds="
				+ depIds + ", testId=" + testId + "]";
	}

	
}