package com.jeeplus.modules.entity;

import java.util.Date;

public class YsReply {
    private Integer id;  // 回复id
    private String replyContent;  // 回复内容
    private Integer forumId;  // 回复的帖子的id
    private Date replyTime;  // 回复时间
    private Integer userId;  // 回复人id
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public Integer getForumId() {
		return forumId;
	}
	public void setForumId(Integer forumId) {
		this.forumId = forumId;
	}
	public Date getReplyTime() {
		return replyTime;
	}
	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "YsReply [id=" + id + ", replyContent=" + replyContent + ", forumId=" + forumId + ", replyTime="
				+ replyTime + ", userId=" + userId + "]";
	}
	
}
