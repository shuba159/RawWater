package com.jeeplus.modules.project.entity;

import java.io.Serializable;
import java.util.List;

import com.jeeplus.modules.entity.YsQuestions;
import com.jeeplus.modules.entity.YsTest;

public class YsTestPojo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String id;
	
	private YsTest ysTest;
	
	private Integer papId;
	
	private Integer planId;
	
	private String ranIds;
	
	private String queIds;
	
	private List<YsQuestions> ysQuestionsList;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public YsTest getYsTest() {
		return ysTest;
	}

	public void setYsTest(YsTest ysTest) {
		this.ysTest = ysTest;
	}

	public Integer getPapId() {
		return papId;
	}

	public void setPapId(Integer papId) {
		this.papId = papId;
	}

	public Integer getPlanId() {
		return planId;
	}

	public void setPlanId(Integer planId) {
		this.planId = planId;
	}

	public String getRanIds() {
		return ranIds;
	}

	public void setRanIds(String ranIds) {
		this.ranIds = ranIds;
	}

	public String getQueIds() {
		return queIds;
	}

	public void setQueIds(String queIds) {
		this.queIds = queIds;
	}

	public List<YsQuestions> getYsQuestionsList() {
		return ysQuestionsList;
	}

	public void setYsQuestionsList(List<YsQuestions> ysQuestionsList) {
		this.ysQuestionsList = ysQuestionsList;
	}
	
	

}
