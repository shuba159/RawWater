/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jeeplus.common.persistence.DataEntity;

/**
 * 考试Entity
 * @author wdy
 * @version 2018-05-29
 */
public class YsTest extends DataEntity<YsTest> {
	
	private static final long serialVersionUID = 1L;
	private String testName;		// 考试名称
	private String describes;		// 考试须知
	private Date startTime;		// 考试开始时间
	private Date endTime;		// 考试结束时间
	private Integer testNumber;		// 考生考试的次数限制
	private Integer testState;		// 1.启用2.禁用
	private Integer testTime;		// 答卷时长
	private Integer isEva;		// 已评卷人数
	private Integer notEva;		// 未评卷人数
	private Integer papId;		// 试题外键
	private Integer proId;		// 项目外键
	//private String depName; 	// 部门名称 用于传值
	private String depId; 		// 用于传值
	private String depNames; 	// 部门名称
	
	public YsTest() {
		super();
	}

	public YsTest(String id){
		super(id);
	}

	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public String getDescribes() {
		return describes;
	}

	public void setDescribes(String describes) {
		this.describes = describes;
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

	public Integer getTestNumber() {
		return testNumber;
	}

	public void setTestNumber(Integer testNumber) {
		this.testNumber = testNumber;
	}

	public Integer getTestState() {
		return testState;
	}

	public void setTestState(Integer testState) {
		this.testState = testState;
	}

	public Integer getTestTime() {
		return testTime;
	}

	public void setTestTime(Integer testTime) {
		this.testTime = testTime;
	}

	public Integer getIsEva() {
		return isEva;
	}

	public void setIsEva(Integer isEva) {
		this.isEva = isEva;
	}

	public Integer getNotEva() {
		return notEva;
	}

	public void setNotEva(Integer notEva) {
		this.notEva = notEva;
	}

	public Integer getPapId() {
		return papId;
	}

	public void setPapId(Integer papId) {
		this.papId = papId;
	}

	public Integer getProId() {
		return proId;
	}

	public void setProId(Integer proId) {
		this.proId = proId;
	}

/*	public String getDepName() {
		return depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}*/

	public String getDepId() {
		return depId;
	}

	public void setDepId(String depId) {
		this.depId = depId;
	}

	public String getDepNames() {
		return depNames;
	}

	public void setDepNames(String depNames) {
		this.depNames = depNames;
	}
	
	
}