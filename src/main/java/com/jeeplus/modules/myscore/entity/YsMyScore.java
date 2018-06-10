/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.myscore.entity;

import com.jeeplus.modules.sys.entity.User;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import com.jeeplus.common.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 自测Entity
 * @author mx
 * @version 2018-05-28
 */
public class YsMyScore extends DataEntity<YsMyScore> {
	
	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	private static final long serialVersionUID = 1L;
	private Integer userid;		// 用户外键
	private String papId;		// 试卷外键
	private String testTime;		// 考试时间
	private String answerTime;		// 答题用时
	private Integer testType;		// 考试类型1.独立考试2.课程内考试
	private Integer totalScore;		// 总分数
	private Integer pathScore;		// 及格分数
	private Integer resultScore;		// 得分
	private Integer errorNumber;		// 错题数
	private Integer yesNumber;
	
	public Integer getYesNumber() {
		return yesNumber;
	}

	public void setYesNumber(Integer yesNumber) {
		this.yesNumber = yesNumber;
	}

	public YsMyScore() {
		super();
	}

	public YsMyScore(String id){
		super(id);
	}

	
	@ExcelField(title="试卷外键", align=2, sort=2)
	public String getPapId() {
		return papId;
	}

	public void setPapId(String papId) {
		this.papId = papId;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="考试时间不能为空")
	@ExcelField(title="考试时间", align=2, sort=3)
	public String getTestTime() {
		return testTime;
	}

	public void setTestTime(String testTime) {
		this.testTime = testTime;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull(message="答题用时不能为空")
	@ExcelField(title="答题用时", align=2, sort=4)
	public String getAnswerTime() {
		return answerTime;
	}

	public void setAnswerTime(String answerTime) {
		this.answerTime = answerTime;
	}
	

	@ExcelField(title="考试类型1.独立考试2.课程内考试", align=2, sort=5)
	public Integer getTestType() {
		return testType;
	}

	public void setTestType(Integer testType) {
		this.testType = testType;
	}
	
	@NotNull(message="总分数不能为空")
	@ExcelField(title="总分数", align=2, sort=6)
	public Integer getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(Integer totalScore) {
		this.totalScore = totalScore;
	}
	
	@NotNull(message="及格分数不能为空")
	@ExcelField(title="及格分数", align=2, sort=7)
	public Integer getPathScore() {
		return pathScore;
	}

	public void setPathScore(Integer pathScore) {
		this.pathScore = pathScore;
	}
	
	@NotNull(message="得分不能为空")
	@ExcelField(title="得分", align=2, sort=8)
	public Integer getResultScore() {
		return resultScore;
	}

	public void setResultScore(Integer resultScore) {
		this.resultScore = resultScore;
	}
	
	@NotNull(message="错题数不能为空")
	@ExcelField(title="错题数", align=2, sort=9)
	public Integer getErrorNumber() {
		return errorNumber;
	}

	public void setErrorNumber(Integer errorNumber) {
		this.errorNumber = errorNumber;
	}
	
}