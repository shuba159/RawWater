/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.onlinetime.entity;


import com.jeeplus.common.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 在线时长Entity
 * @author mx
 * @version 2018-05-22
 */
public class YsOnlinetime extends DataEntity<YsOnlinetime> {
	
	private static final long serialVersionUID = 1L;
	private Integer userid;		// userid
	private Integer timenumber;		// timenumber
	private String lasetime;		// lasetime
	
	public YsOnlinetime() {
		super();
	}

	public YsOnlinetime(String id){
		super(id);
	}

	@ExcelField(title="userid", align=2, sort=1)
	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	
	@ExcelField(title="timenumber", align=2, sort=2)
	public Integer getTimenumber() {
		return timenumber;
	}

	public void setTimenumber(Integer timenumber) {
		this.timenumber = timenumber;
	}
	
	@ExcelField(title="lasetime", align=2, sort=3)
	public String getLasetime() {
		return lasetime;
	}

	public void setLasetime(String lasetime) {
		this.lasetime = lasetime;
	}
	
}