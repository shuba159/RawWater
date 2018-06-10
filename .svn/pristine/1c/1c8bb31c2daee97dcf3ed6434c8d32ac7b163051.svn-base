/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jeeplus.common.persistence.DataEntity;

/**
 * 试卷Entity
 * @author wdy
 * @version 2018-05-30
 */
public class YsPaper extends DataEntity<YsPaper> {
	
	private static final long serialVersionUID = 1L;
	private String papName;		// 试卷名称
	private Integer totalScore = 100;		// 试卷总分
	private Integer passScore = 60;		// 及格分数
	private Integer papType;		// 试卷类型 1.选择出题2.随机抽题3.随机出卷
	private Integer createId;		// 创建人
	private Date createTime;		// 创建时间
	private Date updateTime;		// 更新时间
	private Integer singleScore;	// 单选 每题分数
	private Integer manyScore;		// 多选题分数
	private Integer judgeScore;		// 判断每题分数
	private Integer fillScore;		// 填空每题分数
	private Integer simpleScore;		// 简答题每题分数
	private String questionDescribe;		// 试题描述
	
	public YsPaper() {
		super();
	}

	public YsPaper(String id){
		super(id);
	}

	public String getPapName() {
		return papName;
	}

	public void setPapName(String papName) {
		this.papName = papName;
	}

	public Integer getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(Integer totalScore) {
		this.totalScore = totalScore;
	}

	public Integer getPassScore() {
		return passScore;
	}

	public void setPassScore(Integer passScore) {
		this.passScore = passScore;
	}

	public Integer getPapType() {
		return papType;
	}

	public void setPapType(Integer papType) {
		this.papType = papType;
	}

	public Integer getCreateId() {
		return createId;
	}

	public void setCreateId(Integer createId) {
		this.createId = createId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getSingleScore() {
		return singleScore;
	}

	public void setSingleScore(Integer singleScore) {
		this.singleScore = singleScore;
	}

	public Integer getManyScore() {
		return manyScore;
	}

	public void setManyScore(Integer manyScore) {
		this.manyScore = manyScore;
	}

	public Integer getJudgeScore() {
		return judgeScore;
	}

	public void setJudgeScore(Integer judgeScore) {
		this.judgeScore = judgeScore;
	}

	public Integer getFillScore() {
		return fillScore;
	}

	public void setFillScore(Integer fillScore) {
		this.fillScore = fillScore;
	}

	public Integer getSimpleScore() {
		return simpleScore;
	}

	public void setSimpleScore(Integer simpleScore) {
		this.simpleScore = simpleScore;
	}
	
	public String getQuestionDescribe() {
		return questionDescribe;
	}

	public void setQuestionDescribe(String questionDescribe) {
		this.questionDescribe = questionDescribe;
	}
	
}