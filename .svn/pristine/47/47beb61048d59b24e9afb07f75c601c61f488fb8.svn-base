/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.entity;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;

import com.jeeplus.common.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 论坛管理Entity
 * @author hjm
 * @version 2018-06-04
 */
public class YsForum extends DataEntity<YsForum> {
	
	private static final long serialVersionUID = 1L;
	private Integer userId;		// 用户外键
	private String forumTitle;		// 帖子标题
	private String forumcontent;		// 帖子的内容
	private Date forumTime;		// 发帖日期
	private Integer topState;		// 置顶状态
	private Integer seeNumber;		// 查看次数
	
	public YsForum() {
		super();
	}

	public YsForum(String id){
		super(id);
	}

	@ExcelField(title="用户外键", align=2, sort=1)
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	@ExcelField(title="帖子标题", align=2, sort=2)
	public String getForumTitle() {
		return forumTitle;
	}

	public void setForumTitle(String forumTitle) {
		this.forumTitle = forumTitle;
	}
	
	@ExcelField(title="帖子的内容", align=2, sort=3)
	public String getForumcontent() {
		return forumcontent;
	}

	public void setForumcontent(String forumcontent) {
		this.forumcontent = forumcontent;
	}
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="发帖日期", align=2, sort=4)
	public Date getForumTime() {
		return forumTime;
	}

	public void setForumTime(Date forumTime) {
		this.forumTime = forumTime;
	}
	
	@ExcelField(title="置顶状态", align=2, sort=5)
	public Integer getTopState() {
		return topState;
	}

	public void setTopState(Integer topState) {
		this.topState = topState;
	}
	
	@ExcelField(title="查看次数", align=2, sort=6)
	public Integer getSeeNumber() {
		return seeNumber;
	}

	public void setSeeNumber(Integer seeNumber) {
		this.seeNumber = seeNumber;
	}
}