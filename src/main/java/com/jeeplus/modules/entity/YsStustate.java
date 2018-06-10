/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.entity;

import com.jeeplus.modules.sys.entity.User;
import javax.validation.constraints.NotNull;

import com.jeeplus.common.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 学习记录Entity
 * @author wdy
 * @version 2018-06-05
 */
public class YsStustate extends DataEntity<YsStustate> {
	
	private static final long serialVersionUID = 1L;
	private User user;		// 用户外键
	private Integer proId;		// 项目外键
	private Integer studyState;		// 我的培训项目的状态，1待学习，2学习中，3已完成
	private Integer progress;		// 我的培训的学习进度
	private Integer score;		// 我的项目的学分
	private Integer evaluate;		// 我评价项目的打分 0.未评价 1.一颗星 2.....
	private String evaContent;		// 评论内容
	
	public YsStustate() {
		super();
	}

	public YsStustate(String id){
		super(id);
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

	public Integer getStudyState() {
		return studyState;
	}

	public void setStudyState(Integer studyState) {
		this.studyState = studyState;
	}

	public Integer getProgress() {
		return progress;
	}

	public void setProgress(Integer progress) {
		this.progress = progress;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public Integer getEvaluate() {
		return evaluate;
	}

	public void setEvaluate(Integer evaluate) {
		this.evaluate = evaluate;
	}

	public String getEvaContent() {
		return evaContent;
	}

	public void setEvaContent(String evaContent) {
		this.evaContent = evaContent;
	}
	
}