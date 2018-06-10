/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.entity;
import javax.validation.constraints.NotNull;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.jeeplus.common.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 题库管理Entity
 * @author hjm
 * @version 2018-05-22
 */
public class YsQuestions extends DataEntity<YsQuestions> {
	
	private static final long serialVersionUID = 1L;
	private Integer questionType;		// 试题类型1.单选2.多选3.填空4.判断5.简答
	private Integer level;		// 试题难度1.简单2.一般3.难
	private Integer questionClass;		// 试题分类1.英语考试2.财务会计3.公务员考试
	private Integer questionState;		// 试题状态1.启用2.禁用3.待审核
	private Integer ispath;		// 审批状态1.已审批2.未审批
	private Integer depId;		// 所属部门
	private Integer majorType;		// 专业类型
	private Date updateTime;		// 修改时间
	
	private Date updateTime2;    // 用于查询时间段的临时变量

	private Integer createId;		// 创始人
	private Integer ruleOne;		// 判分规则一1按空算分2全对算分(仅仅填空题需要)
	private Integer ruleTwo;		// 判分规则二1区分答案顺序2不区分答案顺序(仅仅填空题需要)
	private Integer ruleThree;		// 判分规则三1区分大小写2不区分大小写(仅仅填空题需要)
	private String describes;		// 试题描述
	private String answer;		// 试题正确答案
	private String options;		// 选项/简答题答案
	private String analysis;		// 答案解析
	
	private Integer queScore;	// 每道题分数
	
	public YsQuestions() {
		super();
	}

	public YsQuestions(String id){
		super(id);
	}

	@NotNull(message="试题类型不能为空")
	@ExcelField(title="试题类型1.单选2.多选3.填空4.判断5.简答", dictType="question_type", align=2, sort=1)
	public Integer getQuestionType() {
		return questionType;
	}

	public void setQuestionType(Integer questionType) {
		this.questionType = questionType;
	}
	
	@NotNull(message="试题难度不能为空")
	@ExcelField(title="试题难度1.简单2.一般3.难", dictType="level", align=2, sort=2)
	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}
	
	@NotNull(message="试题分类不能为空")
	@ExcelField(title="试题分类1.英语考试2.财务会计3.公务员考试", dictType="question_class", align=2, sort=3)
	public Integer getQuestionClass() {
		return questionClass;
	}

	public void setQuestionClass(Integer questionClass) {
		this.questionClass = questionClass;
	}
	
	@ExcelField(title="试题状态1.启用2.禁用3.待审核", dictType="question_state", align=2, sort=4)
	public Integer getQuestionState() {
		return questionState;
	}

	public void setQuestionState(Integer questionState) {
		this.questionState = questionState;
	}
	
	@ExcelField(title="审批状态1.已审批2.未审批", dictType="ispath", align=2, sort=5)
	public Integer getIspath() {
		return ispath;
	}

	public void setIspath(Integer ispath) {
		this.ispath = ispath;
	}
	
	@NotNull(message="所属部门不能为空")
	@ExcelField(title="所属部门", dictType="dep_id", align=2, sort=6)
	public Integer getDepId() {
		return depId;
	}

	public void setDepId(Integer depId) {
		this.depId = depId;
	}
	
	@ExcelField(title="专业类型", dictType="major_type", align=2, sort=7)
	public Integer getMajorType() {
		return majorType;
	}

	public void setMajorType(Integer majorType) {
		this.majorType = majorType;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="修改时间", align=2, sort=8)
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getUpdateTime2() {
		return updateTime2;
	}

	public void setUpdateTime2(Date updateTime2) {
		this.updateTime2 = updateTime2;
	}
	
	@ExcelField(title="创始人", align=2, sort=9)
	public Integer getCreateId() {
		return createId;
	}

	public void setCreateId(Integer createId) {
		this.createId = createId;
	}
	
	@ExcelField(title="判分规则一1按空算分2全对算分(仅仅填空题需要)", dictType="rule_one", align=2, sort=10)
	public Integer getRuleOne() {
		return ruleOne;
	}

	public void setRuleOne(Integer ruleOne) {
		this.ruleOne = ruleOne;
	}
	
	@ExcelField(title="判分规则二1区分答案顺序2不区分答案顺序(仅仅填空题需要)", dictType="rule_two", align=2, sort=11)
	public Integer getRuleTwo() {
		return ruleTwo;
	}

	public void setRuleTwo(Integer ruleTwo) {
		this.ruleTwo = ruleTwo;
	}
	
	@ExcelField(title="判分规则三1区分大小写2不区分大小写(仅仅填空题需要)", dictType="rule_three", align=2, sort=12)
	public Integer getRuleThree() {
		return ruleThree;
	}

	public void setRuleThree(Integer ruleThree) {
		this.ruleThree = ruleThree;
	}
	
	@ExcelField(title="试题描述", align=2, sort=13)
	public String getDescribes() {
		return describes;
	}

	public void setDescribes(String describes) {
		this.describes = describes;
	}
	
	@ExcelField(title="试题正确答案", align=2, sort=14)
	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	@ExcelField(title="选项/简答题答案", align=2, sort=15)
	public String getOptions() {
		return options;
	}

	public void setOptions(String options) {
		this.options = options;
	}
	
	@ExcelField(title="答案解析", align=2, sort=16)
	public String getAnalysis() {
		return analysis;
	}

	public void setAnalysis(String analysis) {
		this.analysis = analysis;
	}
	
	
	// 每题分数 
	public Integer getQueScore() {
		return queScore;
	}

	public void setQueScore(Integer queScore) {
		this.queScore = queScore;
	}

	@Override
	public String toString() {
		return "YsQuestions [questionType=" + questionType + ", level=" + level + ", questionClass=" + questionClass
				+ ", questionState=" + questionState + ", ispath=" + ispath + ", depId=" + depId + ", majorType="
				+ majorType + ", updateTime=" + updateTime + ", createId=" + createId + ", ruleOne=" + ruleOne
				+ ", ruleTwo=" + ruleTwo + ", ruleThree=" + ruleThree + ", describes=" + describes + ", answer="
				+ answer + ", options=" + options + ", analysis=" + analysis + "]";
	}
	
}