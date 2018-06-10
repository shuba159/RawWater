package com.jeeplus.modules.entity;

public class YsUserPapAnswer {
    private Integer id;  // ys_user_pap_answer 表id
    private Integer papId;  // 试卷id
    private Integer queId;  // 题库id
    private Integer userId;  // 用户id
    private String answer;  // 答案
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getPapId() {
		return papId;
	}
	public void setPapId(Integer papId) {
		this.papId = papId;
	}
	public Integer getQueId() {
		return queId;
	}
	public void setQueId(Integer queId) {
		this.queId = queId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	@Override
	public String toString() {
		return "YsUserPapAnswer [id=" + id + ", papId=" + papId + ", queId=" + queId + ", userId=" + userId
				+ ", answer=" + answer + "]";
	}
  
    
}
