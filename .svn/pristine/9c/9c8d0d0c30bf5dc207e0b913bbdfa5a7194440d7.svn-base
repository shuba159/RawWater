/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.stuscore.entity;

import javax.validation.constraints.NotNull;

import com.jeeplus.common.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 错题Entity
 * @author mx
 * @version 2018-05-28
 */
public class YsStuPaper extends DataEntity<YsStuPaper> {
	
	private static final long serialVersionUID = 1L;
	private String userid;		// 用户外键
	private String depId;		// 部门外键
	private String classId;		// 知识分类的Id（目前没有）
	private String typeId;		// 考试类型的Id（目前没有）
	private String queId;		// 题库中题的Id
	private String isRight;		// 答完题的状态，1错，2对
	private String answer;		// 考生答案
	
	public YsStuPaper() {
		super();
	}

	public YsStuPaper(String id){
		super(id);
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getDepId() {
		return depId;
	}

	public void setDepId(String depId) {
		this.depId = depId;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getQueId() {
		return queId;
	}

	public void setQueId(String queId) {
		this.queId = queId;
	}

	public String getIsRight() {
		return isRight;
	}

	public void setIsRight(String isRight) {
		this.isRight = isRight;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	
	
}