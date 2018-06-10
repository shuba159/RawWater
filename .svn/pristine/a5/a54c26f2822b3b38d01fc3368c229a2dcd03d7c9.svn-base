/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.clickgood.entity;

import com.jeeplus.modules.entity.YsUser;
import com.jeeplus.modules.sys.entity.User;
import javax.validation.constraints.NotNull;

import com.jeeplus.common.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 点赞收藏Entity
 * @author mx
 * @version 2018-05-22
 */
public class YsClickGood extends DataEntity<YsClickGood> {
	
	private static final long serialVersionUID = 1L;
	private String userid;		// 用户外键
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	private Integer couId;		// 课件外键
	private Integer goodState;		// 1.已点赞2.未点赞
	private Integer signState;		// 1.已收藏2.未收藏
	
	public YsClickGood() {
		super();
	}

	public YsClickGood(String id){
		super(id);
	}

	

	
	
	public Integer getCouId() {
		return couId;
	}

	public void setCouId(Integer couId) {
		this.couId = couId;
	}
	
	

	public Integer getGoodState() {
		return goodState;
	}

	public void setGoodState(Integer goodState) {
		this.goodState = goodState;
	}
	
	
	public Integer getSignState() {
		return signState;
	}

	public void setSignState(Integer signState) {
		this.signState = signState;
	}
	
}