package com.jeeplus.modules.stuscore.entity;

public class ErrQuestion {
	private String depName;	// 部门外键
	private String majorName;	//专业
	private Integer resultScore;		// 得分
	private String testTime; //时间
	private String describes;		// 试题描述
	private String answer; //试题正确答案
	public String getDepName() {
		return depName;
	}
	public void setDepName(String depName) {
		this.depName = depName;
	}
	public String getMajorName() {
		return majorName;
	}
	public void setMajorName(String majorName) {
		this.majorName = majorName;
	}
	public Integer getResultScore() {
		return resultScore;
	}
	public void setResultScore(Integer resultScore) {
		this.resultScore = resultScore;
	}
	public String getTestTime() {
		return testTime;
	}
	public void setTestTime(String testTime) {
		this.testTime = testTime;
	}
	public String getDescribes() {
		return describes;
	}
	public void setDescribes(String describes) {
		this.describes = describes;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getOptions() {
		return options;
	}
	public void setOptions(String options) {
		this.options = options;
	}
	public String getAnalysis() {
		return analysis;
	}
	public void setAnalysis(String analysis) {
		this.analysis = analysis;
	}
	private String options;		// 选项/简答题答案
	private String analysis;		// 答案解析

}
